package com.applab.app_nav1

import android.content.ClipData
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        //Bottom bar 切換時會變色
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment,R.id.searchFragment),
        drawer_layout //加入 drawer 要設定
        )

        //手動加入ActionBar
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //底部 btton 的配置
        bottom_nav.setupWithNavController(navController)

        //Drawer menu 的配置
        nav_view.setupWithNavController(navController)

        //建立監聽器 Exit 的方法
        nav_view.setNavigationItemSelectedListener {
            if(it.itemId == R.id.item_exit){
                finish()
                true
            }
            //使用NavigationUI 更新界面組件
            NavigationUI.onNavDestinationSelected(it,navController)
            // 點選時收起選單
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

//        nav_view.setNavigationItemSelectedListener(object: NavigationView.OnNavigationItemSelectedListener {
//            override fun onNavigationItemSelected(item: MenuItem): Boolean {
//                return if(item.itemId == R.id.item_exit) {
//                    finish()
//                    true
//                } else {
//                    false
//                }
//            }
//        })
    }


    // navController.navigateUp()
    // 將堆疊內的資料再倒出來 自動到堆疊中 找出上一頁
    // 實現 <- 的作用
    //navController.navigateUp() || super.onSupportNavigateUp()
    //假設前方有 就跑前方
    override fun onSupportNavigateUp(): Boolean {
        //return navController.navigateUp() || super.onSupportNavigateUp()
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    //抽屜
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("MainActivity","onOptionsItemSelected")
        if(item.itemId==R.id.menu_info){
            //具有動畫效果的menu
            var action = NavGraphDirections.actionGlobalInfoFragment()
            navController.navigate(action)
            return true
        }

        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("MainActivity","onCreateOptionsMenu")
        menuInflater.inflate(R.menu.options_menu,menu)
        return true
    }
//    fun onClick(item: MenuItem){
//        finish()
//    }
}
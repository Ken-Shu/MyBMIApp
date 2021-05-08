package com.applab.app_nav1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*

//繼承 Fragment 使用的是 R.layout.frament_home
class HomeFragment :Fragment(R.layout.fragment_home){
    //設定button 可以轉場到 login fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_login.setOnClickListener {
            //在 nav_graph 的配置結束後
            //注意 !! 若要出現HomeFragmentDirections 類別 必須要先按下 上方欄位的 Bulid->Rebulid project
            //才會出現 actionHomeFragmentToLoginFragment 指的是 nav_geaph.xml 的箭頭線
            val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            //讓navigate 取得 action
            findNavController().navigate(action)
        }
    }
}
package com.applab.app_food

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.*
import kotlinx.android.synthetic.main.title.*
import kotlinx.android.synthetic.main.title.image_food
import kotlinx.android.synthetic.main.title.image_new
import kotlinx.android.synthetic.main.title.image_spicy
import kotlinx.android.synthetic.main.title.text_name
import kotlinx.android.synthetic.main.title.text_price
import kotlinx.android.synthetic.main.title.view.*

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    var foods ="foods.json"
    var drinks = "drinks.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image_spicy.setImageResource(android.R.color.transparent)
        image_new.setImageResource(android.R.color.transparent)
        context = this
       service(foods)
    }

    fun service(name : String){
        //讀出 name(json) 的資料

        var json_foods = assets.open(name).bufferedReader().use { it.readText() }
        //2 .json 字串 轉成 food 陣列 (List) 物件
        var foods = Gson().fromJson(json_foods, Array<Food>::class.java).toList()
        Log.d("MainActivity", json_foods)
        Log.d("MainActivity", foods.toString())



        //3.建立適配器 adapter 給 grid_view 使用 第二為放置位置 第三為資料儲存位子 第四為 資料來源
        val adapter = object : ArrayAdapter<Food>(context, R.layout.item, R.id.text_name, foods) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                //這裡指的就是　R.layout.item 所配置的物件
                val v = super.getView(position, convertView, parent)
                val food = getItem(position) // 得到food 物件資料
                //奶油刀 textName = v.text_name
                //把View 內的 text_name 丟進去 textName 裡面
                val textName = v.findViewById<View>(R.id.text_name) as TextView
                //把價錢寫進去textPrice裡面
                val textPrice = v.findViewById<View>(R.id.text_price) as TextView
                //把 image_food 寫進去 imageFood
                val imageFood = v.findViewById<View>(R.id.image_food) as ImageView
                //從食物(json檔內) 依據 idName 來從 drawable<-packageName 檔案內抓取
                val imageFoodId = resources.getIdentifier(food?.idName, "drawable", packageName)

                //辣字樣
                val imagespicy = v.findViewById<View>(R.id.image_spicy) as ImageView
                //新字樣
                val imagenew = v.findViewById<View>(R.id.image_new) as ImageView

                //此行是 根據每一個 food 內的 idName 把取得的資料放入每一個item 裡面
                imageFood.setImageResource(imageFoodId)

                //預設 title 的圖片
                if(name.equals("foods.json")){
                    image_food.setImageResource(imageFoodId)
                    text_price.text=food?.price.toString()
                    text_name.text=food?.name.toString()
                } else if(name.equals("drinks.json")){
                    image_food.setImageResource(imageFoodId)
                    text_price.text=food?.price.toString()
                    text_name.text=food?.name.toString()
                }



                //把抓到的 food.name 丟入 title 的 text_name
                textName.text = food?.name
                //把抓到的 food.price 丟入 title 的 text_price
                textPrice.text = food?.price.toString()


                //判斷食物是否有辣
                if (food!!.spicy) {
                    imagespicy.setImageResource(R.drawable.isspicy)
                } else {
                    //android.R.color.transparent 透明圖
                    imagespicy.setImageResource(android.R.color.transparent)
                }
                //判斷食物是否是新的
                if (food.new) {
                    imagenew.setImageResource(R.drawable.isnew)
                } else {
                    imagenew.setImageResource(android.R.color.transparent)
                }
                return v
            }
        }

        //4 . 配置 adapter 給 grid_view
        grid_view.adapter = adapter


        //5 grid_vrew 配置監聽
        grid_view.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id
                ->
                val food = parent?.getItemAtPosition(position) as Food
                //Toast.makeText(context,food.toString(),Toast.LENGTH_SHORT).show()
                //取的圖檔名稱 名稱 "在哪個檔案"
                val image_id = resources.getIdentifier(food.idName, "drawable", packageName)



                //把抓取到的 impge_id 丟進去 image_food 裡面
                image_food.setImageResource(image_id)
                //把 food.name 丟進去 text_name 裡面
                text_name.text = food.name
                text_price.text = food.price.toString()
                if (food.spicy) {
                    image_spicy.setImageResource(R.drawable.isspicy)
                } else {
                    //android.R.color.transparent 透明圖
                    image_spicy.setImageResource(android.R.color.transparent)
                }
                if (food.new) {
                    image_new.setImageResource(R.drawable.isnew)
                } else {
                    image_new.setImageResource(android.R.color.transparent)
                }

            }
    }


//
//    private fun Foodservice() {
//        //1. 抓取 assets 資料夾下面的 foods.json
//        //bufferedReader 讀 text
//        var json_foods = assets.open("foods.json").bufferedReader().use { it.readText() }
//        //2 .json 字串 轉成 food 陣列 (List) 物件
//        var foods = Gson().fromJson(json_foods, Array<Food>::class.java).toList()
//        Log.d("MainActivity", json_foods)
//        Log.d("MainActivity", foods.toString())
//        //3.建立適配器 adapter 給 grid_view 使用 第三為資料儲存位子 第四為 資料來源
//        val adapter = object : ArrayAdapter<Food>(context, R.layout.item, R.id.text_name, foods) {
//            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//                //這裡指的就是　R.layout.item 所配置的物件
//                val v = super.getView(position, convertView, parent)
//                val food = getItem(position) // 得到food 物件資料
//                //奶油刀 textName = v.text_name
//                val textName = v.findViewById<View>(R.id.text_name) as TextView
//                val textPrice = v.findViewById<View>(R.id.text_price) as TextView
//                val imageFood = v.findViewById<View>(R.id.image_food) as ImageView
//                val imageFoodId = resources.getIdentifier(food?.idName, "drawable", packageName)
//                imageFood.setImageResource(imageFoodId)
//                textName.text = food?.name
//                textPrice.text = food?.price.toString()
//
//                val imagespicy = v.findViewById<View>(R.id.image_spicy) as ImageView
//
//                val imagenew = v.findViewById<View>(R.id.image_new) as ImageView
//
//                if (food!!.spicy) {
//                    imagespicy.setImageResource(R.drawable.isspicy)
//                } else {
//                    //android.R.color.transparent 透明圖
//                    imagespicy.setImageResource(android.R.color.transparent)
//                }
//                if (food.new) {
//                    imagenew.setImageResource(R.drawable.isnew)
//                } else {
//                    imagenew.setImageResource(android.R.color.transparent)
//                }
//                return v
//            }
//        }
//
//        //4 . 配置 adapter 給 grid_view
//        grid_view.adapter = adapter
//
//        //5 grid_vrew 配置監聽
//        grid_view.onItemClickListener =
//            AdapterView.OnItemClickListener { parent, view, position, id
//                ->
//                val food = parent?.getItemAtPosition(position) as Food
//                //Toast.makeText(context,food.toString(),Toast.LENGTH_SHORT).show()
//                //取的圖檔名稱 名稱 "在哪個檔案"
//                val image_id = resources.getIdentifier(food.idName, "drawable", packageName)
//                //把抓取到的 impge_id 丟進去 image_food 裡面
//                image_food.setImageResource(image_id)
//                //把 food.name 丟進去 text_name 裡面
//                text_name.text = food.name
//                text_price.text = food.price.toString()
//                if (food.spicy) {
//                    image_spicy.setImageResource(R.drawable.isspicy)
//                } else {
//                    //android.R.color.transparent 透明圖
//                    image_spicy.setImageResource(android.R.color.transparent)
//                }
//                if (food.new) {
//                    image_new.setImageResource(R.drawable.isnew)
//                } else {
//                    image_new.setImageResource(android.R.color.transparent)
//                }
//
//            }
//    }
//    private fun Drinkservice() {
//        //1. 抓取 assets 資料夾下面的 foods.json
//        //bufferedReader 讀 text
//        var json_foods = assets.open("drinks.json").bufferedReader().use { it.readText() }
//        //2 .json 字串 轉成 food 陣列 (List) 物件
//        var foods = Gson().fromJson(json_foods, Array<Food>::class.java).toList()
//        Log.d("MainActivity", json_foods)
//        Log.d("MainActivity", foods.toString())
//        //3.建立適配器 adapter 給 grid_view 使用 第三為資料儲存位子 第四為 資料來源
//        val adapter = object : ArrayAdapter<Food>(context, R.layout.item, R.id.text_name, foods) {
//            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//                //這裡指的就是　R.layout.item 所配置的物件
//                val v = super.getView(position, convertView, parent)
//                val food = getItem(position) // 得到food 物件資料
//                //奶油刀 textName = v.text_name
//                val textName = v.findViewById<View>(R.id.text_name) as TextView
//                val textPrice = v.findViewById<View>(R.id.text_price) as TextView
//                val imageFood = v.findViewById<View>(R.id.image_food) as ImageView
//                val imageFoodId = resources.getIdentifier(food?.idName, "drawable", packageName)
//                imageFood.setImageResource(imageFoodId)
//                textName.text = food?.name
//                textPrice.text = food?.price.toString()
//
//                val imagespicy = v.findViewById<View>(R.id.image_spicy) as ImageView
//
//                val imagenew = v.findViewById<View>(R.id.image_new) as ImageView
//
//                if (food!!.spicy) {
//                    imagespicy.setImageResource(R.drawable.isspicy)
//                } else {
//                    //android.R.color.transparent 透明圖
//                    imagespicy.setImageResource(android.R.color.transparent)
//                }
//                if (food.new) {
//                    imagenew.setImageResource(R.drawable.isnew)
//                } else {
//                    imagenew.setImageResource(android.R.color.transparent)
//                }
//                return v
//            }
//        }
//
//        //4 . 配置 adapter 給 grid_view
//        grid_view.adapter = adapter
//
//        //5 grid_vrew 配置監聽
//        grid_view.onItemClickListener =
//            AdapterView.OnItemClickListener { parent, view, position, id
//                ->
//                val food = parent?.getItemAtPosition(position) as Food
//                //Toast.makeText(context,food.toString(),Toast.LENGTH_SHORT).show()
//                //取的圖檔名稱 名稱 "在哪個檔案"
//                val image_id = resources.getIdentifier(food.idName, "drawable", packageName)
//                //把抓取到的 impge_id 丟進去 image_food 裡面
//                image_food.setImageResource(image_id)
//                //把 food.name 丟進去 text_name 裡面
//                text_name.text = food.name
//                text_price.text = food.price.toString()
//                if (food.spicy) {
//                    image_spicy.setImageResource(R.drawable.isspicy)
//                } else {
//                    //android.R.color.transparent 透明圖
//                    image_spicy.setImageResource(android.R.color.transparent)
//                }
//                if (food.new) {
//                    image_new.setImageResource(R.drawable.isnew)
//                } else {
//                    image_new.setImageResource(android.R.color.transparent)
//                }
//
//            }
//    }
        //建立選單
        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            //設置用哪個 menu檔最為選單
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem) : Boolean{
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            Log.d("MainActivity","Food")
            var id  : Int = item.itemId
            if (id == R.id.action_food){
                service(foods)
                return true
            }else if(id == R.id.action_exit){
                finish()
                return true
            }else if (id == R.id.action_drink){
                service(drinks)
                return true
            }
            return super.onOptionsItemSelected(item)
        }
    }


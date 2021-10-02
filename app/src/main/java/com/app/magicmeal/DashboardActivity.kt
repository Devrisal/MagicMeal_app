package com.app.magicmeal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.magicmeal.adapter.ProductAdapter
import com.app.magicmeal.model.Fooditem
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {


    private var listOfMeal = arrayListOf<Fooditem>()

    companion object {
        var listOfItemInCart = arrayListOf<Fooditem>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        loadList()
        val adapter = ProductAdapter(listOfMeal, this)
        recyclerMeal.adapter = adapter
        recyclerMeal.layoutManager = LinearLayoutManager(this)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dashboard, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.miUser -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.miCart -> {
                startActivity(Intent(this, CartActivity::class.java))
            }

        }
        return true
    }

    private fun addToCart() {

    }

    private fun loadList() {
        val fooditem1 = Fooditem(
            "Daal Bhat",
            R.drawable.dalbhat,
            350F,
            "Nepali Khana"

        )
        listOfMeal.add(fooditem1)

        val fooditem2 = Fooditem(
            "Momo",
            R.drawable.momo,
            150F,
            "Nepali Snack"
        )
        listOfMeal.add(fooditem2)

        val fooditem3 = Fooditem(
            "Sadheko Mix Khaja",
            R.drawable.sadekokhaja,
            250F,
            "Snacks"
        )
        listOfMeal.add(fooditem3)

        val fooditem4 = Fooditem(
            "Selroti",
            R.drawable.selroti,
            30F,
            "Nepali Roti"
        )
        listOfMeal.add(fooditem4)
    }

}
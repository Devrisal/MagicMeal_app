package com.app.magicmeal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.magicmeal.R
import com.app.magicmeal.model.Fooditem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_list_product_recycler.view.*

class ProductAdapter(
    private val listOfMeal: ArrayList<Fooditem>,
    private val context: Context
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    companion object {
        val cartList: ArrayList<Fooditem> = arrayListOf()
    }

    inner class ProductViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val productName: TextView = v.tvMealName
        val productImage: ImageView = v.imgMeal
        val cartView: TextView = v.tvAddToCart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_list_product_recycler, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val meal = listOfMeal[position]
        holder.productName.text = meal.item_name.toString()
        holder.productImage.setImageResource(meal.image!!)
        holder.cartView.setOnClickListener {
            cartList.add(meal)
        }
    }

    override fun getItemCount(): Int = listOfMeal.size

}
package com.app.magicmeal.adapter

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.layout_list_cart_recycler.view.*
import kotlinx.android.synthetic.main.layout_list_product_recycler.view.*
import org.w3c.dom.Text


class CartAdapter(
    private val listOfMeal: ArrayList<Fooditem>,
    private val context: Context
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    var i: Int = 1
    val price: Float = 0.0F
    val listOfCart = arrayListOf<String>()

    inner class CartViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val productName: TextView = v.tvProductName
        val productImage: ImageView = v.imgCartMeal
        val delete: TextView = v.tvDeleteFromCart
        val plus: TextView = v.tvAddQty
        val minus: TextView = v.tvSubQty
        val count: TextView = v.tvProductCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_list_product_recycler, parent, false)
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val meal = listOfMeal[position]
        holder.productName.text = meal.item_name.toString()
        Glide.with(context)
            .load(R.drawable.img_login)
            .into(holder.productImage)
        listOfCart.add(meal.toString())

        holder.delete.setOnClickListener {
            listOfCart.removeAt(position - 1)
            notifyDataSetChanged()
        }

        holder.plus.setOnClickListener {
            i++
            holder.count.text = (meal.price?.times(i)).toString()
        }
        holder.minus.setOnClickListener {
            i--
            holder.count.text = (meal.price?.times(i)).toString()
        }

    }

    override fun getItemCount(): Int = listOfMeal.size

}
package com.example.btik.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.btik.R
import com.example.btik.databinding.ItemListBarangBinding

class ProductAdapter(var c : Context, var productList: ArrayList<DataBarang>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(var v :ItemListBarangBinding) : RecyclerView.ViewHolder(v.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflter = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ItemListBarangBinding>(
            inflter, R.layout.item_list_barang,parent, false)
        return ProductViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.v.isProduct = productList[position]
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
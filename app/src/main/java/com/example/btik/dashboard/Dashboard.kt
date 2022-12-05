package com.example.btik.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.btik.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {

    lateinit var mDataBase : DatabaseReference
    private lateinit var productList: ArrayList<DataBarang>
    private lateinit var mAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        productList = ArrayList()
        mAdapter = ProductAdapter(this,productList)
        recycleProduct.layoutManager = LinearLayoutManager(this)
        recycleProduct.setHasFixedSize(true)
        recycleProduct.adapter = mAdapter
        getProductData()

    }

    private fun getProductData(){

        mDataBase = FirebaseDatabase.getInstance().getReference("Product")
        mDataBase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (productSnapshot in snapshot.children){
                        val product = productSnapshot.getValue(DataBarang :: class.java)
                        productList.add(product!!)
                    }
                    recycleProduct.adapter = mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Dashboard, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
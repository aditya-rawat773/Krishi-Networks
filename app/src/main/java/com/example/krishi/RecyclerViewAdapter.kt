package com.example.krishi

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recycler_view_row.view.*

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var items = ArrayList<OtherMandi>()

    fun setListData(data: ArrayList<OtherMandi>){
        this.items = data
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {

        val inflater =  LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }



   inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

       private val tvDistrict = view.tvDistrict
       private val tvDistance = view.tvDistance
       private val tvState = view.tvState
       private val ivImage = view.ivOtherMandiImage

        @SuppressLint("SetTextI18n")
        fun bind(data: OtherMandi){

            tvDistrict.text = data.district
            tvState.text = data.state
            tvDistance.text = data.km.toString()+" Km"

            val url = data.image

            Glide.with(ivImage)
                    .load(url)
                    .circleCrop()
                    .into(ivImage)
        }
    }
}
package com.example.krishi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.krishi.network.RetroInstance
import com.example.krishi.network.RetroService
import com.example.krishi.viewmodels.RecyclerActivityViewModel
import kotlinx.android.synthetic.main.activity_recycler_view.*
import retrofit2.Call
import retrofit2.Response

class RecyclerViewActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter:RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        intiRecyclerView()
        createData()
    }

    private fun intiRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(applicationContext,VERTICAL)
            addItemDecoration(divider)
        }
    }

    private fun createData(){


        var viewModel = ViewModelProvider(this).get(RecyclerActivityViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this,Observer<MandiResponse>{

            if(it != null){
                recyclerViewAdapter.setListData(it.data.other_mandi as ArrayList<OtherMandi>)
                recyclerViewAdapter.notifyDataSetChanged()
            }
            else{
                Toast.makeText(this@RecyclerViewActivity,"Error",Toast.LENGTH_LONG).show()
            }

        })

        viewModel.makeMakeApiCall()


    }

}

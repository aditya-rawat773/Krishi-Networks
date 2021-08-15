package com.example.krishi.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.krishi.MandiResponse
import com.example.krishi.network.RetroInstance
import com.example.krishi.network.RetroService
import retrofit2.Call
import retrofit2.Response

class RecyclerActivityViewModel: ViewModel(){
    private var recyclerListData:MutableLiveData<MandiResponse> = MutableLiveData()

    fun getRecyclerListDataObserver(): MutableLiveData<MandiResponse> {
       return recyclerListData
    }


    fun makeMakeApiCall(){
        val retroInstance =  RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromApi()

        call.enqueue(object : retrofit2.Callback<MandiResponse> {
            override fun onResponse(call: Call<MandiResponse>, response: Response<MandiResponse>) {


                if(response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                }
                else{
                    recyclerListData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<MandiResponse>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }

}
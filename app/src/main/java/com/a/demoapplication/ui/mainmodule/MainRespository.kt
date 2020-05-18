package com.a.demoapplication.ui.mainmodule

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.a.demoapplication.core.network.RetrofitCallAPI
import com.a.demoapplication.core.network.WebServiceAPI
import com.a.demoapplication.data.api.PlaceHolderModel
import com.a.demoapplication.utils.DialogUtils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

class MainRespository {

    val userList = MutableLiveData<List<PlaceHolderModel>>()
    fun callUserListApi(): LiveData<List<PlaceHolderModel>> {
        CoroutineScope(Dispatchers.Main).launch {
            val resultDef: Deferred<Response<List<PlaceHolderModel>>> = getAllUserList()
            try {
                val result: Response<List<PlaceHolderModel>> = resultDef.await()
                if (result.isSuccessful) {
                    val response = result.body()
                    response?.let {

                        if (response != null) {
                            if (response!=null) {

                                DialogUtils.stopProgressDialog()
                                userList.value = response


                            } else {

                                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT)
                                    .show()
                            }


                        }

                    }
                } else {
                    DialogUtils.stopProgressDialog()
                    Toast.makeText(
                        mContext,
                        "Please Check Internet Connections.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            } catch (ex: Exception) {
                DialogUtils.stopProgressDialog()
                resultDef.getCompletionExceptionOrNull()?.let {
                    println(resultDef.getCompletionExceptionOrNull()!!.message)
                }

            }
        }
        return userList

    }

     fun getAllUserList(): Deferred<Response<List<PlaceHolderModel>>> {
        return RetrofitCallAPI.getInstance(WebServiceAPI.SERVERBASE_URL)!!.getUserListREsponse()
    }


    companion object {

        var mainRespository: MainRespository? = null
        var mContext: Application? = null


        @Synchronized
        @JvmStatic
        fun getInstance(context: Application): MainRespository {
            mContext = context
            if (mainRespository == null) mainRespository = MainRespository()
            return mainRespository!!
        }


    }


}

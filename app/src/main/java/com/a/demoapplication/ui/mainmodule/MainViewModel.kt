package com.a.demoapplication.ui.mainmodule

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.a.demoapplication.core.presentation.base.BaseViewModel
import com.a.demoapplication.data.api.PlaceHolderModel



class MainViewModel(application: Application): BaseViewModel(application){
    var itemPagedList: LiveData<PagedList<PlaceHolderModel>>? = null
    var liveDataSource: LiveData<PageKeyedDataSource<Int, PlaceHolderModel>>? = null
    fun UserListApi(): LiveData<List<PlaceHolderModel> >{
        return MainRespository.getInstance(getApplication()).callUserListApi()
    }


  /*  var postsLiveData  :LiveData<PagedList<PlaceHolderModel>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        postsLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPosts():LiveData<PagedList<PlaceHolderModel>> = postsLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, PlaceHolderModel> {

        val dataSourceFactory = object : DataSource.Factory<String, PlaceHolderModel>() {
            override fun create(): DataSource<String, PlaceHolderModel> {
                return PostsDataSource(Dispatchers.IO)
            }
        }
        return LivePagedListBuilder<String, PlaceHolderModel>(dataSourceFactory, config)
    }*/

}

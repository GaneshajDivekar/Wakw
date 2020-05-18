package com.a.demoapplication.core.network

import com.a.demoapplication.data.api.PlaceHolderModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


public interface RetrofitApiInterface {
    @GET(WebServiceAPI.user_list)
    fun getUserListREsponse():Deferred<Response<List<PlaceHolderModel>>>



}



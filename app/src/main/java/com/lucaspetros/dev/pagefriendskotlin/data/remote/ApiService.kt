package com.lucaspetros.dev.pagefriendskotlin.data.remote

import com.lucaspetros.dev.pagefriendskotlin.data.model.response.FriendsResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users?")
    fun getListFriendsByPage(@Query("page")id:String):Observable<FriendsResponse>
}
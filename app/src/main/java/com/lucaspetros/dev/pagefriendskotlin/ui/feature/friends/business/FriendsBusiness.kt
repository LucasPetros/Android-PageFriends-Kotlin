package com.lucaspetros.dev.pagefriendskotlin.ui.feature.friends.business

import com.lucaspetros.dev.pagefriendskotlin.data.model.response.FriendsResponse
import com.lucaspetros.dev.pagefriendskotlin.data.model.response.dto.FriendsDTO
import com.lucaspetros.dev.pagefriendskotlin.data.remote.ApiService
import com.lucaspetros.dev.pagefriendskotlin.data.remote.RetroInstance
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class FriendsBusiness {
    private val retroInstance = RetroInstance.getRetrofit()!!.create(ApiService::class.java)
    private lateinit var friendsResponse: FriendsResponse

    fun getListFriends(pageId: String?): Observable<FriendsDTO> {
        return Observable.defer {
            retroInstance.getListFriendsByPage(pageId!!)
                .map { friendsResponse2 ->
                    this.friendsResponse = friendsResponse2
                    FriendsDTO(friendsResponse)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

}
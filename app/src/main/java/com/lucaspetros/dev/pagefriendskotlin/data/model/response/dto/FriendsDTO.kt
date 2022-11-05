package com.lucaspetros.dev.pagefriendskotlin.data.model.response.dto

import com.lucaspetros.dev.pagefriendskotlin.data.model.User
import com.lucaspetros.dev.pagefriendskotlin.data.model.response.FriendsResponse

data class FriendsDTO(val friendsResponse: FriendsResponse) {
    private val totalPages:Int = friendsResponse.total_pages
    private val listFriends: MutableList<User> = friendsResponse.data

    fun getTotalPages():Int{
        return totalPages
    }

    fun getFriends(): MutableList<User> {
        return listFriends
    }

}

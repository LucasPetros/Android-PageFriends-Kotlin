package com.lucaspetros.dev.pagefriendskotlin.data.model.response

import com.lucaspetros.dev.pagefriendskotlin.data.model.User

data class FriendsResponse(val data: ArrayList<User>, val total_pages: Int) {

}
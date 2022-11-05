package com.lucaspetros.dev.pagefriendskotlin.data.model

import com.google.gson.annotations.SerializedName

class User {

    var id:Int = 0
    var email: String? = null

    @SerializedName("first_name")
    var firstName: String? = null

    @SerializedName("last_name")
    var lastName: String? = null
    var avatar: String? = null
}
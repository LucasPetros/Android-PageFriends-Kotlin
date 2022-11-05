package com.lucaspetros.dev.pagefriendskotlin.ui.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

class Picasso {

    companion object{
        fun picasso(imgView:ImageView,imgUrl:String){
            Picasso.get()
                .load(imgUrl)
                .resize(50,50)
                .centerCrop()
                .into(imgView)
        }
    }

}
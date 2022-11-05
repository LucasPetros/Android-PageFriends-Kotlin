package com.lucaspetros.dev.pagefriendskotlin.ui.feature.friends.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lucaspetros.dev.pagefriendskotlin.R
import com.lucaspetros.dev.pagefriendskotlin.ui.feature.friends.presentation.fragments.MyFriendsFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(MyFriendsFragment())
    }

    fun replaceFragment(fragment: Fragment?) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.my_frame_container, fragment!!).commit()
    }
}
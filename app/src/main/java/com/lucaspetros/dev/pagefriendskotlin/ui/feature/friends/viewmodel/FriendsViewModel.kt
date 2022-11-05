package com.lucaspetros.dev.pagefriendskotlin.ui.feature.friends.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucaspetros.dev.pagefriendskotlin.data.model.User
import com.lucaspetros.dev.pagefriendskotlin.ui.feature.friends.business.FriendsBusiness
import io.reactivex.rxjava3.disposables.CompositeDisposable

class FriendsViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val friendsListLiveData: MutableLiveData<MutableList<User>?> = MutableLiveData()
    private val error: MutableLiveData<Throwable> = MutableLiveData()
    private val load: MutableLiveData<Boolean> = MutableLiveData()
    private val listNull: MutableLiveData<Boolean> = MutableLiveData()

    companion object {
        private val myFriends = mutableListOf<User>()
        private val friendsBusiness = FriendsBusiness()
    }


    private fun makeApiCall(pages: Int) {
        myFriends.clear()
        for (i in 1..pages) {
            friendsBusiness.getListFriends(i.toString())
                .subscribe(
                    { response ->
                        myFriends.addAll(response.getFriends())
                        friendsListLiveData.postValue(myFriends)
                    },
                    { throwable -> error.postValue(throwable) }

                ) { load.postValue(false) }
        }
    }

    fun getApiPages() {
        load.postValue(true)
        friendsBusiness.getListFriends("1")
            .subscribe(
                { response ->
                    makeApiCall(response.getTotalPages())
                },
                { throwable -> error.postValue(throwable) }
            )
    }


    fun getFriendsFilter(search: String) {
        val userFilter = mutableListOf<User>()
        load.postValue(true)
        myFriends.forEach {
            val fullName: String = it.firstName + " " + it.lastName
            when (true) {
                fullName.lowercase().contains(search.lowercase()) -> userFilter.add(it)
                else -> {}
            }
        }
        if (userFilter.isEmpty() && search == "") {
            friendsListLiveData.postValue(myFriends)

        } else if (userFilter.isEmpty()) {
            friendsListLiveData.postValue(userFilter)
            listNull.postValue(true)
        } else {
            friendsListLiveData.postValue(userFilter)
        }

    }

    fun getFriendsObserver(): MutableLiveData<MutableList<User>?> {
        return friendsListLiveData
    }

    fun getErrorObserver(): MutableLiveData<Throwable> {
        return error
    }

    fun getLoadObserver(): MutableLiveData<Boolean> {
        return load
    }

    fun getListNull(): MutableLiveData<Boolean> {
        return listNull
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}
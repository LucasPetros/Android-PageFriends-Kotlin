package com.lucaspetros.dev.pagefriendskotlin.ui.feature.friends.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucaspetros.dev.pagefriendskotlin.R
import com.lucaspetros.dev.pagefriendskotlin.databinding.FragmentMyFriendsBinding
import com.lucaspetros.dev.pagefriendskotlin.ui.feature.friends.adapter.FriendsAdapter
import com.lucaspetros.dev.pagefriendskotlin.ui.feature.friends.viewmodel.FriendsViewModel


class MyFriendsFragment : Fragment() {
    private var binding: FragmentMyFriendsBinding? = null
    private lateinit var viewModel: FriendsViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyFriendsBinding.inflate(inflater, container, false)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[FriendsViewModel::class.java]
        configRecyclerView()
        loadApi()
        refresh()

        startLoadingAnimation()


        viewModel.getLoadObserver().observe(viewLifecycleOwner) {
            if (!it) {
                loadingAnimation()
            }
        }

        viewModel.getErrorObserver().observe(viewLifecycleOwner) {
            Toast.makeText(requireActivity(), getString(R.string.errorMessage), Toast.LENGTH_SHORT)
                .show()
        }

        viewModel.getListNull().observe(viewLifecycleOwner) {
            if (it) {
                binding!!.txtUserNotFound.visibility = View.VISIBLE
            } else binding!!.txtUserNotFound.visibility = View.GONE

        }

        binding!!.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                viewModel.getFriendsFilter(binding!!.edtSearch.text.toString())
            }

            override fun afterTextChanged(editable: Editable) {}
        })


    }


    private fun loadApi() {

        viewModel.getFriendsObserver().observe(viewLifecycleOwner) {
            if (it != null) {
                binding!!.txtUserNotFound.visibility = View.GONE
                recyclerView.adapter = FriendsAdapter(it)
            }


        }

        viewModel.getApiPages()
    }


    private fun configRecyclerView() {
        recyclerView = requireView().findViewById(R.id.rvFriends)
        recyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(this@MyFriendsFragment.context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager

        }

    }

    private fun startLoadingAnimation() {
        binding!!.cardShimmer.visibility = View.VISIBLE
        binding!!.cardShimmer2.visibility = View.VISIBLE
        binding!!.shimmerViewContainer1.startShimmer()
        binding!!.shimmerViewContainer2.startShimmer()
    }

    private fun loadingAnimation() {
        binding!!.shimmerViewContainer1.stopShimmer()
        binding!!.shimmerViewContainer2.stopShimmer()
        binding!!.cardShimmer.visibility = View.GONE
        binding!!.cardShimmer2.visibility = View.GONE
    }


    private fun refresh() {
        binding!!.swiperefresh.setOnRefreshListener {
            loadApi()
            binding!!.edtSearch.setText("")
            binding!!.swiperefresh.isRefreshing = false
        }
    }


}


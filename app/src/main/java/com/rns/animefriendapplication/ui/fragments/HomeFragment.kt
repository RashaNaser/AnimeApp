package com.rns.animefriendapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.lifecycleScope
import com.rns.animefriendapplication.data.repository.AnimeRepository
import com.rns.animefriendapplication.data.Status
import com.rns.animefriendapplication.data.response.AnimeResponse
import com.rns.animefriendapplication.data.response.Data
import com.rns.animefriendapplication.ui.base.BaseFragment
import com.rns.animefriendapplication.databinding.FragmentHomeBinding
import com.rns.animefriendapplication.ui.interfaces.OnClickListener
import com.rns.animefriendapplication.ui.adapter.AnimeAdapter
import com.rns.animefriendapplication.utils.Constants
import com.rns.animefriendapplication.utils.navigateTo
import kotlinx.coroutines.async

class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnClickListener {
    override val inflate: (LayoutInflater, ViewGroup?, attachToRoot: Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun setup() {
        getRequestAsync()
    }

    private fun getRequestAsync() =
        lifecycleScope.async {
            AnimeRepository().fetchAnimeList().collect { state ->
                showResponseStatus(state)
            }
        }

    private fun showResponseStatus(responseState: Status<AnimeResponse>) = when (responseState) {
        is Status.Loading -> {
            startLoadingEffect()
        }
        is Status.Error -> {
            Toast.makeText(requireActivity(), "Oops, there are some issues here.", LENGTH_SHORT)
                .show()
        }
        is Status.Success -> responseState.data.data?.let {
            stopLoading()
            setupRecyclerview(it)
        }
    }

    private fun startLoadingEffect() {
        binding.shimmerLayout.startShimmer()
    }

    private fun stopLoading() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE
    }

    private fun setupRecyclerview(dataList: List<Data>) {
        binding.recyclerview.visibility = View.VISIBLE
        binding.recyclerview.adapter = AnimeAdapter(dataList, this)
    }

    override fun onClick(data: Data) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.ANIME_ITEM, data)
        requireActivity().navigateTo(DetailAnimeFragment(), bundle)
    }
}
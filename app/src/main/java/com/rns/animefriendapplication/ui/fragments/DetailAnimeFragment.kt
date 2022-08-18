package com.rns.animefriendapplication.ui.fragments

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rns.animefriendapplication.R
import com.rns.animefriendapplication.data.response.Data
import com.rns.animefriendapplication.ui.base.BaseFragment
import com.rns.animefriendapplication.databinding.FragmentDetailAnimeBinding
import com.rns.animefriendapplication.utils.Constants
import com.rns.animefriendapplication.utils.back
import com.rns.animefriendapplication.utils.replaceFragment

class DetailAnimeFragment : BaseFragment<FragmentDetailAnimeBinding>() {
    private lateinit var data: Data

    override val inflate: (LayoutInflater, ViewGroup?, attachToRoot: Boolean) -> FragmentDetailAnimeBinding
        get() = FragmentDetailAnimeBinding::inflate

    override fun setup() {
        data = arguments?.getParcelable(Constants.ANIME_ITEM)!!

        goBackToPreviousScreen()
        setData()
        openTrailer()
    }

    private fun setData() {
        Glide.with(requireActivity()).load(data.images.jpg.image_url)
            .placeholder(R.drawable.ic_placeholder)
            .into(binding.imagePoster)

        binding.textTitle.text = data.title_english
        binding.textDate.text = data.aired.string
        binding.textDescription.text = data.synopsis
    }

    private fun openTrailer() {
        binding.videoCard.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.trailer.url)))
        }
    }

    private fun goBackToPreviousScreen() {
        binding.imageBackArrow.setOnClickListener {
            requireActivity().replaceFragment(HomeFragment())
        }
    }
}
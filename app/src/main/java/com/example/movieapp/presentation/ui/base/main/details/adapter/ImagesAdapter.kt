package com.example.movieapp.presentation.ui.base.main.details.adapter

import android.view.View
import com.example.movieapp.R
import com.example.movieapp.data.model.Images
import com.example.movieapp.presentation.ui.base.RecyclerAdapter

/**
 * Authored by Abdelrahman Ahmed on 20 Nov, 2020.
 */
class ImagesAdapter : RecyclerAdapter<String, ImagesViewHolder>() {

  private lateinit var onDownloadClick: (String) -> Unit
  override fun getLayoutId(viewType: Int) = R.layout.item_image

  override fun instantiateViewHolder(itemView: View, viewType: Int) =
    ImagesViewHolder(itemView, onDownloadClick)

  override fun setData(newItems: List<String>) {
    adapterItems.addAll(newItems)
  }

  fun setOnDownloadClick(onDownloadClick: (String) -> Unit) {
    this.onDownloadClick = onDownloadClick
  }

  override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
    holder.bind(adapterItems[position])
  }

}
package com.example.movieapp.presentation.ui.base.main.details.adapter

import android.view.View
import android.widget.ImageView
import com.example.movieapp.R
import com.example.movieapp.data.remote.api.Urls.BASE_IMG_URL
import com.example.movieapp.presentation.ui.base.RecyclerAdapter.BaseViewHolder
import com.example.movieapp.utility.extensions.loadWithoutPlaceHolder

/**
 * Authored by Abdelrahman Ahmed on 20 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
class ImagesViewHolder(itemView: View,private val downloadClick : (String)->Unit) : BaseViewHolder<String>(itemView) {
  private val image = itemView.findViewById<ImageView>(R.id.peopleGridImageView)
  private val download = itemView.findViewById<ImageView>(R.id.peopleGridDownload)

  override fun bind(item: String) {
    val imageUrl = "$BASE_IMG_URL$item"
    image.loadWithoutPlaceHolder(imageUrl)
    onDownloadClicked(imageUrl)
  }

  private fun onDownloadClicked(url:String){
    download.setOnClickListener {
      downloadClick.invoke(url)
    }
  }
}
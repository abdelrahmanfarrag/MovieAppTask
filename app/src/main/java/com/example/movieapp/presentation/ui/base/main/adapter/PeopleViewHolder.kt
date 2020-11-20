package com.example.movieapp.presentation.ui.base.main.adapter

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.View
import com.example.movieapp.data.model.People
import com.example.movieapp.data.remote.api.Urls.BASE_IMG_URL
import com.example.movieapp.presentation.ui.base.RecyclerAdapter.BaseViewHolder
import com.example.movieapp.utility.extensions.loadWithoutPlaceHolder
import kotlinx.android.synthetic.main.item_people.view.peopleNameTextView
import kotlinx.android.synthetic.main.item_people.view.peoplePopularityTextView
import kotlinx.android.synthetic.main.item_people.view.peopleProfileImageView

/**
 * Authored by Abdelrahman Ahmed on 20 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
class PeopleViewHolder(itemView: View, private val peopleClick: (Int) -> Unit) :
  BaseViewHolder<People.PeopleResult>(itemView) {

  private val peopleName = itemView.peopleNameTextView
  private val peopleImage = itemView.peopleProfileImageView
  private val peoplePopularityCount = itemView.peoplePopularityTextView
  @SuppressLint("SetTextI18n")
  override fun bind(item: People.PeopleResult) {
    val imageUrl = "$BASE_IMG_URL${item.profilePath}"
    peopleImage.loadWithoutPlaceHolder(imageUrl)
    peopleName.text = item.name
    peoplePopularityCount.text = "${item.popularity}"
    peopleClick(item.id)

  }
  private fun peopleClick(id:Int){
    peopleImage.setOnClickListener {
      peopleClick.invoke(id)
    }
  }
}
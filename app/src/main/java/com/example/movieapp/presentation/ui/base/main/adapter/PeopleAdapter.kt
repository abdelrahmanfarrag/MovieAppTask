package com.example.movieapp.presentation.ui.base.main.adapter

import android.view.View
import com.example.movieapp.R
import com.example.movieapp.data.model.People.PeopleResult
import com.example.movieapp.presentation.ui.base.RecyclerAdapter

/**
 * Authored by Abdelrahman Ahmed on 20 Nov, 2020.
 */
class PeopleAdapter : RecyclerAdapter<PeopleResult, PeopleViewHolder>() {

  private lateinit var peopleClick: (Int) -> Unit

  override fun getLayoutId(viewType: Int) = R.layout.item_people

  override fun instantiateViewHolder(itemView: View, viewType: Int) = PeopleViewHolder(itemView,peopleClick)

  override fun setData(newItems: List<PeopleResult>) {
    adapterItems.addAll(newItems)
  }

  fun setOnPeopleClickListener(peopleClick:(Int)->Unit){
    this.peopleClick=peopleClick
  }


  override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
    holder.bind(adapterItems[position])
  }
}
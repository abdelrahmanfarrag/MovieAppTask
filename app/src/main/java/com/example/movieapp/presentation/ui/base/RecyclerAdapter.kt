package com.example.movieapp.presentation.ui.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.presentation.ui.base.RecyclerAdapter.BaseViewHolder
import com.example.movieapp.utility.extensions.inflate

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 */
abstract class RecyclerAdapter<T, H : BaseViewHolder<T>> : RecyclerView.Adapter<H>() {

  protected var adapterItems = mutableListOf<T>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    instantiateViewHolder(parent.inflate(getLayoutId(viewType)), viewType)

  fun clear() {
    adapterItems.clear()
    notifyDataSetChanged()
  }

  override fun getItemCount() = adapterItems.size

  protected open fun getItemAtPosition(position: Int) = adapterItems[position]

  @LayoutRes protected abstract fun getLayoutId(viewType: Int): Int

  protected abstract fun instantiateViewHolder(itemView: View, viewType: Int): H

  abstract fun setData(newItems: List<T>)

  fun addItems(newItems: List<T>){
    adapterItems.addAll(newItems)
    notifyItemRangeChanged(adapterItems.size, adapterItems.size + newItems.size)

  }



  abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
  }
}
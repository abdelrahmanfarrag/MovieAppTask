package com.example.movieapp.presentation.ui.base.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import com.example.movieapp.R
import com.example.movieapp.data.model.Images
import com.example.movieapp.utility.Constants.ARGS.KEY.IMAGES
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Authored by Abdelrahman Ahmed on 20 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
class PeopleDetailBottomSheetDialog : BottomSheetDialogFragment() {

  var dialogView: View? = null

  companion object {
    fun newInstance(images: Images): PeopleDetailBottomSheetDialog {
      val peopleDetailBottomSheetDialog = PeopleDetailBottomSheetDialog()
      val peopleDetailsBundle = Bundle()
      peopleDetailsBundle.putParcelable(IMAGES, images)
      peopleDetailBottomSheetDialog.arguments = peopleDetailsBundle
      return peopleDetailBottomSheetDialog
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    view.viewTreeObserver.addOnGlobalLayoutListener(object :
      ViewTreeObserver.OnGlobalLayoutListener {
      override fun onGlobalLayout() {
        view.viewTreeObserver.removeOnGlobalLayoutListener(this)
        val dialog = dialog as BottomSheetDialog
        val bottomSheet = dialog.findViewById<View>(
          com.google.android.material.R.id.design_bottom_sheet
        ) as FrameLayout?
        val behavior = BottomSheetBehavior.from(bottomSheet!!)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

        val newHeight = activity?.window?.decorView?.measuredHeight
        val viewGroupLayoutParams = bottomSheet.layoutParams
        viewGroupLayoutParams.height = (newHeight!!)
        bottomSheet.layoutParams = viewGroupLayoutParams
      }
    })
    dialogView = view

    val image = arguments?.getParcelable<Images>(IMAGES)
    Log.d("imagesPrint", image?.imagePath?.size.toString())

  }

  override fun getTheme() = R.style.AppBottomSheetDialogTheme

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.people_detail_bottom_sheet, container, false)
  }

  override fun onDestroyView() {
    dialogView?.viewTreeObserver?.addOnGlobalLayoutListener(null)
    super.onDestroyView()
  }

}
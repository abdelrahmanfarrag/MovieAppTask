package com.example.movieapp.presentation.ui.base.main.details

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.movieapp.R
import com.example.movieapp.data.model.Images
import com.example.movieapp.presentation.ui.base.main.details.adapter.ImagesAdapter
import com.example.movieapp.utility.Constants.ARGS.KEY.IMAGES
import com.example.movieapp.utility.downloadFile
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.people_detail_bottom_sheet.peopleDetailRecyclerView

/**
 * Authored by Abdelrahman Ahmed on 20 Nov, 2020.
 */
class PeopleDetailBottomSheetDialog : BottomSheetDialogFragment() {

  var dialogView: View? = null
  private lateinit var rx: RxPermissions


  companion object {
    fun newInstance(images: Images): PeopleDetailBottomSheetDialog {
      val peopleDetailBottomSheetDialog =
        PeopleDetailBottomSheetDialog()
      val peopleDetailsBundle = Bundle()
      peopleDetailsBundle.putParcelable(IMAGES, images)
      peopleDetailBottomSheetDialog.arguments = peopleDetailsBundle
      return peopleDetailBottomSheetDialog
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    rx = RxPermissions(this)
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
        bottomSheet.layoutParams =
          CoordinatorLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
          )
      }
    })
    dialogView = view

    val image = arguments?.getParcelable<Images>(IMAGES)
    Log.d("imagesPrint", image?.imagePath?.size.toString())
    val adapter = ImagesAdapter()
    adapter.setOnDownloadClick { url ->
      onDownloadRequestClickListener(url)
    }
    adapter.setData(image?.imagePath!!)
    peopleDetailRecyclerView.adapter = adapter


  }

  override fun getTheme() = R.style.AppBottomSheetDialogTheme

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.people_detail_bottom_sheet, container, false)
  }

  @SuppressLint("CheckResult")
  private fun onDownloadRequestClickListener(url: String) {
    rx.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe { granted ->
      if (granted) {
        if (::rx.isInitialized)
          downloadFile(url, this.context!!)
        else
          Toast.makeText(this.context!!, getString(R.string.error), Toast.LENGTH_LONG).show()

      } else {
        Toast.makeText(this.context!!, getString(R.string.apply_permission), Toast.LENGTH_LONG).show()
      }
    }
  }

  override fun onDestroyView() {
    dialogView?.viewTreeObserver?.addOnGlobalLayoutListener(null)
    super.onDestroyView()
  }

}
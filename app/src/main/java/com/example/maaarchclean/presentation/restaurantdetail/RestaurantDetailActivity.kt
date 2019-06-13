package com.example.maaarchclean.presentation.restaurantdetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.example.maaarchclean.presentation.BaseActivity
import com.example.maaarchclean.R

import com.example.maaarchclean.ext.addTo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RestaurantDetailActivity : BaseActivity() {

  @Inject lateinit var viewModel: RestaurantDetailViewModel
  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding: com.example.maaarchclean.databinding.ActivityRestaurantDetailBinding = DataBindingUtil.setContentView(this,
        R.layout.activity_restaurant_detail)

    screenComponent.inject(this)

    binding.viewModel = viewModel
    viewModel.bound(intent.getIntExtra(EXTRA_RESTAURANT_ID, -1))
  }

  override fun onResume() {
    super.onResume()
    viewModel.showErrorGettingRestaurantDetails.observe()
        .subscribe {
          AlertDialog.Builder(this)
              .setTitle("error")
              .setMessage("massage")
              .setNeutralButton("Ok", { dialog, _ -> dialog.dismiss() })
        }.addTo(disposables)
  }

  override fun onPause() {
    disposables.clear()
    super.onPause()
  }

  override fun onDestroy() {
    viewModel.unbound()
    super.onDestroy()
  }

  companion object {
    const val EXTRA_RESTAURANT_ID = "restaurant_id"
  }
}

package com.example.maaarchclean.presentation.restaurantdetail

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.os.Bundle
import com.example.maaarchclean.rx.StickyAction
import com.example.domain.GetRestaurantUseCase
import com.example.maaarchclean.ext.addTo
import com.example.maaarchclean.presentation.imagedetail.ImageDetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RestaurantDetailViewModel @Inject constructor(
  private val getRestaurantUseCase: GetRestaurantUseCase,
  private val restaurantDetailRouter: RestaurantDetailRouter
) {

  private val disposables = CompositeDisposable()
  val progressVisible = ObservableBoolean()
  val showErrorGettingRestaurantDetails = StickyAction<Boolean>()
  var restaurantName = ObservableField<String>()
  var restaurantDescription = ObservableField<String>()
  var restaurantStatus = ObservableField<String>()
  var restaurantImage = ObservableField<String>()

  // Called onCreate. Retrieves the restaurant details
  fun bound(id: Int) {
    getRestaurantUseCase.execute(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { handleGetRestaurantListResult(it) }
        .addTo(disposables)
  }

  // Called onDestroy. Clean up method
  fun unbound() {
    disposables.clear()
  }

  // Handles Result from getRestaurantUseCase
  private fun handleGetRestaurantListResult(result: GetRestaurantUseCase.Result) {
    progressVisible.set(result == GetRestaurantUseCase.Result.Loading)
    when (result) {
      is
      GetRestaurantUseCase.Result.Success -> {
        restaurantName.set(result.restaurant.name)
        restaurantDescription.set(result.restaurant.description)
        restaurantStatus.set(result.restaurant.status)
        restaurantImage.set(result.restaurant.coverImgUrl)
      }
      is GetRestaurantUseCase.Result.Failure -> {
        showErrorGettingRestaurantDetails.trigger(true)
      }
    }
  }

  fun onImageClicked() {
    restaurantDetailRouter.navigate(RestaurantDetailRouter.Route.IMAGE_DETAIL, Bundle().apply {
      putString(ImageDetailActivity.EXTRA_URL, restaurantImage.get())
    })
  }
}

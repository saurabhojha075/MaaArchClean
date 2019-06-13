package com.example.maaarchclean.di.screen

import com.example.maaarchclean.di.scope.PerScreen
import com.example.maaarchclean.presentation.imagedetail.ImageDetailActivity
import com.example.maaarchclean.presentation.main.MainActivity
import com.example.maaarchclean.presentation.restaurantdetail.RestaurantDetailActivity
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [ScreenModule::class])
interface ScreenComponent {

  fun inject(mainActivity: MainActivity)

  fun inject(restaurantDetailActivity: RestaurantDetailActivity)

  fun inject(imageDetailActivity: ImageDetailActivity)
}

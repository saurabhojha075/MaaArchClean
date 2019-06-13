package com.example.maaarchclean.di.screen

import com.example.maaarchclean.di.scope.PerScreen
import com.example.maaarchclean.presentation.BaseActivity
import com.example.maaarchclean.presentation.main.MainRouter
import com.example.maaarchclean.presentation.restaurantdetail.RestaurantDetailRouter
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

@Module
class ScreenModule(private val activity: BaseActivity) {

  @PerScreen
  @Provides
  fun providesActivity(): BaseActivity {
    return activity
  }

  @PerScreen
  @Provides
  fun providesMainRouter(): MainRouter {
    return MainRouter(WeakReference(activity))
  }

  @PerScreen
  @Provides
  fun providesRestaurantDetailRouter(): RestaurantDetailRouter {
    return RestaurantDetailRouter(WeakReference(activity))
  }
}

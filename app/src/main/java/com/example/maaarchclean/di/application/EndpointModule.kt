package com.example.maaarchclean.di.application


import com.example.data.RestaurantEndpoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class EndpointModule {

  @Provides
  @Singleton
  fun provideRestaurantEndpoint(retrofit: Retrofit): RestaurantEndpoint {
    return retrofit
        .create(RestaurantEndpoint::class.java)
  }
}

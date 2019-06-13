package com.example.maaarchclean.di.application
import com.example.data.RestaurantApi
import com.example.data.RestaurantRepositoryImpl
import com.example.data.mapper.RestaurantMapper
import com.example.domain.RestaurantRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

  @Provides
  @Singleton
  fun provideRestaurantRepository(restaurantApi: RestaurantApi, restaurantMapper: RestaurantMapper): RestaurantRepository {
    return RestaurantRepositoryImpl(restaurantApi, restaurantMapper)
  }

}

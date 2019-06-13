package com.example.data

import com.example.data.mapper.RestaurantMapper
import com.example.domain.RestaurantRepository
import com.example.domain.model.Restaurant
import io.reactivex.Single

class RestaurantRepositoryImpl(
    private val restaurantApi: RestaurantApi,
    private val restaurantMapper: RestaurantMapper
):RestaurantRepository {



    override fun getRestaurantList(lat: Double, lng: Double, offset: Int, limit: Int): Single<List<Restaurant>> {
        return restaurantApi.getRestaurantList(lat, lng, offset, limit)
            .map { restaurantMapper.map(it) }
    }

    override fun getRestaurant(id: Int): Single<Restaurant> {
        return restaurantApi.getRestaurant(id)
            .map { restaurantMapper.map(it) }
    }
}
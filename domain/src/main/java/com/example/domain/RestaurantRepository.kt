package com.example.domain

import com.example.domain.model.Restaurant
import io.reactivex.Single

interface RestaurantRepository {

    fun getRestaurantList(lat: Double, lng: Double, offset: Int, limit: Int): Single<List<Restaurant>>

    fun getRestaurant(id: Int): Single<Restaurant>
}
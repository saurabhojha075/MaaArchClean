package com.example.domain
import com.example.domain.model.Restaurant
import io.reactivex.Observable
import javax.inject.Inject

/**
 * GetRestaurantUseCase
 *
 * Used to retrieve the restaurant from the id provided and return it in a Result object
 * Additional business logic related to the restaurant can be performed here
 * @param restaurantRepository repository to get the restaurant from
 */
class GetRestaurantUseCase @Inject constructor(private val restaurantRepository: RestaurantRepository) {

  sealed class Result {
    object Loading : Result()
    data class Success(val restaurant: Restaurant) : Result()
    data class Failure(val throwable: Throwable) : Result()
  }

  fun execute(id: Int): Observable<Result> {
    return restaurantRepository.getRestaurant(id)
        .toObservable()
        .map { Result.Success(it) as Result }
        .onErrorReturn { Result.Failure(it) }
        .startWith(Result.Loading)
  }
}

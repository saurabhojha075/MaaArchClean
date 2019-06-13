package com.example.maaarchclean.presentation.main

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.maaarchclean.presentation.BaseActivity
import com.example.maaarchclean.R
import com.example.maaarchclean.ext.addTo
import com.example.maaarchclean.presentation.adapter.RestaurantListAdapter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity : BaseActivity() {

  @Inject lateinit var viewModel: MainViewModel
  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding: com.example.maaarchclean.databinding.ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    screenComponent.inject(this)

    binding.viewModel = viewModel
    viewModel.bound()
  }

  // onResume we need to subscribe to our viewModel actions
  override fun onResume() {
    super.onResume()
    viewModel.showErrorGettingRestaurants.observe()
        .subscribe {
          AlertDialog.Builder(this)
              .setTitle("error")
              .setMessage("error1")
              .setNeutralButton("Ok") { dialog, _ -> dialog.dismiss() }
        }.addTo(disposables)
  }

  // onPause we need to unsubscribe from viewModel actions since the view is not backgrounded
  override fun onPause() {
    disposables.clear()
    super.onPause()
  }

  override fun onDestroy() {
    viewModel.unbound()
    super.onDestroy()
  }

  companion object {
    /**
     * bindList uses Databinding to initialize the recyclerView using an ObservableList from the MainViewModel
     * this is referenced in activity_main.xml as 'app:adapter={@viewModel}'
     */
    @JvmStatic
    @BindingAdapter("adapter")
    fun bindList(recyclerView: RecyclerView, viewModel: MainViewModel) {
      val adapter = RestaurantListAdapter(viewModel.restaurantList)
      adapter.onItemClickListener = { viewModel.onRestaurantClicked(it) }
      adapter.onImageClickedListener = { viewModel.onRestaurantImageClicked(it) }
      recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
      recyclerView.layoutManager = GridLayoutManager(recyclerView.context,4)
      recyclerView.adapter = adapter
    }
  }
}

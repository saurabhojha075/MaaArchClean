package com.example.maaarchclean.di.application

import com.example.maaarchclean.BaseApplication
import com.example.maaarchclean.di.screen.ScreenComponent
import com.example.maaarchclean.di.screen.ScreenModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RepositoryModule::class, EndpointModule::class])
interface ApplicationComponent {

  fun inject(activity: BaseApplication)

  fun plus(screenModule: ScreenModule): ScreenComponent
}

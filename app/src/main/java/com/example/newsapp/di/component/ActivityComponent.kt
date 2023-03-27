package com.example.newsapp.di.component

import com.example.newsapp.ActivityScope
import com.example.newsapp.ui.topheadline.TopHeadlineActivity
import com.example.newsapp.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)

}
package com.abdhilabs.coreandroid.di

import android.app.Activity

fun Activity.appDaggerComponent(): AppComponent {
    return (this.application as DependencyProvider).appComponent
}
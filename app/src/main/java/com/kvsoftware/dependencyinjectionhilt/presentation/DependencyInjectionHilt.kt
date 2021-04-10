package com.kvsoftware.dependencyinjectionhilt.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * To use Hilt, Set Application class to be annotated with @HiltAndroidApp.
 *
 * @HiltAndroidApp triggers Hilt's code generation, including a base class for your application
 * that serves as the application-level dependency container.
 * */
@HiltAndroidApp
class DependencyInjectionHilt : Application()
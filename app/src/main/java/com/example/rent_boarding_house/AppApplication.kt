package com.example.rent_boarding_house

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication : Application() {
	override fun onCreate() {
		super.onCreate()

		// Initialize Firebase
		FirebaseApp.initializeApp(this)
	}
}
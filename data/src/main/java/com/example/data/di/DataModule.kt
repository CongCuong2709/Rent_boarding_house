package com.example.data.di

import com.example.data.features.createPost.CreatePostRepositoryImpl
import com.example.data.features.detail.DetailRepositoryImpl
import com.example.data.features.home.HomeRepositoryImpl
import com.example.data.features.managePosts.ManagePostRepositoryImpl
import com.example.data.features.notifications.NotificationRepositoryImpl
import com.example.data.features.search.SearchRepositoryImpl
import com.example.data.features.signin.FirebaseAuthRepositoryImpl
import com.example.domain.features.createPost.CreatePostRepository
import com.example.domain.features.detail.DetailRepository
import com.example.domain.features.home.HomeRepository
import com.example.domain.features.managePosts.ManagePostRepository
import com.example.domain.features.notifications.NotificationRepository
import com.example.domain.features.search.SearchRepository
import com.example.domain.features.signin.SignInRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

	@Binds
	@Singleton
	abstract fun bindAuthRepository(impl : FirebaseAuthRepositoryImpl) : SignInRepository

	@Binds
	@Singleton
	abstract fun bindHomeRepository(impl : HomeRepositoryImpl) : HomeRepository

	@Binds
	@Singleton
	abstract fun bindDetailRepository(impl : DetailRepositoryImpl) : DetailRepository

	@Binds
	@Singleton
	abstract fun bindCreatePostRepository(impl : CreatePostRepositoryImpl) : CreatePostRepository

	@Binds
	@Singleton
	abstract fun bindSearchRepository(impl : SearchRepositoryImpl) : SearchRepository

	@Binds
	@Singleton
	abstract fun bindManagePostRepository(impl : ManagePostRepositoryImpl) : ManagePostRepository

//	@Binds
//	@Singleton
//	abstract fun bindNotificationRepository(impl : NotificationRepositoryImpl) : NotificationRepository

}
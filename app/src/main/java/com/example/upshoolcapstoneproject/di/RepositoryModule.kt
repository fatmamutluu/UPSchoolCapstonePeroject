package com.example.upshoolcapstoneproject.di

import com.example.upshoolcapstoneproject.data.ProductService
import com.example.upshoolcapstoneproject.data.model.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductsRepository(productService: ProductService) = ProductRepository(productService)
}
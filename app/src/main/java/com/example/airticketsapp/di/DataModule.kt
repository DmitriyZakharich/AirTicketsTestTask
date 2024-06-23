package com.example.airticketsapp.di

import android.content.Context
import com.example.air_tickets.data.local.CacheFormCacheFormDataStoreImpl
import com.example.air_tickets.data.repositories.interfaces.NetworkLoader
import com.example.air_tickets.data.network.NetworkLoaderImpl
import com.example.air_tickets.data.repositories.MainScreenOfferRepositoryImpl
import com.example.air_tickets.data.repositories.PlaceDepartureRepositoryImpl
import com.example.air_tickets.data.network.RetrofitHelper
import com.example.air_tickets.data.repositories.TicketsRepositoryImpl
import com.example.air_tickets.data.repositories.interfaces.CacheFormDataStore
import com.example.air_tickets.domain.repositories.MainScreenOffersRepository
import com.example.air_tickets.domain.repositories.PlaceDepartureRepository
import com.example.air_tickets.domain.repositories.TicketsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRetrofitHelper(): RetrofitHelper =
        RetrofitHelper()

    @Provides
    @Singleton
    fun provideOffersRepository(
        networkLoader: NetworkLoader
    ): MainScreenOffersRepository =
        MainScreenOfferRepositoryImpl(networkLoader)

    @Provides
    @Singleton
    fun provideNetworkLoader(
        retrofitHelper: RetrofitHelper
    ): NetworkLoader =
        NetworkLoaderImpl(retrofitHelper)

    @Provides
    @Singleton
    fun provideRepository(
        cacheFormDataStore: CacheFormDataStore
    ): PlaceDepartureRepository =
        PlaceDepartureRepositoryImpl(cacheFormDataStore)

    @Provides
    @Singleton
    fun provideCacheFormDataStore(
        @ApplicationContext appContext: Context
    ): CacheFormDataStore =
        CacheFormCacheFormDataStoreImpl(appContext)

    @Provides
    @Singleton
    fun provideTicketsRepository(
        networkLoader: NetworkLoader
    ): TicketsRepository =
        TicketsRepositoryImpl(networkLoader)

}
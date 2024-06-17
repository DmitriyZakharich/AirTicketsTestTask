package com.example.airticketsapp.di

import com.example.air_tickets.data.CacheFormDataStore
import com.example.air_tickets.data.NetworkLoader
import com.example.air_tickets.data.NetworkLoaderImpl
import com.example.air_tickets.data.OfferRepositoryImpl
import com.example.air_tickets.data.RetrofitHelper
import com.example.air_tickets.domain.OffersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
//@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRetrofitHelper(): RetrofitHelper =
        RetrofitHelper()

    @Provides
    @Singleton
    fun provideOffersRepository(
        networkLoader: NetworkLoader
    ): OffersRepository =
        OfferRepositoryImpl(networkLoader)

    @Provides
    @Singleton
    fun provideNetworkLoader(
        retrofitHelper: RetrofitHelper
    ): NetworkLoader =
        NetworkLoaderImpl(retrofitHelper)

//    @Provides
//    @Singleton
//    fun provideCacheFormDataStore(
//    ): CacheFormDataStore =
//        CacheFormDataStore()



}
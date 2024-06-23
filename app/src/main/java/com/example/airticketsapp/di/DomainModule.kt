package com.example.airticketsapp.di

import com.example.air_tickets.domain.use_cases.GetOffersUseCase
import com.example.air_tickets.domain.use_cases.GetPlaceDepartureUseCase
import com.example.air_tickets.domain.repositories.MainScreenOffersRepository
import com.example.air_tickets.domain.repositories.PlaceDepartureRepository
import com.example.air_tickets.domain.repositories.TicketsRepository
import com.example.air_tickets.domain.use_cases.FormattingDateNumberSeatsUseCase
import com.example.air_tickets.domain.use_cases.GetCurrentDataUseCase
import com.example.air_tickets.domain.use_cases.GetFullListTicketsUseCase
import com.example.air_tickets.domain.use_cases.GetShortListTicketsUseCase
import com.example.air_tickets.domain.use_cases.RouteFormattingUseCase
import com.example.air_tickets.domain.use_cases.SavePlaceDepartureUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun providesGetOffersUseCase(mainScreenOffersRepository: MainScreenOffersRepository): GetOffersUseCase =
        GetOffersUseCase(mainScreenOffersRepository)

    @Provides
    fun providesGetShortListTicketsUseCase(ticketsRepository: TicketsRepository): GetShortListTicketsUseCase =
        GetShortListTicketsUseCase(ticketsRepository)

    @Provides
    fun providesGetFullListTicketsUseCase(ticketsRepository: TicketsRepository): GetFullListTicketsUseCase =
        GetFullListTicketsUseCase(ticketsRepository)

    @Provides
    fun providesGetPlaceDepartureUseCase(placeDepartureRepository: PlaceDepartureRepository): GetPlaceDepartureUseCase =
        GetPlaceDepartureUseCase(placeDepartureRepository)

    @Provides
    fun providesSavePlaceDepartureUseCase(placeDepartureRepository: PlaceDepartureRepository): SavePlaceDepartureUseCase =
        SavePlaceDepartureUseCase(placeDepartureRepository)

    @Provides
    fun providesGetCurrentDataUseCase(): GetCurrentDataUseCase = GetCurrentDataUseCase()

    @Provides
    fun providesDateNumberSeatsUseCase(): FormattingDateNumberSeatsUseCase = FormattingDateNumberSeatsUseCase()

    @Provides
    fun providesRouteFormattingUseCase(): RouteFormattingUseCase = RouteFormattingUseCase()
}
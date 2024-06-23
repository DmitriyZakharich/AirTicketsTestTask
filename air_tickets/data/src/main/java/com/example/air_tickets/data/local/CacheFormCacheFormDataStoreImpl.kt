package com.example.air_tickets.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.air_tickets.data.repositories.interfaces.CacheFormDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATA_STORE_NAME = "cache_place_departure_store"
private const val PLACE_DEPARTURE = "place_departure"

class CacheFormCacheFormDataStoreImpl(private val context: Context) :
    CacheFormDataStore {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            DATA_STORE_NAME
        )
    }

    private val key: Preferences.Key<String> = stringPreferencesKey(PLACE_DEPARTURE)

    override suspend fun saveData(string: String) {
        context.dataStore.edit { preferences ->
            preferences[key] = string
        }
    }

    override fun getData(): Flow<String> = context.dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(PLACE_DEPARTURE)] ?: ""
    }
}
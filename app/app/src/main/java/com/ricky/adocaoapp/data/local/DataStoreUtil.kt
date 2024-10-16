package com.ricky.adocaoapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.ricky.adocaoapp.domain.models.Token
import com.ricky.adocaoapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreUtil(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.SETTINGS)

        val THEME_KEY = booleanPreferencesKey(Constants.IS_DARK_MODE)
        val TOKEN = stringPreferencesKey(Constants.USER_TOKEN)
        val LAT = doublePreferencesKey(Constants.USER_LAT)
        val LONG = doublePreferencesKey(Constants.USER_LONG)
    }

    suspend fun saveTheme(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDark
        }
    }

    suspend fun saveToken(token: Token) {
        val json = Gson().toJson(token)
        context.dataStore.edit { p -> p[TOKEN] = json }
    }

    suspend fun saveLat(lat: Double) {
        context.dataStore.edit { preferences ->
            preferences[LAT] = lat
        }
    }

    suspend fun saveLong(long: Double) {
        context.dataStore.edit { preferences ->
            preferences[LONG] = long
        }
    }

    fun getTheme(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    fun getToken(): Flow<Token?> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[TOKEN] ?: ""
            if (json.isNotEmpty()) {
                Gson().fromJson(json, Token::class.java)
            } else {
                null
            }
        }
    }

    fun getLat(): Flow<Double> {
        return context.dataStore.data.map { preferences ->
            preferences[LAT] ?: 0.0
        }
    }

    fun getLong(): Flow<Double> {
        return context.dataStore.data.map { preferences ->
            preferences[LONG] ?: 0.0
        }
    }

}
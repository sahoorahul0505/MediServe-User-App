package com.kodebug.mediserveuser.data.datastore



import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kodebug.mediserveuser.data.datastore.PreferenceKeys.USER_ID_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class DataStoreManager @Inject constructor(@ApplicationContext private val context : Context) {

    val userPreferences: DataStore<Preferences>
        get() = context.dataStore

    val userIdFlow : Flow<String?> = userPreferences.data
        .map { prefs -> prefs[USER_ID_KEY] }

    suspend fun saveUserId(userId : String){
        userPreferences.edit { prefs ->
            prefs[USER_ID_KEY] = userId
        }
    }

    suspend fun clearUserId(){
        userPreferences.edit { prefs ->
            prefs.remove(USER_ID_KEY)
        }
    }

    suspend fun clearUserData(){
        userPreferences.edit { prefs ->
            prefs.clear() // Clears all stored keys (e.g. user_id)
        }
    }

}

object PreferenceKeys {
    val USER_ID_KEY = stringPreferencesKey("user_id")
}
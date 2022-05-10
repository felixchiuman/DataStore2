package com.felix.datastore2

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow

class DataStoreManager(private val context:Context) {

    suspend fun setPass(passValue : String){
        context.passDataStore.edit{preferences ->
            preferences [PASS_KEY] = passValue
        }
    }

    fun getPass(): kotlinx.coroutines.flow.Flow<String>{
        return context.passDataStore.data.map { preferences ->
            preferences [PASS_KEY]?:""
        }
    }

    companion object{
        private const val DATASTORE_NAME = "pass_preferences"

        private val PASS_KEY = stringPreferencesKey("pass_key")

        private val Context.passDataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}
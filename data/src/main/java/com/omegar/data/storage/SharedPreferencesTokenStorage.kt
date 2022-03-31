package com.omegar.data.storage

import android.content.SharedPreferences
import androidx.core.content.edit
import com.omegar.domain.storage.TokenStorage

class SharedPreferencesTokenStorage(private val sharedPreferences: SharedPreferences) : TokenStorage {

    companion object {

        private const val KEY_TOKEN = "token"
    }

    override var token: String?
        get() = sharedPreferences.getString(KEY_TOKEN, null)
        set(value) {
            sharedPreferences.edit {
                putString(KEY_TOKEN, value)
            }
        }
}
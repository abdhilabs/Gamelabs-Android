package com.abdhilabs.coreandroid.utils.preference

import android.content.SharedPreferences
import javax.inject.Inject

class EncryptedPreference @Inject constructor(private val sp: SharedPreferences) {

    private val spe: SharedPreferences.Editor by lazy {
        sp.edit()
    }

    fun clear() {
        sp.edit().clear().apply()
    }

    var secureToken: String?
        get() = sp.getString(SP_TOKEN, "")
        set(value) = spe.putString(SP_TOKEN, value).apply()

    var tokenFcm: String?
        get() = sp.getString(SP_TOKEN_FCM, "")
        set(value) = spe.putString(SP_TOKEN_FCM, value).apply()

    companion object {
        const val SECURE_PREF_NAME = "id.co.hepta.secure_preference"
        const val SP_TOKEN = "pref_token"
        const val SP_TOKEN_FCM = "pref_token_fcm"
    }
}
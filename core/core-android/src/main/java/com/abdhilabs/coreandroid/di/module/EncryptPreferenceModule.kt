package com.abdhilabs.coreandroid.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.abdhilabs.coreandroid.utils.preference.EncryptedPreference
import dagger.Module
import dagger.Provides

@Module
class EncryptPreferenceModule {

    @Provides
    fun providesEncryptPreference(applicationContext: Context, masterKey: MasterKey): SharedPreferences =
        EncryptedSharedPreferences.create(
            applicationContext,
            EncryptedPreference.SECURE_PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    @Provides
    fun providesEncryptEditor(sp: SharedPreferences): SharedPreferences.Editor = sp.edit()

    @Provides
    fun provideMasterKey(applicationContext: Context) =
        MasterKey.Builder(applicationContext, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

}
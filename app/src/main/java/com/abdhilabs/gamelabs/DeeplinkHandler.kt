package com.abdhilabs.gamelabs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abdhilabs.detail.GamelabsDetailDeepLinkModule
import com.abdhilabs.detail.GamelabsDetailDeepLinkModuleLoader
import com.airbnb.deeplinkdispatch.BaseDeepLinkDelegate
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.airbnb.deeplinkdispatch.Parser

@DeepLinkHandler(
    AppDeeplinkModule::class,
    GamelabsDetailDeepLinkModule::class
)
class DeeplinkHandlerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseDeepLinkDelegate(
            listOfNotNull(
                AppDeeplinkModuleLoader(),
                GamelabsDetailDeepLinkModuleLoader(),
                loadDeepLinkLoader(DYNAMIC_FEATURE_FAVORITE_MODULE)
            )
        ).dispatchFrom(this)
        finish()
    }

    //use reflection to support dynamic module deeplink
    private fun loadDeepLinkLoader(loadClassName: String): Parser? {
        return try {
            Class.forName(loadClassName).newInstance() as Parser
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        private const val DYNAMIC_FEATURE_FAVORITE_MODULE =
            "com.abdhilabs.gamelabs.favourite.GamelabsFavoriteDeeplinkModuleLoader"
    }
}
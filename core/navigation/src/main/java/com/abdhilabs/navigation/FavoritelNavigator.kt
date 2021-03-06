package com.abdhilabs.navigation

import android.content.Intent
import android.net.Uri

private const val SCHEME = "gamelabs://"
private const val FAVORITE = "favorite"

class FavoriteNavigator {
    companion object {
        @JvmStatic
        fun getFavoritePageIntent(): Intent {
            return Intent(Intent.ACTION_VIEW, Uri.parse(SCHEME + FAVORITE))
        }
    }
}
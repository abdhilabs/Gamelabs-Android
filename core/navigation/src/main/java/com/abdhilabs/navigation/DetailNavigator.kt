package com.abdhilabs.navigation

import android.content.Intent
import android.net.Uri

private const val SCHEME = "gamelabs://detail/"

class DetailNavigator {
    companion object {
        @JvmStatic
        fun getDetailPageIntent(movieId: Int): Intent {
            return Intent(Intent.ACTION_VIEW, Uri.parse(SCHEME + movieId))
        }
    }
}
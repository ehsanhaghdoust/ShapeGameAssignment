package ehsan.haghdoust.shapegameassignment.utility

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import ehsan.haghdoust.shapegameassignment.utility.ApplicationClass

object Utils {

    fun isNetworkConnected(context: Context): Boolean {
        //1
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //2
        val activeNetwork = connectivityManager.activeNetwork
        //3
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        //4
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    fun isUrlInFormat(url: String): Boolean {
        val regex = Regex("^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?(\\/[a-z0-9])*(\\/?|(\\?[a-z0-9]=[a-z0-9](&[a-z0-9]=[a-z0-9]*)?))\$")
        return regex.matches(url)
    }

    fun toUrl(url: String): Uri {
        var uri: Uri = if (url.startsWith("https://") || url.startsWith("https://")) {
            Uri.parse(url)
        } else if (url.startsWith("www.")) {
            Uri.parse(url)
        } else {
            Uri.parse("https://www.${url}")
        }
        return uri
    }

    fun copyToClipboard(str: String) {
        var myClipboard = ApplicationClass.applicationContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var myClip: ClipData = ClipData.newPlainText("", str)
        myClipboard.setPrimaryClip(myClip)
    }

    fun getBooleanFromSharedPrefs(key: String): Boolean {
        return ApplicationClass.getSharedPreferences().getBoolean(key, false)
    }

    fun saveBooleanToSharedPrefs(key: String, value: Boolean) {
        ApplicationClass.getSharedPreferences().edit().putBoolean(key, value).apply()
    }
}
package com.yi.common.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import com.yi.common.BuildConfig
import com.yi.common.base.BaseApplication
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException

/**
 * @author Yi
 * @date 2020/5/19
 */
object MobileUtil {
    val OS = "android"

    fun getAndroidId() = Settings.Secure.getString(
        BaseApplication.getInstance().contentResolver,
        Settings.Secure.ANDROID_ID
    )

    @SuppressLint("MissingPermission")
    fun getIMEI(): String? {
        var imei: String? = null
        try {
            var manager = BaseApplication.getInstance()
                .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            imei = manager.deviceId
        } catch (e: Exception) {
        }
        return imei
    }

    fun getAndroidVersion() = Build.VERSION.SDK_INT

    fun getIp(): String? {
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddress =
                    intf.inetAddresses
                while (enumIpAddress.hasMoreElements()) {
                    val inetAddress = enumIpAddress.nextElement()
                    if (!inetAddress.isLoopbackAddress
                        && inetAddress is Inet4Address
                    ) {
                        return inetAddress.getHostAddress()
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }
        return null
    }

    fun getNetStatus(): String? {
        return try {
            val connectivityManager = BaseApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetInfo = connectivityManager.activeNetworkInfo
            if (activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI) {
                "WIFI"
            } else if (activeNetInfo != null
                && (activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_EDGE || activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_GPRS || activeNetInfo
                    .subtype == TelephonyManager.NETWORK_TYPE_CDMA)
            ) {
                "2G"
            } else if (activeNetInfo != null
                && (activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_UMTS || activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_EVDO_0 || activeNetInfo
                    .subtype == TelephonyManager.NETWORK_TYPE_EVDO_A || activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_HSDPA || activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_HSUPA || activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_HSPA || activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_EVDO_B || activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_EHRPD || activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_HSPAP)
            ) {
                "3G"
            } else if (activeNetInfo != null
                && activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_LTE
            ) {
                "4G"
            } else {
                "NONE"
            }
        } catch (e: Exception) {
            null
        }
    }

    fun getMetaData(key: String): String? {
        var context = BaseApplication.getInstance()
        var manager = context?.packageManager
        var applicationInfo =
            manager?.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
        var result = applicationInfo?.metaData?.getString(key)
        return result
    }

    fun getVersionNam(): String = BuildConfig.VERSION_NAME

    fun getVersionCode(): Int = BuildConfig.VERSION_CODE
}
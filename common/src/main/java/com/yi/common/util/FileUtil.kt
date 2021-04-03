package com.yi.common.util

import android.os.Environment
import com.yi.common.base.CommonBaseApplication
import java.io.File
import java.math.BigDecimal

/**
 * @author Yi
 * @date 2020/5/15
 */
object FileUtil {
    /**
     * root = /storage/emulated/0/Android/data/[包名]/files/
     * 此路径写入文件在应用卸载后会自动删除
     */
    private fun getAppDir(): File? {
        var root = CommonBaseApplication.getInstance().getExternalFilesDir(null)
        return root
    }

    /**
     * 创建子目录
     */
    fun getDataDir(subDir: String): File {
        var dir = File(getAppDir(), subDir)
        dir.mkdirs()
        return dir
    }

    /**
     * 删除目录
     */
    fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory) {
            val children = dir.list() ?: return false
            for (i in children.indices) {
                val success =
                    deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
        }
        return dir?.delete() ?: true
    }

    /**
     * 获取应用缓存大小
     */
    fun getCacheSize(): String {
        var cacheSize = 0L
        cacheSize =
            getFolderSize(CommonBaseApplication.getInstance().cacheDir)
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            cacheSize += getFolderSize(
                CommonBaseApplication.getInstance().externalCacheDir
            )
        }
        return getFormatSize(cacheSize)
    }

    /**
     * 清除缓存
     */
    fun clearCache() {
        deleteDir(CommonBaseApplication.getInstance().cacheDir)
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            deleteDir(CommonBaseApplication.getInstance().externalCacheDir)
        }
    }

    private fun getFolderSize(file: File?): Long {
        var size = 0L
        var files: Array<File>? = file?.listFiles() ?: return size

        for (file in files!!) {
            size += if (file.isDirectory) {
                getFolderSize(file)
            } else {
                file.length()
            }
        }
        return size
    }

    private fun getFormatSize(size: Long): String {
        var kiloByte = size / 1024
        if (kiloByte < 1) {
            return "0K"
        }
        var megaByte = kiloByte / 1024
        if (megaByte < 1) {
            val megaResult = BigDecimal(kiloByte.toString()).setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "KB"
            return megaResult
        }
        var gigaByte = megaByte / 1024
        if (gigaByte < 1) {
            val gigaResult = BigDecimal(megaByte.toString()).setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "MB"
            return gigaResult
        }
        var teraByte = gigaByte / 1024
        if (teraByte < 1) {
            val teraResult = BigDecimal(gigaByte.toString()).setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "GB"
            return teraResult
        }
        val teraByteResult =
            BigDecimal(teraByte).setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "TB"
        return teraByteResult
    }


}
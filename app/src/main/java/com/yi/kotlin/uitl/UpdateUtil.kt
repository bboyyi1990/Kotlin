package com.yi.kotlin.uitl

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import com.liulishuo.okdownload.DownloadTask
import com.liulishuo.okdownload.core.cause.EndCause
import com.liulishuo.okdownload.core.cause.ResumeFailedCause
import com.liulishuo.okdownload.core.listener.DownloadListener1
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist
import com.yi.common.util.FileUtil
import com.yi.kotlin.BuildConfig
import com.yi.kotlin.base.BaseApplication
import java.io.File
import java.text.DecimalFormat

/**
 * @author Yi
 * @date 2020/5/18
 */
object UpdateUtil {

    fun checkUpdate(context: Context, needTip: Boolean = true) {
        //TODO update&&tip
        var needUpdate = true
        if (needUpdate && needTip) {

        } else if (!needUpdate && needTip) {

        }
    }

    fun download(url: String, version: String) {
        var apkDir = FileUtil.getDataDir("apk")
        var apkName = "xxx_${version}_${BuildConfig.FLAVOR}.apk"
        DownloadTask.Builder(url, apkDir).setFilename(apkName)
            .setMinIntervalMillisCallbackProcess(1000)
            .setPassIfAlreadyCompleted(false).build().enqueue(object : DownloadListener1() {
                override fun taskStart(task: DownloadTask, model: Listener1Assist.Listener1Model) {
                }

                override fun taskEnd(
                    task: DownloadTask,
                    cause: EndCause,
                    realCause: Exception?,
                    model: Listener1Assist.Listener1Model
                ) {
                    when (cause) {
                        EndCause.COMPLETED -> installApk(task.file?.path)
                        EndCause.ERROR -> realCause?.message
                        else -> {}
                    }
                }

                override fun progress(task: DownloadTask, currentOffset: Long, totalLength: Long) {
                    var progerss = (currentOffset / totalLength) * 100
                    var myformat = DecimalFormat("0.0")
                    var totalStr = myformat.format(totalLength / 1024 / 1024)
                    var currentStr = myformat.format(currentOffset / 1024 / 1024)
                }

                override fun connected(
                    task: DownloadTask,
                    blockCount: Int,
                    currentOffset: Long,
                    totalLength: Long
                ) {
                }

                override fun retry(task: DownloadTask, cause: ResumeFailedCause) {
                }
            })
    }

    private fun installApk(path: String?) {
        var file = File(path)
        if (!file.exists()) {
            return
        }
        var intent = Intent(Intent.ACTION_VIEW)
        var uri: Uri
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            uri = FileProvider.getUriForFile(
                BaseApplication.getInstance(),
                "${BuildConfig.APPLICATION_ID}.fileProvider",
                file
            )
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            uri = Uri.parse("file://$file")
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive")
        BaseApplication.getInstance().startActivity(intent)
    }
}
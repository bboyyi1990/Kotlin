package com.yi.kotlin.main

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.webkit.MimeTypeMap
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.snackbar.Snackbar
import com.gyf.immersionbar.OnKeyboardListener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.yi.common.http.BaseData
import com.yi.common.http.BaseResponse
import com.yi.common.util.LanguageUtils
import com.yi.common.util.Logger
import com.yi.common.util.setOnClicker
import com.yi.kotlin.action.api.ApiCallback
import com.yi.kotlin.action.user.CheckRegisteredAction
import com.yi.kotlin.alert.CommonSelectDialog
import com.yi.kotlin.base.BaseActivity
import com.yi.kotlin.base.Router
import com.yi.kotlin.data.LoginData
import com.yi.kotlin.databinding.ActivityMainBinding

/**
 * @author Yi
 * @date 2020/5/20
 */
@Route(path = "${Router.GROUP}MainActivity")
class MainActivity : BaseActivity() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    @JvmField
    @Autowired(name = "value")
    var value: String? = null

    @JvmField
    @Autowired(name = "digit")
    var digit: String? = null

    private val adapter by lazy {
        MainAdapter().apply {
            repeat(30) { addData(LoginData("title = $it")) }
        }
    }

    private val model by viewModels<MainViewModel>()

    private val path =
        "http://pre-newpay-chat.oss-ap-southeast-1.aliyuncs.com/d0401bd6518a441787b218e1016dc5d7.png?x-oss-process=image/resize,w_70,h_70"

    override fun onCreate() {
        initBar(true, keyboardListener)
        binding.titleTv.setOnClickListener(clickListener)
        binding.titleTv.setOnClicker(clickListener)
        binding.refreshLayout.setOnRefreshLoadMoreListener(refreshListener)
        binding.recyclerMain.layoutManager = LinearLayoutManager(this)
        binding.recyclerMain.adapter = adapter
        adapter.setOnItemChildClickListener { adapter, view, position -> }
        model.getData(0)
        binding.titleTv.text = getString(com.yi.common.R.string.title)
        binding.languageBtn.setOnClickListener {
            CommonSelectDialog().addOptions(
                arrayOf("中文", "LO", "English")
            ) { position ->
                when (position) {
                    0 -> LanguageUtils.changeLanguage(this@MainActivity, LanguageUtils.ZH)
                    1 -> LanguageUtils.changeLanguage(this@MainActivity, LanguageUtils.LO)
                    2 -> LanguageUtils.changeLanguage(this@MainActivity, LanguageUtils.EN)
                }
                finish()
                val intent = Intent(this@MainActivity, MainActivity::class.java)
                startActivity(intent)
            }.show(supportFragmentManager)
        }
        binding.titleTv.text = "FucK!!"
    }

    private var keyboardListener = OnKeyboardListener { isPopup, keyboardHeight ->
        Logger.e(TAG, "isPopup $isPopup , keyboard height $keyboardHeight")
    }

    private var refreshListener = object : OnRefreshLoadMoreListener {
        override fun onRefresh(refreshLayout: RefreshLayout) {
            model.getData(0)
        }

        override fun onLoadMore(refreshLayout: RefreshLayout) {
            model.getData(adapter.itemCount)
        }
    }

    private var clickListener = View.OnClickListener { view ->
//        openDeviceFileManager()
//        TestDialogFragment.getInstant().show(supportFragmentManager, "")
//        LoginAction("1", "12", object : ApiCallback<LoginData>() {
//            override fun onNext(t: BaseResponse<LoginData>) {
//            }
//        })
//
//        model.insertData()
//
//        User.getDao().queryAllLiveDate().observe(this) {
//            Logger.e("viewmodel ", "data observer ${it.size}")
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            User.getDao().queryAllObservable().subscribe {
//                Logger.e("viewmodel ", "queryAllObservable ${it.size}")
//            }
//        }

        Snackbar.make(view,"this is snackBar",Snackbar.LENGTH_SHORT).show()

        CheckRegisteredAction().enqueue("11111111111", "+86", object : ApiCallback<BaseData>() {
            override fun onNext(t: BaseResponse<BaseData>) {
                var data = t.data
                Logger.e(TAG, "data is $data")
            }
        })
    }

    private fun openDeviceFileManager() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.data?.let {
            val type = contentResolver.getType(it)
            val extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(type)
            Logger.e(TAG, "file extension is $extension")
        }
    }
}

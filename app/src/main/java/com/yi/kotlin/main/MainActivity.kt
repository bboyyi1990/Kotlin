package com.yi.kotlin.main

import android.content.Intent
import android.os.Build
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.OnKeyboardListener
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.yi.common.database.User
import com.yi.common.glide.GlideUtil
import com.yi.common.http.BaseResponse
import com.yi.common.util.ClickInterceptor
import com.yi.common.util.LanguageUtils
import com.yi.common.util.Logger
import com.yi.common.util.setOnClicker
import com.yi.kotlin.R
import com.yi.kotlin.action.LoginAction
import com.yi.kotlin.action.api.ApiCallback
import com.yi.kotlin.alert.IOSSelectDialog
import com.yi.kotlin.base.BaseActivity
import com.yi.kotlin.base.Router
import com.yi.kotlin.data.LoginData
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Yi
 * @date 2020/5/20
 */
@Route(path = "${Router.GROUP}MainActivity")
class MainActivity : BaseActivity() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun getLayout(): Int = R.layout.activity_main

    @JvmField
    @Autowired(name = "value")
    var value: String? = null

    @JvmField
    @Autowired(name = "digit")
    var digit: String? = null

    private val adapter by lazy {
        MainAdapter().apply {
            repeat(30) {
                addData(LoginData("title = $it"))
            }
        }
    }

    private val model by viewModels<MainViewModel>()

    private val path =
        "http://pre-newpay-chat.oss-ap-southeast-1.aliyuncs.com/d0401bd6518a441787b218e1016dc5d7.png?x-oss-process=image/resize,w_70,h_70"

    override fun onCreate() {
//        val bindingUtil = DataBindingUtil.setContentView<ActivityMainBinding>(this, getLayout())
//        bindingUtil.mainData = model.mainData
        initBar(true, keyboardListener)
//        title_tv.setOnClickListener(clickListener)
        title_tv.setOnClicker(clickListener)
        refresh_layout.setOnRefreshLoadMoreListener(refreshListener)
        recycler_main.layoutManager = LinearLayoutManager(this)
        recycler_main.adapter = adapter
        adapter.setOnItemChildClickListener { adapter, view, position -> }
        model.getData(0)
        title_tv.text = getString(R.string.title)
        language_btn.setOnClickListener {
            IOSSelectDialog(this@MainActivity).addOptions(
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
            }.show()
        }
    }

    override fun onResume() {
        super.onResume()
        GlideUtil.loadImage(path, 10, GlideUtil.TOP_ROUND, 0, 0, iv_main, false)
        title_tv.text = getString(R.string.title)
        val language = resources.configuration.locale.language
        Logger.e(TAG, "language is $language")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent?.action == null) {
            finish()
            val intent = Intent(this, MainActivity::class.java)
        }
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


//        CheckRegisteredAction().enqueue("11111111111", "+86", object : ApiCallback<BaseData>() {
//            override fun onNext(t: BaseResponse<BaseData>) {
//                var data = t.data
//                Logger.e(TAG, "data is $data")
//            }
//        })


    }

    override fun onDestroy() {
        super.onDestroy()
//        RealmHelper.getRealDb().close()
    }
}

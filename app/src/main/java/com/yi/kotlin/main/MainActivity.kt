package com.yi.kotlin.main

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gyf.immersionbar.OnKeyboardListener
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.yi.kotlin.R
import com.yi.kotlin.base.BaseActivity
import com.yi.kotlin.base.Router
import com.yi.kotlin.data.BannerData
import com.yi.kotlin.data.MainData
import com.yi.kotlin.http.action.BannerAction
import com.yi.kotlin.http.action.TestAction
import com.yi.kotlin.http.base.ApiCallback
import com.yi.kotlin.uitl.Logger
import com.yi.kotlin.uitl.Main
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

    private val adapter by lazy { MainAdapter() }

    override fun onCreate() {
        initBar(false, keyboardListener)
        title_tv.setOnClickListener(clickListener)
        refresh_layout.setOnRefreshLoadMoreListener(refreshListener)
        recycler_main.layoutManager = LinearLayoutManager(this)
        recycler_main.adapter = adapter

        adapter.setOnItemChildClickListener { adapter, view, position -> }

    }

    private var keyboardListener = OnKeyboardListener { isPopup, keyboardHeight ->
        Logger.e(TAG, "isPopup $isPopup , keyboard height $keyboardHeight")
    }

    private var presenter = MainPresenter(object : MainPresenter.Callback {
        override fun onMainData(reset: Boolean, list: ArrayList<MainData>?) {
            adapter.setData(list, reset)
            refresh_layout.finishRefresh()
            refresh_layout.finishLoadMore()
        }
    })

    private var refreshListener = object : OnRefreshLoadMoreListener {
        override fun onRefresh(refreshLayout: RefreshLayout) {
            presenter.getData(0)
        }

        override fun onLoadMore(refreshLayout: RefreshLayout) {
            presenter.getData(adapter.itemCount)
        }
    }

    private var clickListener = View.OnClickListener { view ->
        TestAction().enqueue(object : ApiCallback<MainData>() {})
        BannerAction().enqueue(object : ApiCallback<BannerData>() {})
        BannerAction().enqueue(null)
    }
}

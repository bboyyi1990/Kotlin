package com.yi.kotlin.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gyf.immersionbar.ktx.fitsTitleBarMarginTop
import com.yi.kotlin.base.BaseDialogFragment
import com.yi.kotlin.base.CommonTextStyle
import kotlinx.coroutines.launch

/**
 * create by yi on 2023/5/25
 */
class ComposeDialog : BaseDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = ComposeView(requireActivity()).apply { setContent { Greeting() } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fitsTitleBarMarginTop(View(requireContext()).apply {
//            this.height = UIUtil.getStatusBarHeight()
        })
    }

    @Composable
    @Preview()
    fun DefaultPreview() {
        Greeting()
    }

    @Composable
    fun Greeting() {
        MaterialTheme {
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            Scaffold(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize(),
                scaffoldState = scaffoldState,
                topBar = {
                    GetTopBar {
                        scope.launch {
                            scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                            scaffoldState.snackbarHostState.showSnackbar("this is navi")
                        }
                    }
                },
                contentColor = Color.Blue
            ) {
                it
                Column(
                    modifier = Modifier
                        .background(Color.Red)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "this is content",
                        modifier = Modifier
                            .clickable {},
                    )
                }
            }
        }
    }

    @Composable
    fun GetTopBar(naviClick: () -> Unit) = Row(modifier = Modifier.background(Color.White)) {
        TopAppBar(modifier = Modifier
            .background(Color.White)
            .clickable {}, title = {
            Text("this is title", style = CommonTextStyle())
        }, navigationIcon = {
            IconButton(onClick = naviClick) { Icon(Icons.Default.ArrowBack, null) }
        }, backgroundColor = Color.White
        )
    }
}



package com.yi.kotlin.compose

import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.snackbar.Snackbar
import com.yi.kotlin.R
import com.yi.kotlin.alert.CommonSelectDialog
import com.yi.kotlin.base.BaseComposeActivity
import com.yi.kotlin.base.CommonTextStyle
import com.yi.kotlin.base.Router

/**
 * create by yi on 2023/3/17
 */
@Route(path = "${Router.GROUP}MainComposeActivity")
class MainComposeActivity : BaseComposeActivity() {

    override fun onCreate() {
//        setContent { Greeting() }
        setContent { LoafOnTheJob() }
    }

    @Preview(showBackground = true, name = "@@@@")
    @Composable
    fun DefaultPreview() = Greeting()

    @Composable
    @Preview(showBackground = true, name = "Touch Fish!")
    fun LoafOnTheJob() {
        val context = LocalContext.current as? MainComposeActivity
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
        ) {
            AndroidView(
                factory = {
                    TextView(it).apply {
                        text = "initialize"
                        textSize = 30f
                        setTextColor(Color.Green.toArgb())
//                        setOnClickListener {}
                    }
                },
                update = {
                    it.text = System.currentTimeMillis().toString()
                },
                modifier = Modifier
//                    .fillMaxSize()
                    .background(Color.Blue)
//                    .sizeIn(maxWidth = 10.dp, maxHeight = 10.dp)
                    .clickable {
                        Snackbar
                            .make(window.decorView, "this is Snackbar", Snackbar.LENGTH_SHORT)
                            .show()
                        ComposeDialog().show(context!!.supportFragmentManager,"")
                    },
            )
        }
    }
}

@Composable
fun Greeting() {
    MaterialTheme {
        val list = mutableListOf<Any>()
        repeat(15) { list.add(it) }
        LazyColumn { items(list) { MessageCard() } }
    }
}

@Composable
fun MessageCard() {
    val viewModel = viewModel<MainComposeViewModel>()
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        Image(
            painter = painterResource(R.drawable.dog_lover),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clickable { viewModel.test() }
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colors.onSecondary, CircleShape),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.size(4.dp))
        var isExpanded by rememberSaveable { mutableStateOf(false) }
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = viewModel._result.value,
                style = CommonTextStyle()
            )
            Spacer(modifier = Modifier.height(4.dp))
            CustomizeText(isExpanded)
        }
    }
}

@Composable
fun CustomizeText(isExpanded: Boolean) {
    val viewModel = viewModel<MainComposeViewModel>()
    val content =
        "文字对任何界面都属于核心内容，而利用 Jetpack Compose 可以更轻松地显示或写入文字。Compose 可以充分利用其构建块的组合，这意味着您无需覆盖各种属性和方法，也无需扩展大型类，即可拥有特定的可组合项设计以及按您期望的方式运行的逻辑。"
    val context = LocalContext.current as? MainComposeActivity
    Surface(
        shape = MaterialTheme.shapes.medium, elevation = 100.dp,
        modifier = Modifier
            .animateContentSize()
            .padding(1.dp)
    ) {
        Text(
            text = content,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
            overflow = TextOverflow.Ellipsis,
            style = CommonTextStyle(),
            modifier = Modifier
                .animateContentSize()
                .padding(4.dp)
                .clickable {
                    CommonSelectDialog()
                        .addOptions(arrayOf(viewModel._result.value)) {
                        }
                        .show(context?.supportFragmentManager)
                },
        )
    }
}

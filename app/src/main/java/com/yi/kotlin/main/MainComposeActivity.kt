package com.yi.kotlin.main

import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alibaba.android.arouter.facade.annotation.Route
import com.yi.kotlin.R
import com.yi.kotlin.alert.CommonSelectDialog
import com.yi.kotlin.base.BaseComposeActivity
import com.yi.kotlin.base.Router

/**
 * create by yi on 2023/3/17
 */
@Route(path = "${Router.GROUP}MainComposeActivity")
class MainComposeActivity : BaseComposeActivity() {

//    private val model by viewModels<MainViewModel>()

    override fun onCreate() {
        initBar(true)
        setContent { Greeting() }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Greeting()
}

@Composable
fun Greeting() {
    MaterialTheme {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
//                .background(Color.Red)
//            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.dog_lover),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { }
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colors.onSecondary, CircleShape),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Column {
                Text(text = "msg.author", style = commonTextStyle())
                Spacer(modifier = Modifier.height(4.dp))
                CustomizeText()
            }
        }
    }
}

@Composable
fun CustomizeText() {
    val content =
        "文字对任何界面都属于核心内容，而利用 Jetpack Compose 可以更轻松地显示或写入文字。Compose 可以充分利用其构建块的组合，这意味着您无需覆盖各种属性和方法，也无需扩展大型类，即可拥有特定的可组合项设计以及按您期望的方式运行的逻辑。"
    val context = LocalContext.current as AppCompatActivity
    Surface(shape = MaterialTheme.shapes.medium, elevation = 100.dp) {
        Text(
//            text = stringResource(R.string.APP_NAME),
            text = content,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = commonTextStyle(),
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    CommonSelectDialog()
                        .addOptions(arrayOf("a")) {
                        }
                        .show(context.supportFragmentManager)
                },
        )
    }
}

@Composable
fun commonTextStyle() = TextStyle(
    fontSize = 14.sp,
    fontFamily = FontFamily(Font(R.font.phetsarath_ot)),
    color = colorResource(R.color.colorTitle),
    fontWeight = FontWeight(400),
)


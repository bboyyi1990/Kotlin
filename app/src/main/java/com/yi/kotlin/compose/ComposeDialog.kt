package com.yi.kotlin.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gyf.immersionbar.ktx.fitsTitleBarMarginTop
import com.yi.common.util.Logger.loggerE
import com.yi.kotlin.base.BaseDialogFragment
import com.yi.kotlin.base.CommonTextStyle
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

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
    @Preview
    fun DefaultPreview() {
        Greeting()
    }

    @Composable
    fun Greeting() {
        MaterialTheme {
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            scope.launch {

            }
            Scaffold(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize(),
                scaffoldState = scaffoldState,
                topBar = {
                    GetTopBar {
                        scope.launch {
                            scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                            val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                                "This is navi",
                                "Dismiss"
                            )
                            when (snackBarResult) {
                                SnackbarResult.Dismissed -> {
                                    "snackBar dismiss".loggerE(this)
                                }

                                SnackbarResult.ActionPerformed -> {
                                    "snackBar ActionPerformed".loggerE(this)
                                }
                            }
                        }
                    }
                },
                bottomBar = {
                    GetBottomBar {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                },
                contentColor = Color.Yellow,
                snackbarHost = { SnackbarHost(it) },
                drawerContent = { GetDrawer() },
                floatingActionButton = {
                    val showDialogState = remember { mutableStateOf(false) }
                    GetFloating { showDialogState.value = true }
                    GetAlert(showDialogState)
                },
                floatingActionButtonPosition = FabPosition.End
            ) {
                it
                GetContent()
            }
        }
    }
}

@Composable
fun GetTopBar(naviClick: () -> Unit) = Column(modifier = Modifier.background(Color.White)) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(25.dp)
            .background(Color.White)
    )
    TopAppBar(
        modifier = Modifier
            .background(Color.White)
            .clickable {},
        title = {
            Text(
                "this is title",
                style = CommonTextStyle(),
                color = Color.DarkGray.copy(0.5f)
            )
        },
        navigationIcon = {
            IconButton(onClick = naviClick) { Icon(Icons.Default.ArrowBack, null) }
        },
        backgroundColor = Color.White,
//        elevation = 0.dp,
    )
}

@Composable
fun GetContent() {
    Column(
        modifier = Modifier
            .background(Color.Red)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(100) {
            Text(
                text = "this is content $it",
                color = Color.White,
                modifier = Modifier
                    .clickable(onClick = {})
//            .border(4.dp, Color.White, CircleShape)

                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = {
                                "this is onDoubleTap".loggerE(this)
                            },
                            onLongPress = {
                                "this is onLongPress".loggerE(this)
                            },
                            onPress = {
                                "this is onPress".loggerE(this)
                            },
                            onTap = {
                                "this is onTap".loggerE(this)
                            }
                        )
                    },
                style = CommonTextStyle()
            )
        }
        GetProgress()
    }
}

@Composable
fun GetBottomBar(onClick: () -> Unit) = BottomAppBar {
    Text(
        text = "this is bottom bar", color = Color.Cyan, modifier =
        Modifier.clickable(onClick = onClick, enabled = true)
    )
}

@Composable
fun GetDrawer() = Column(
    modifier = Modifier
        .background(Color.Red)
        .fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = "this is Drawer", modifier = Modifier.clickable {},
    )
    repeat(5) { item ->
        Text(text = "Item $item", modifier = Modifier.padding(8.dp), color = Color.Black)
    }
}

@Composable
fun GetFloating(onClick: () -> Unit) {
    var offset by remember { mutableStateOf(Offset.Zero) }
//    var offsetX by remember { mutableStateOf(0f) }
//    var offsetY by remember { mutableStateOf(0f) }
    val state = rememberDraggableState {
        "DraggableState = $it".loggerE(ComposeDialog::class.java.simpleName)
//        offsetX += it
//        offsetY += it
    }
    FloatingActionButton(
        modifier = Modifier
            .offset {
//                IntOffset(offsetX.roundToInt(), 0)
                IntOffset(offset.x.toInt(), offset.y.roundToInt())
            }
            .draggable(
                DraggableState {
//                    offsetX += it
//                    offsetY += it
                },
//                state,
                Orientation.Vertical
            )
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change: PointerInputChange, dragAmount: Offset ->
                        offset += dragAmount
                    }
                )
            },
        onClick = onClick,
        backgroundColor = Color.Red,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(0, 20, 50, 75),
    ) { Text(text = "+", fontSize = 28.sp) }
}

@Composable
fun GetProgress() {
    CircularProgressIndicator(
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(16.dp)
            .size(40.dp),
        progress = 0.4f,
        strokeCap = StrokeCap.Round
    )
    LinearProgressIndicator(
        progress = 0.4f, color = Color.Yellow, backgroundColor = Color.White,
        strokeCap = StrokeCap.Round,
    )
}

@Composable
fun GetAlert(showState: MutableState<Boolean>) {
    if (showState.value) {
        AlertDialog(
            onDismissRequest = { showState.value = false },
            text = { Text(text = "this is dialog title") },
            confirmButton = {
                Button(onClick = { showState.value = false }) { Text(text = "Confirm") }
            },
            dismissButton = {
                Button(onClick = { showState.value = false }) { Text(text = "Dismiss") }
            },
            backgroundColor = Color.Black,
            contentColor = Color.White
        )
    }
}


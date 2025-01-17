package com.yi.kotlin.compose.theme

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val themeColor = Color(0xFF4CAF50)
val color1 = Color(0, 93, 99, 1)
val color2 by lazy { Color(0xFFEFB8C8) }

@Preview
@Composable
fun DrawPreview() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasQuadrantSize = size / 2f
//        drawRect(color = Color.Magenta, size = canvasQuadrantSize)
//        drawLine(
//            Color.Blue, Offset.Zero,
//            Offset(canvasQuadrantSize.width, canvasQuadrantSize.height)
//        )
//        drawCircle(color = Color.Blue, 20.dp.toPx())

        scale(10f, 15f) {}
        translate(100f, -300f) {}
        rotate(degrees = 45f) {}
        inset(horizontal = 100f, vertical = 50f) {}
        withTransform({
//            rotate(45f)
//            translate(50f, 300f)
//            scale(1.5f, 1.5f)
            inset(horizontal = 2f, vertical = 2f)
        }) {
//            drawRect(
////                color = Color.Magenta,
//                Brush.verticalGradient(mutableListOf(Color.Red, Color.Green)),
////                Offset(size.width, 100.dp.toPx()),
//                size = canvasQuadrantSize,
////                style = Stroke(10f, cap = StrokeCap.Round)
//            )
            drawRect(
                Brush.verticalGradient(mutableListOf(Color.Red, Color.Green), 0f, 10.dp.toPx()),
                size = Size(canvasQuadrantSize.width, 10.dp.toPx())
            )
            drawIntoCanvas {
                drawRoundRect(
                    Brush.verticalGradient(mutableListOf(Color.Red, Color.Green)),
                    Offset(size.width, 100.dp.toPx()), Size(canvasQuadrantSize.width, 10.dp.toPx())
                )
                val drawable: Drawable? = null
                drawable?.draw(it.nativeCanvas)
            }
        }

        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width / 2f, size.height / 2f)
            lineTo(size.width, 0f)
            close()
        }
//        drawPath(path, Color.Green, style = Stroke(10f, cap = StrokeCap.Round))
    }
}
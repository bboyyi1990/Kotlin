package com.yi.kotlin.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.yi.kotlin.R

@Composable
fun CommonTextStyle() = TextStyle(
    fontSize = 14.sp,
    fontFamily = FontFamily(Font(R.font.phetsarath_ot)),
    color = colorResource(R.color.colorTitle),
    fontWeight = FontWeight(400),
)
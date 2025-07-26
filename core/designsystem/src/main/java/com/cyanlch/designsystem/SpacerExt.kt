package com.cyanlch.designsystem

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WidthSpacer(width: Int) = Spacer(modifier = Modifier.width(width.dp))

@Composable
fun HeightSpacer(height: Int) = Spacer(modifier = Modifier.height(height.dp))
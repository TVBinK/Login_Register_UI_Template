package com.example.learnjetpack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StepProgressBar(
    steps: List<String>,
    currentStep: Int = 0,
    modifier: Modifier = Modifier
) {
    val completedCircleColor = Color(0xFFFF7A3C)
    val pendingCircleColor = Color(0xFFD3D3D3)
    val completedLineColor = Color(0xFFFF7A3C)
    val pendingLineColor = Color(0xFFD3D3D3)
    val completedLabelColor = Color(0xFFFF7A3C)
    val currentLabelColor = Color(0xFFFF7A3C)
    val pendingLabelColor = Color(0xFFD3D3D3)
    val circleSize = 40.dp
    val lineHeight = 6.dp

    Column(modifier = modifier) {
        // Labels
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // set labels with dynamic width and padding
            steps.forEachIndexed { index, label ->
                val labelModifier = if (label == "Birthday") {
                    Modifier
                        .width(50.dp)
                        .padding(start = 5.dp)
                }
                else if (label == "Gender") {
                    Modifier
                        .width(50.dp)
                        .padding(start = 5.dp)
                }
                else if (label == "Pass") {
                    Modifier
                        .width(50.dp)
                        .padding(start = 10.dp)
                }
                else {
                    Modifier.width(50.dp)
                }
                
                val labelColor = when {
                    index < currentStep -> completedLabelColor
                    else -> pendingLabelColor
                }
                
                Text(
                    text = label,
                    color = labelColor,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = labelModifier,
                    textAlign = TextAlign.Center,
                    maxLines =1                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        // Circles + Lines (đường nối ở giữa circle)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(0.3f)) // Spacer to center the circles
            steps.forEachIndexed { index, _ ->
                val circleColor = when {
                    index < currentStep -> completedCircleColor
                    else -> pendingCircleColor
                }
                
                Box(
                    modifier = Modifier
                        .size(circleSize)
                        .background(circleColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    // Show checkmark for completed steps
                    if (index < currentStep) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Completed",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                
                if (index < steps.lastIndex) {
                    val lineColor = if (index < currentStep-1) completedLineColor else pendingLineColor
                    Box(
                        modifier = Modifier
                            .height(lineHeight)
                            .weight(0.7f)
                            .background(lineColor, RoundedCornerShape(10))
                    )
                }
            }
        }
    }
} 
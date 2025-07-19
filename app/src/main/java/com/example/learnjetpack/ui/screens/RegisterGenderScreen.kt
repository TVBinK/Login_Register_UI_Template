package com.example.learnjetpack.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnjetpack.R
import com.example.learnjetpack.ui.components.StepProgressBar

@Composable
fun RegisterGenderScreen(
    onSignUpSuccess: () -> Unit,
    onBackClick: () -> Unit
) {
    val genderOptions = listOf("Male", "Female", "Other")
    var expanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf("") }
    var isGenderValid by remember { mutableStateOf(false) }
    val buttonScale by animateFloatAsState(
        targetValue = if (isGenderValid) 1f else 0.95f,
        animationSpec = tween(durationMillis = 20),
        label = "buttonScale"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(800, delayMillis = 200)),
            exit = fadeOut(animationSpec = tween(800))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.main_register),
                    contentDescription = "Register Illustration",
                    modifier = Modifier.size(340.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 8.dp, top = 8.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                color = Color.White.copy(alpha = 0.9f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu_back),
                            contentDescription = "Back",
                            tint = Color(0xFF232B35),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 400)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Text(
                text = "Create account",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF232B35),
                modifier = Modifier.padding(vertical = 8.dp),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 600)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            StepProgressBar(
                steps = listOf("Email", "Name", "Birthday", "Gender", "Pass"),
                currentStep = 3,
                modifier = Modifier.fillMaxWidth(1f)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 800)),
            exit = fadeOut(animationSpec = tween(600)),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "Gender",
                fontSize = 15.sp,
                color = Color(0xFF232B35),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 900)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .background(Color(0xFFF2F3F7), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = if (selectedGender.isEmpty()) "Select your gender" else selectedGender,
                    color = if (selectedGender.isEmpty()) Color(0xFFB0B4BA) else Color(0xFF232B35),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
                //icon dropdown với clickable
                IconButton(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_drop_down),
                        contentDescription = "Dropdown Arrow",
                        modifier = Modifier
                            .scale(1.2f)
                            .size(20.dp),
                        tint = Color(0xFF232B35),
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(Color.White)
                        .align(Alignment.TopEnd)

                ) {
                    genderOptions.forEach { gender ->
                        DropdownMenuItem(
                            //text color = black
                            text = { Text(gender, color = Color.Black )},
                            onClick = {
                                selectedGender = gender
                                expanded = false
                                isGenderValid = true
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 1000)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Button(
                onClick = {
                    if (isGenderValid) {
                        onSignUpSuccess()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .scale(buttonScale),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isGenderValid) Color(0xFFFF7A3C) else Color(0xFFD3D3D3)
                ),
                shape = RoundedCornerShape(24.dp),
                enabled = isGenderValid
            ) {
                Text(
                    text = "Continue",
                    color = if (isGenderValid) Color.White else Color(0xFFB0B4BA),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 1200)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Column {
                Text(
                    text = "Sign up with",
                    fontSize = 15.sp,
                    color = Color(0xFF232B35),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "Facebook",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_github),
                        contentDescription = "Github",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 1400)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Have an account? ",
                    fontSize = 14.sp,
                    color = Color(0xFF232B35)
                )
                Text(
                    text = "Log in",
                    fontSize = 14.sp,
                    color = Color(0xFFFF7A3C),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "RegisterGenderScreen Preview")
@Composable
fun PreviewRegisterGenderScreen() {
    MaterialTheme {
        RegisterGenderScreen(
            onSignUpSuccess = {},
            onBackClick = {}
        )
    }
} 
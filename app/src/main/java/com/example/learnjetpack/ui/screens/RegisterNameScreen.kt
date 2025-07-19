package com.example.learnjetpack.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnjetpack.R
import com.example.learnjetpack.ui.components.StepProgressBar

@Composable
fun RegisterNameScreen(
    onSignUpSuccess: () -> Unit,
    onBackClick: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var isNameValid by remember { mutableStateOf(false) }
    
    // Animation states
    val buttonScale by animateFloatAsState(
        targetValue = if (isNameValid) 1f else 0.95f,
        animationSpec = tween(durationMillis = 20),
        label = "buttonScale"
    )

    // Name validation function
    fun validateName(name: String): Boolean {
        return name.trim().length >= 2
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        
        // Illustration with back button overlay
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
                // Thay bằng ảnh thật nếu có
                Image(
                    painter = painterResource(id = R.drawable.main_register),
                    contentDescription = "Register Illustration",
                    modifier = Modifier.size(340.dp)
                )
                
                // Back button positioned on top of image
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
                            painter = painterResource(id = R.drawable.ic_menu_back), // Thay bằng icon back thật
                            contentDescription = "Back",
                            tint = Color(0xFF232B35),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Title with slide up animation
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
        
        // StepProgressBar with fade in animation
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 600)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            StepProgressBar(
                steps = listOf("Email", "Name", "Birthday", "Gender", "Pass"),
                currentStep = 1,
                modifier = Modifier.fillMaxWidth(1f)
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Full Name label with slide in animation
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 800)),
            exit = fadeOut(animationSpec = tween(600)),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "Your FullName",
                fontSize = 15.sp,
                color = Color(0xFF232B35),
                fontWeight = FontWeight.Medium
            )
        }
        
        Spacer(modifier = Modifier.height(6.dp))
        
        // Full Name input with slide in animation
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 900)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            OutlinedTextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                    isNameValid = validateName(it)
                },
                placeholder = { Text("TvBinK", color = Color(0xFFB0B4BA)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedContainerColor = Color(0xFFF2F3F7),
                    focusedContainerColor = Color(0xFFF2F3F7)
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                visualTransformation = VisualTransformation.None,
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black)
            )
        }
        
        Spacer(modifier = Modifier.height(18.dp))
        
        // Continue button with scale animation
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 1000)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Button(
                onClick = {
                    if (isNameValid) {
                        onSignUpSuccess()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .scale(buttonScale),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isNameValid) Color(0xFFFF7A3C) else Color(0xFFD3D3D3)
                ),
                shape = RoundedCornerShape(24.dp),
                enabled = isNameValid
            ) {
                Text(
                    text = "Continue",
                    color = if (isNameValid) Color.White else Color(0xFFB0B4BA),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        Spacer(modifier = Modifier.height(18.dp))
        
        // Social sign up section with fade in animation
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
                        painter = painterResource(id = R.drawable.ic_google), // Thay bằng icon Google thật
                        contentDescription = "Google",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_facebook), // Thay bằng icon Facebook thật
                        contentDescription = "Facebook",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_github), // Thay bằng icon Github thật
                        contentDescription = "Github",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(25.dp))
        
        // Login link with fade in animation
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

@Preview(showBackground = true, name = "RegisterNameScreen Preview")
@Composable
fun PreviewRegisterNameScreen() {
    MaterialTheme {
        RegisterNameScreen(
            onSignUpSuccess = {},
            onBackClick = {}
        )
    }
}
// Gói chứa màn hình giao diện đăng nhập
package com.example.learnjetpack.ui.screens

// Import các thư viện cần thiết cho Compose
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnjetpack.R

// Hàm Composable cho màn hình đăng nhập
@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,           // Callback khi đăng nhập thành công
    onGoogleLoginClick: () -> Unit,     // Callback khi đăng nhập Google
    onFacebookLoginClick: () -> Unit,   // Callback khi đăng nhập Facebook
    onGithubLoginClick: () -> Unit,     // Callback khi đăng nhập GitHub
    onSignUpClick: () -> Unit           // Callback khi chuyển sang đăng ký
) {
    // State cho form đăng nhập
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    
    // State cho hiệu ứng scale của nút Login
    val buttonScale by animateFloatAsState(
        targetValue = if (email.isNotEmpty() && password.isNotEmpty()) 1f else 0.95f,
        animationSpec = tween(durationMillis = 200),
        label = "buttonScale"
    )

    // Giao diện tổng thể của màn hình
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Hình minh họa đăng nhập
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
                // Ảnh minh họa đăng nhập
                Image(
                    painter = painterResource(id = R.drawable.main_login),
                    contentDescription = "Login Illustration",
                    modifier = Modifier.size(340.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tiêu đề "Log in"
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 400)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Text(
                text = "Log in",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF232B35),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Form đăng nhập
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 600)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                // Email field
                Text(
                    text = "Email",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF232B35),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = {
                        Text(
                            text = "tvbink@example.com",
                            fontSize = 15.sp,
                            color = Color(0xFFB0B4BA)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFF7A3C),
                        unfocusedBorderColor = Color(0xFFE8E8E8),
                        focusedContainerColor = Color(0xFFF8F9FA),
                        unfocusedContainerColor = Color(0xFFF8F9FA)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        color = Color(0xFF232B35),
                        fontSize = 15.sp
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Password field
                Text(
                    text = "Password",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF232B35),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = {
                        Text(
                            text = "Enter your password",
                            fontSize = 15.sp,
                            color = Color(0xFFB0B4BA)
                        )
                    },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFF7A3C),
                        unfocusedBorderColor = Color(0xFFE8E8E8),
                        focusedContainerColor = Color(0xFFF8F9FA),
                        unfocusedContainerColor = Color(0xFFF8F9FA)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        color = Color(0xFF232B35),
                        fontSize = 15.sp
                    ),
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Nút Login
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 800)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        onLoginClick()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .scale(buttonScale),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (email.isNotEmpty() && password.isNotEmpty()) Color(0xFFFF7A3C) else Color(0xFFD3D3D3)
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = email.isNotEmpty() && password.isNotEmpty()
            ) {
                Text(
                    text = "Log in",
                    color = if (email.isNotEmpty() && password.isNotEmpty()) Color.White else Color(0xFFB0B4BA),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Divider với text "Log in with"
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 1000)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFE8E8E8),
                    thickness = 1.dp
                )
                Text(
                    text = "Log in with",
                    fontSize = 15.sp,
                    color = Color(0xFF666666),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Divider(
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFE8E8E8),
                    thickness = 1.dp
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Social login buttons
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 1200)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                // Google button
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFFE8E8E8),
                            shape = CircleShape
                        )
                        .clickable { onGoogleLoginClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google Login",
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Facebook button
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFFE8E8E8),
                            shape = CircleShape
                        )
                        .clickable { onFacebookLoginClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "Facebook Login",
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // GitHub button
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFFE8E8E8),
                            shape = CircleShape
                        )
                        .clickable { onGithubLoginClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_github),
                        contentDescription = "GitHub Login",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Sign up link
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
                    text = "No account? ",
                    fontSize = 15.sp,
                    color = Color(0xFF232B35)
                )
                Text(
                    text = "Sign up",
                    fontSize = 15.sp,
                    color = Color(0xFFFF7A3C),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onSignUpClick() }
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

// Hàm preview cho màn hình đăng nhập
@Preview(showBackground = true, name = "LoginScreen Preview")
@Composable
fun PreviewLoginScreen() {
    MaterialTheme {
        LoginScreen(
            onLoginClick = {},
            onGoogleLoginClick = {},
            onFacebookLoginClick = {},
            onGithubLoginClick = {},
            onSignUpClick = {}
        )
    }
} 
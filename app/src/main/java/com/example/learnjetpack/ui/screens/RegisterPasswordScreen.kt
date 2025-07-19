// Gói chứa màn hình giao diện đăng ký mật khẩu
package com.example.learnjetpack.ui.screens

// Import các thư viện cần thiết cho Compose
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnjetpack.R
import com.example.learnjetpack.ui.components.StepProgressBar

// Hàm Composable cho màn hình đăng ký mật khẩu
@Composable
fun RegisterPasswordScreen(
    onSignUpSuccess: () -> Unit, // Callback khi đăng ký thành công
    onBackClick: () -> Unit      // Callback khi bấm nút quay lại
) {
    // State lưu giá trị mật khẩu
    var password by remember { mutableStateOf(TextFieldValue("")) }
    // State lưu giá trị xác nhận mật khẩu
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    // State hiển thị/ẩn mật khẩu
    var passwordVisible by remember { mutableStateOf(false) }
    // State hiển thị/ẩn xác nhận mật khẩu
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    // State kiểm tra mật khẩu hợp lệ hay không
    var isPasswordValid by remember { mutableStateOf(false) }
    // State lưu thông báo lỗi mật khẩu
    var passwordError by remember { mutableStateOf("") }
    // State lưu thông báo lỗi xác nhận mật khẩu
    var confirmPasswordError by remember { mutableStateOf("") }

    // State cho hiệu ứng scale của nút Finish
    val buttonScale by animateFloatAsState(
        targetValue = if (isPasswordValid) 1f else 0.95f,
        animationSpec = tween(durationMillis = 20),
        label = "buttonScale"
    )

    // Hàm kiểm tra mật khẩu hợp lệ: ít nhất 8 ký tự, có chữ hoa, chữ thường, số
    fun validatePassword(password: String): Boolean {
        if (password.length < 8) return false
        if (!password.any { it.isUpperCase() }) return false
        if (!password.any { it.isLowerCase() }) return false
        if (!password.any { it.isDigit() }) return false
        return true
    }

    // Hàm kiểm tra xác nhận mật khẩu
    fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword && confirmPassword.isNotEmpty()
    }

    // Cập nhật trạng thái hợp lệ tổng thể
    val isFormValid = isPasswordValid && confirmPasswordError.isEmpty() && 
                     password.text.isNotEmpty() && confirmPassword.text.isNotEmpty()

    // Giao diện tổng thể của màn hình
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Hình minh họa và nút quay lại (back)
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
                // Ảnh minh họa đăng ký
                Image(
                    painter = painterResource(id = R.drawable.main_register),
                    contentDescription = "Register Illustration",
                    modifier = Modifier.size(340.dp)
                )

                // Nút back nằm trên ảnh
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

        // Tiêu đề màn hình
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

        // Thanh tiến trình các bước đăng ký
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 600)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            StepProgressBar(
                steps = listOf("Email", "Name", "Birthday", "Gender", "Pass"),
                currentStep = 4, // Đang ở bước Pass (cuối cùng)
                modifier = Modifier.fillMaxWidth(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nhãn "Password"
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 800)),
            exit = fadeOut(animationSpec = tween(600)),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "Password",
                fontSize = 15.sp,
                color = Color(0xFF232B35),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        // Ô nhập mật khẩu
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 900)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            OutlinedTextField(
                value = password,
                onValueChange = { newValue ->
                    password = newValue
                    isPasswordValid = validatePassword(newValue.text)
                    if (newValue.text.isNotEmpty() && !isPasswordValid) {
                        passwordError = "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường và số"
                    } else {
                        passwordError = ""
                    }
                    // Kiểm tra lại xác nhận mật khẩu
                    if (confirmPassword.text.isNotEmpty()) {
                        if (!validateConfirmPassword(newValue.text, confirmPassword.text)) {
                            confirmPasswordError = "Mật khẩu xác nhận không khớp"
                        } else {
                            confirmPasswordError = ""
                        }
                    }
                },
                placeholder = { Text("Enter Password", color = Color(0xFFB0B4BA)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .background(Color.Transparent),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedContainerColor = Color(0xFFF2F3F7),
                    focusedContainerColor = Color(0xFFF2F3F7)
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
                trailingIcon = {
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (passwordVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_closed
                            ),
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            tint = Color(0xFFB0B4BA)
                        )
                    }
                }
            )
        }

        // Hiển thị thông báo lỗi mật khẩu nếu có
        if (passwordError.isNotEmpty()) {
            Text(
                text = passwordError,
                color = Color.Red,
                fontSize = 13.sp,
                modifier = Modifier.align(Alignment.Start).padding(top = 2.dp, start = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Nhãn "Confirm Password"
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 1000)),
            exit = fadeOut(animationSpec = tween(600)),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "Confirm Password",
                fontSize = 15.sp,
                color = Color(0xFF232B35),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        // Ô nhập xác nhận mật khẩu
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 1100)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { newValue ->
                    confirmPassword = newValue
                    if (newValue.text.isNotEmpty()) {
                        if (!validateConfirmPassword(password.text, newValue.text)) {
                            confirmPasswordError = "Mật khẩu xác nhận không khớp"
                        } else {
                            confirmPasswordError = ""
                        }
                    } else {
                        confirmPasswordError = ""
                    }
                },
                placeholder = { Text("Enter Password", color = Color(0xFFB0B4BA)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .background(Color.Transparent),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedContainerColor = Color(0xFFF2F3F7),
                    focusedContainerColor = Color(0xFFF2F3F7)
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
                trailingIcon = {
                    IconButton(
                        onClick = { confirmPasswordVisible = !confirmPasswordVisible }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (confirmPasswordVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_closed
                            ),
                            contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password",
                            tint = Color(0xFFB0B4BA)
                        )
                    }
                }
            )
        }

        // Hiển thị thông báo lỗi xác nhận mật khẩu nếu có
        if (confirmPasswordError.isNotEmpty()) {
            Text(
                text = confirmPasswordError,
                color = Color.Red,
                fontSize = 13.sp,
                modifier = Modifier.align(Alignment.Start).padding(top = 2.dp, start = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nút Finish, chỉ bật khi tất cả hợp lệ
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 1200)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Button(
                onClick = {
                    if (isFormValid) {
                        onSignUpSuccess()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .scale(buttonScale),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) Color(0xFFFF7A3C) else Color(0xFFD3D3D3)
                ),
                shape = RoundedCornerShape(24.dp),
                enabled = isFormValid
            ) {
                Text(
                    text = "Finish",
                    color = if (isFormValid) Color.White else Color(0xFFB0B4BA),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// Hàm preview cho màn hình đăng ký mật khẩu
@Preview(showBackground = true, name = "RegisterPasswordScreen Preview")
@Composable
fun PreviewPasswordScreen() {
    MaterialTheme {
        RegisterPasswordScreen(
            onSignUpSuccess = {},
            onBackClick = {}
        )
    }
} 
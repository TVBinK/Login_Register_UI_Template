// Gói chứa màn hình giao diện đăng ký ngày sinh
package com.example.learnjetpack.ui.screens

// Import các thư viện cần thiết cho Compose và xử lý ngày tháng
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnjetpack.R
import com.example.learnjetpack.ui.components.StepProgressBar
import java.util.Calendar
import java.text.SimpleDateFormat
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle

// Hàm Composable cho màn hình đăng ký ngày sinh
@Composable
fun RegisterBirthdayScreen(
    onSignUpSuccess: () -> Unit, // Callback khi đăng ký thành công
    onBackClick: () -> Unit      // Callback khi bấm nút quay lại
) {
    // Khởi tạo biến calendar để dùng cho DatePicker nếu cần
    val calendar = remember { Calendar.getInstance() }
    // State lưu giá trị ngày sinh dưới dạng TextFieldValue để kiểm soát con trỏ
    var birthday by remember { mutableStateOf(TextFieldValue("")) }
    // State kiểm tra ngày sinh hợp lệ hay không
    var isBirthdayValid by remember { mutableStateOf(false) }
    // State lưu thông báo lỗi ngày sinh
    var birthdayError by remember { mutableStateOf("") }

    // State cho hiệu ứng scale của nút Continue
    val buttonScale by animateFloatAsState(
        targetValue = if (isBirthdayValid) 1f else 0.95f,
        animationSpec = tween(durationMillis = 20),
        label = "buttonScale"
    )

    // Hàm kiểm tra ngày sinh hợp lệ: đúng định dạng và tuổi >= 10
    fun validateBirthday(date: String): Boolean {
        if (date.isBlank()) return false
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val dob = sdf.parse(date)
            val today = Calendar.getInstance()
            val dobCal = Calendar.getInstance().apply { time = dob }
            var age = today.get(Calendar.YEAR) - dobCal.get(Calendar.YEAR)
            if (today.get(Calendar.DAY_OF_YEAR) < dobCal.get(Calendar.DAY_OF_YEAR)) {
                age--
            }
            age >= 10
        } catch (e: Exception) {
            false
        }
    }

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
                            painter = painterResource(id = R.drawable.ic_menu_back), // Icon quay lại
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
                currentStep = 2, // Đang ở bước Birthday
                modifier = Modifier.fillMaxWidth(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nhãn "Your Birthday"
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 800)),
            exit = fadeOut(animationSpec = tween(600)),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "Your Birthday",
                fontSize = 15.sp,
                color = Color(0xFF232B35),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        // Ô nhập ngày sinh, tự động thêm dấu '/' và di chuyển con trỏ
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 900)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            OutlinedTextField(
                value = birthday,
                onValueChange = { newValue ->
                    // Lọc chỉ cho phép số và dấu /
                    var input = newValue.text.filter { it.isDigit() || it == '/' }
                    var selection = newValue.selection.end

                    // Tự động thêm dấu / sau ngày và tháng
                    if (input.length == 2 && !input.endsWith("/") && input.count { it == '/' } == 0) {
                        input += "/"
                        selection = input.length // Đặt con trỏ sau dấu /
                    } else if (input.length == 5 && input.count { it == '/' } == 1) {
                        input += "/"
                        selection = input.length // Đặt con trỏ sau dấu /
                    }
                    // Giới hạn tối đa 10 ký tự (DD/MM/YYYY)
                    if (input.length > 10) input = input.take(10)
                    birthday = TextFieldValue(
                        text = input,
                        selection = TextRange(selection.coerceAtMost(input.length))
                    )
                    // Validate và set lỗi
                    if (input.length == 10) {
                        val sdf = java.text.SimpleDateFormat("dd/MM/yyyy")
                        sdf.isLenient = false
                        try {
                            val dob = sdf.parse(input)
                            val today = java.util.Calendar.getInstance()
                            val dobCal = java.util.Calendar.getInstance().apply { time = dob }
                            var age = today.get(java.util.Calendar.YEAR) - dobCal.get(java.util.Calendar.YEAR)
                            if (today.get(java.util.Calendar.DAY_OF_YEAR) < dobCal.get(java.util.Calendar.DAY_OF_YEAR)) {
                                age--
                            }
                            if (dob.after(today.time)) {
                                isBirthdayValid = false
                                birthdayError = "Ngày sinh không được ở tương lai"
                            } else if (age < 10) {
                                isBirthdayValid = false
                                birthdayError = "Bạn phải đủ 10 tuổi trở lên"
                            } else {
                                isBirthdayValid = true
                                birthdayError = ""
                            }
                        } catch (e: Exception) {
                            isBirthdayValid = false
                            birthdayError = "Định dạng ngày sinh không hợp lệ"
                        }
                    } else if (input.isNotEmpty()) {
                        isBirthdayValid = false
                        birthdayError = "Định dạng ngày sinh phải là DD/MM/YYYY"
                    } else {
                        isBirthdayValid = false
                        birthdayError = ""
                    }
                },
                placeholder = { Text("DD/MM/YYYY", color = Color(0xFFB0B4BA)) },
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
                enabled = true, // Cho phép nhập
                readOnly = false, // Cho phép nhập
                textStyle = TextStyle(color = Color.Black)
            )
        }
        Spacer(modifier = Modifier.height(18.dp))

        // Hiển thị thông báo lỗi nếu có
        if (birthdayError.isNotEmpty()) {
            Text(
                text = birthdayError,
                color = Color.Red,
                fontSize = 13.sp,
                modifier = Modifier.align(Alignment.Start).padding(top = 2.dp, start = 4.dp)
            )
        }

        // Nút Continue, chỉ bật khi ngày sinh hợp lệ
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 1000)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Button(
                onClick = {
                    if (isBirthdayValid) {
                        onSignUpSuccess()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .scale(buttonScale),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isBirthdayValid) Color(0xFFFF7A3C) else Color(0xFFD3D3D3)
                ),
                shape = RoundedCornerShape(24.dp),
                enabled = isBirthdayValid
            ) {
                Text(
                    text = "Continue",
                    color = if (isBirthdayValid) Color.White else Color(0xFFB0B4BA),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Khu vực đăng ký bằng mạng xã hội
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
                        painter = painterResource(id = R.drawable.ic_google), // Icon Google
                        contentDescription = "Google",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_facebook), // Icon Facebook
                        contentDescription = "Facebook",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_github), // Icon Github
                        contentDescription = "Github",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Link chuyển sang màn hình đăng nhập
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

// Hàm preview cho màn hình đăng ký ngày sinh
@Preview(showBackground = true, name = "RegisterNameScreen Preview")
@Composable
fun PreviewBirthdayScreen() {
    MaterialTheme {
        RegisterBirthdayScreen(
            onSignUpSuccess = {},
            onBackClick = {}
        )
    }
}
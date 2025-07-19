// Gói chứa màn hình giao diện xác nhận mã code
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnjetpack.R
import com.example.learnjetpack.ui.components.StepProgressBar

// Hàm Composable cho màn hình xác nhận mã code
@Composable
fun RegisterConfirmCodeScreen(
    email: String = "john@doe.com", // Email để hiển thị
    onConfirmSuccess: () -> Unit,   // Callback khi xác nhận thành công
    onResendCode: () -> Unit,       // Callback khi gửi lại mã
    onBackClick: () -> Unit         // Callback khi bấm nút quay lại
) {
    // State lưu mã code 6 chữ số
    var code by remember { mutableStateOf(TextFieldValue("")) }
    // State cho hiệu ứng scale của nút Confirm
    val buttonScale by animateFloatAsState(
        targetValue = if (code.text.length == 6) 1f else 0.95f,
        animationSpec = tween(durationMillis = 20),
        label = "buttonScale"
    )

    // Tạo danh sách focus requesters cho 6 ô input
    val focusRequesters = remember { List(6) { FocusRequester() } }

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
                // Ảnh minh họa xác nhận code
                Image(
                    painter = painterResource(id = R.drawable.main_register),
                    contentDescription = "Confirmation Code Illustration",
                    modifier = Modifier.size(340.dp)
                )

                // Nút back nằm trên ảnh
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 8.dp, top = 8.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                color = Color.White.copy(alpha = 0.9f),
                                shape = CircleShape
                            )
                            .clickable { onBackClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_menu_back),
                            contentDescription = "Back",
                            modifier = Modifier.size(16.dp)
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
                text = "Enter Confirmation Code",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF232B35),
                modifier = Modifier.padding(vertical = 8.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Thanh tiến trình các bước đăng ký (tất cả đã hoàn thành)
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 600)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            StepProgressBar(
                steps = listOf("Email", "Name", "Birthday", "Gender", "Pass"),
                currentStep = 5, // Tất cả bước đã hoàn thành
                modifier = Modifier.fillMaxWidth(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Hướng dẫn nhập mã
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 800)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Enter the 6-digit code we sent to",
                    fontSize = 15.sp,
                    color = Color(0xFF232B35),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = email,
                    fontSize = 15.sp,
                    color = Color(0xFF232B35),
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nút gửi lại mã
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 900)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Text(
                text = "Resend code",
                fontSize = 15.sp,
                color = Color(0xFF232B35),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onResendCode() }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Ô nhập mã code 6 chữ số
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 1000)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(6) { index ->
                    CodeInputField(
                        value = if (index < code.text.length) code.text[index].toString() else "",
                        onValueChange = { newValue ->
                            if (newValue.length <= 1) {
                                val newCode = code.text.toMutableList()
                                if (newValue.isEmpty()) {
                                    if (index < newCode.size) {
                                        newCode.removeAt(index)
                                    }
                                } else {
                                    if (index < newCode.size) {
                                        newCode[index] = newValue[0]
                                    } else {
                                        newCode.add(newValue[0])
                                    }
                                }
                                code = TextFieldValue(newCode.joinToString(""))
                                
                                // Tự động nhảy sang ô tiếp theo khi nhập số
                                if (newValue.isNotEmpty() && index < 5) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }
                        },
                        isFocused = index == code.text.length,
                        focusRequester = focusRequesters[index],
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Nút Confirm, chỉ bật khi đã nhập đủ 6 chữ số
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(600, delayMillis = 1100)),
            exit = fadeOut(animationSpec = tween(600))
        ) {
            Button(
                onClick = {
                    if (code.text.length == 6) {
                        onConfirmSuccess()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .scale(buttonScale),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (code.text.length == 6) Color(0xFFFF7A3C) else Color(0xFFD3D3D3)
                ),
                shape = RoundedCornerShape(24.dp),
                enabled = code.text.length == 6
            ) {
                Text(
                    text = "Confirm",
                    color = if (code.text.length == 6) Color.White else Color(0xFFB0B4BA),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    // Tự động focus vào ô đầu tiên khi màn hình được load
    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }
}

// Composable cho từng ô nhập mã code
@Composable
fun CodeInputField(
    value: String,
    onValueChange: (String) -> Unit,
    isFocused: Boolean,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .background(
                color = Color(0xFFF2F3F7),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = if (isFocused) Color(0xFFFF7A3C) else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { focusRequester.requestFocus() },
        contentAlignment = Alignment.Center
    ) {
        // TextField ẩn để xử lý input và cursor
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF232B35), // Hiển thị text trong TextField
                textAlign = TextAlign.Center
            ),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    innerTextField()
                }
            }
        )
    }
}

// Hàm preview cho màn hình xác nhận mã code
@Preview(showBackground = true, name = "RegisterConfirmCodeScreen Preview")
@Composable
fun PreviewConfirmCodeScreen() {
    MaterialTheme {
        RegisterConfirmCodeScreen(
            email = "john@doe.com",
            onConfirmSuccess = {},
            onResendCode = {},
            onBackClick = {}
        )
    }
} 
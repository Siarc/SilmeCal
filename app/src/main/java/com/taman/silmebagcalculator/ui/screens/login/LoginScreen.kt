package com.taman.silmebagcalculator.ui.screens.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.taman.silmebagcalculator.R
import com.taman.silmebagcalculator.ui.components.LoginTextField
import com.taman.silmebagcalculator.ui.theme.Roboto
import com.taman.silmebagcalculator.ui.theme.SilmeBagCalculatorTheme
import com.taman.silmebagcalculator.utils.DashboardScreen
import org.koin.compose.viewmodel.koinViewModel

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    val fakeViewModel = remember { LoginScreenViewModel(FakeAuthRepository()) }

    SilmeBagCalculatorTheme {
        LoginScreen(viewModel = fakeViewModel, navController = navController)
    }
}

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel = koinViewModel(),
    navController: NavHostController
) {
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            TopSection()
            Spacer(modifier = Modifier.height(36.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                LoginSection(viewModel, navController)

                Box(
                    modifier = Modifier
                        .fillMaxHeight(fraction = 0.8f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.BottomCenter
                ) {

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xFF94A3B8),
                                    fontSize = 14.sp,
                                    fontFamily = Roboto,
                                    fontWeight = FontWeight.Normal
                                )
                            ) {
                                append("Don't have account?\nRequest your provider for access.")
                            }
                        }
                    )

                }

            }
        }

    }
}


@Composable
private fun LoginSection(
    viewModel: LoginScreenViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current

    // Collect stored values
    val savedEmail by viewModel.savedEmail.collectAsState(initial = "")
    val savedPassword by viewModel.savedPassword.collectAsState(initial = "")
    val savedRememberMe by viewModel.savedRememberMe.collectAsState(initial = false)

    Log.d("Rony2", "LoginSection: -> $savedEmail -> $savedPassword -> $savedRememberMe")

    var usernameEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    val emailError by viewModel.emailError.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LoginTextField(
        label = "Username/Email",
        text = usernameEmail,
        onTextChange = {
            usernameEmail = it
            viewModel.onEmailChanged(it)
        },
        hasError = emailError
    )
    Spacer(modifier = Modifier.height(15.dp))
    LoginTextField(
        label = stringResource(R.string.password),
        text = password,
        onTextChange = { password = it },
        isPassword = true
    )
    Spacer(modifier = Modifier.height(10.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Checkbox(
            checked = rememberMe,
            onCheckedChange = { rememberMe = it }
        )
        Text(
            text = "Remember Me",
            style = MaterialTheme.typography.bodyMedium
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        onClick = onClick@{

            if (emailError) return@onClick

            viewModel.login(usernameEmail, password, rememberMe)
        },
        shape = RoundedCornerShape(size = 4.dp)
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        } else {
            Text(
                text = stringResource(R.string.log_in),
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium)
            )
        }
    }

    // Update from datastore when flows emit
    LaunchedEffect(savedEmail) { usernameEmail = savedEmail }
    LaunchedEffect(savedPassword) { password = savedPassword }
    LaunchedEffect(savedRememberMe) { rememberMe = savedRememberMe }
    LaunchedEffect(Unit) {
        viewModel.loginEvents.collect { event ->
            if (event.success) {
                navController.navigate(DashboardScreen) {
                    popUpTo("login") { inclusive = true }
                }
            } else {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}


@Composable
private fun TopSection() {

    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.5f),
            painter = painterResource(id = R.drawable.shape),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )


        Row(
            modifier = Modifier.padding(top = 80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = stringResource(id = R.string.the_tolet),
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = stringResource(id = R.string.find_house),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }

        Text(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(alignment = Alignment.BottomCenter),
            text = stringResource(id = R.string.login),
            style = MaterialTheme.typography.headlineLarge,
        )
    }
}

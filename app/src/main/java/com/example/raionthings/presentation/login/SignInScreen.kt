package com.example.raionthings.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.raionthings.R
import com.example.raionthings.presentation.navigation.register
import com.example.raionthings.presentation.navigation.resetpassword
import com.example.raionthings.presentation.theme.ui.SFProdisplayFontFamily
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: SignInViewModel,
    state: SignInState,
    Email: EmailAuthUIClient,
    onSignInClick: () -> Unit,
   navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val icon = if(passwordVisibility)
        painterResource(id = R.drawable.eye)
    else
        painterResource(id = R.drawable.hide)
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            viewModel.resetState()
        }
    }
    Column (
        modifier = Modifier
        .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
    ){
        Spacer(modifier = Modifier.padding(20.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.sajigo),
                contentDescription = "SajigoLogo",
                modifier = Modifier.size(width = 209.dp, height = 120.dp)
            )
        }
        Spacer(modifier = Modifier.padding(30.dp))
        Text(
            text = "Login",
            color = Color.Black,
            fontSize = 31.96.sp,
            fontFamily = SFProdisplayFontFamily, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp)

        )

        Column (
            modifier = Modifier.fillMaxWidth().fillMaxHeight()

        ){
            //Username container
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Email",
                fontSize = 16.sp,
                fontFamily = SFProdisplayFontFamily
            )
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                placeholder = {Text(
                    text = "E-Mail",
                    color = Color(0xFF757575),
                    fontFamily = SFProdisplayFontFamily)},
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.quill_mail),
                        contentDescription = "Email Icon",
                        modifier = Modifier.size(width = 22.dp, height = 22.dp),
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .size(width = 372.dp, height = 56.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .focusRequester(emailFocusRequester)
                    .onKeyEvent { event ->
                        if (event.key == Key.Enter) {
                            passwordFocusRequester.requestFocus()
                            true
                        } else {
                            false
                        }
                    },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Email),
                keyboardActions = KeyboardActions(
                    onNext = { passwordFocusRequester.requestFocus() }
                ),
                shape = RoundedCornerShape(24.dp)
            )
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Kata Sandi",
                fontSize = 16.sp,
                fontFamily = SFProdisplayFontFamily
            )

            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                placeholder = {Text(
                    text = "Kata Sandi",
                    color = Color(0xFF757575),
                    fontFamily = SFProdisplayFontFamily)},
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(
                            painter = icon,
                            contentDescription = "Visibility Icon",
                            modifier = Modifier.size(width = 24.dp, height = 24.dp)
                        )
                    }
                },

                visualTransformation = if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.simple_line_icons_lock),
                        contentDescription = "Lock Icon",
                        modifier = Modifier.size(width = 22.dp, height = 22.dp),
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .size(width = 372.dp, height = 56.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .focusRequester(passwordFocusRequester),
                shape = RoundedCornerShape(24.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row (
                    modifier = Modifier.fillMaxWidth().padding( horizontal = 12.dp),
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(
                        text = "Lupa Kata Sandi ?",
                        fontSize = 14.sp,
                        fontFamily = SFProdisplayFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF632713),
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 12.dp)
                            .clickable {navController.navigate(resetpassword) },
                        textAlign = TextAlign.End

                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        if (email.isNotEmpty() && password.isNotEmpty()) {
                            viewModel.viewModelScope.launch {
                                viewModel.onGoogleSignInSuccess()
                                val result = Email.login(email, password)
                                viewModel.onSignInResult(result)
                            }
                        } else {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()                }
                    },
                    modifier = Modifier
                        .size(width = 372.dp, height = 56.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFC63433)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "Login",
                        fontFamily = SFProdisplayFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.padding(12.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Belum mempunyai akun? ",
                        fontFamily = SFProdisplayFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp
                    )
                    Text(
                        text = "Daftar",
                        fontFamily = SFProdisplayFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = Color(255 ,165, 0),
                        modifier = Modifier.clickable {
                            navController.navigate(register)
                        }
                    )
                }

            }
            Spacer(modifier = Modifier.padding(12.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Image(
                    painter = painterResource(id = R.drawable.line_6),
                    contentDescription = "line",
                    modifier = Modifier.padding(8.dp),

                    )

                Text(
                    text = "Atau dengan",
                    fontFamily = SFProdisplayFontFamily,
                    fontSize = 14.sp
                )

                Image(
                    painter = painterResource(id = R.drawable.line_6),
                    contentDescription = "line",
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick = onSignInClick,
                    modifier = Modifier
                        .size(width = 372.dp, height = 56.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFE3E3E3)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.logo_google_nobg),
                            contentDescription = "Logo Google",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Sign Up with Google",
                            fontFamily = SFProdisplayFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    state: SignInState,
    Email: EmailAuthUIClient,
    onSignInClick: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToReset: () -> Unit

) {
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val sfApple = FontFamily(
        Font(R.font.blackitalic, FontWeight.Black, FontStyle.Italic),
        Font(R.font.bold, FontWeight.Bold),
        Font(R.font.heavyitalic, FontWeight.ExtraBold, FontStyle.Italic),
        Font(R.font.lightitalic, FontWeight.Light, FontStyle.Italic),
        Font(R.font.medium, FontWeight.Medium),
    )


    // Show error messages
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            viewModel.resetState()
        }
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .padding(20.dp, 10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 25.dp)
                    .focusRequester(emailFocusRequester)
                    .onKeyEvent { event ->
                        if (event.key == Key.Enter) {
                            passwordFocusRequester.requestFocus()
                            true
                        } else {
                            false
                        }
                    },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.LightGray,
                    focusedContainerColor = Color.Gray
                ),
                label = { Text("Email",
                    fontFamily = sfApple,
                    fontWeight = FontWeight.SemiBold) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Email),
                keyboardActions = KeyboardActions(
                    onNext = { passwordFocusRequester.requestFocus() }
                )
            )
            Spacer(modifier = Modifier.height(60.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 25.dp)
                    .focusRequester(passwordFocusRequester)
                    .onKeyEvent { event ->
                        if (event.key == Key.Enter) {
                            if (email.isEmpty() || password.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Signed out",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                viewModel.viewModelScope.launch {
                                    viewModel.onGoogleSignInSuccess()
                                    val result = Email.login(email, password)
                                    viewModel.onSignInResult(result)
                                }
                            }
                            true
                        } else {
                            false
                        }
                    },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.LightGray,
                    focusedContainerColor = Color.Gray
                ),
                label = { Text("Password",
                    fontFamily = sfApple,
                    fontWeight = FontWeight.SemiBold) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions(
                    onDone = {
                        viewModel.viewModelScope.launch {
                            viewModel.onGoogleSignInSuccess()
                            val result = Email.login(email, password)
                            viewModel.onSignInResult(result)
                        }
                    }
                )
            )
            TextButton(onClick = onNavigateToReset){
                Text("Forgot ur password?")
            }
            Spacer(modifier = Modifier.height(60.dp))
            Button(onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.viewModelScope.launch {
                        viewModel.onGoogleSignInSuccess()
                        val result = Email.login(email, password)
                        viewModel.onSignInResult(result)
                    }
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()                }
            }) { Text("Login") }

            Spacer(modifier = Modifier.height(60.dp))
            TextButton(onClick = onNavigateToRegister) {
                Text("Don't have an account? Sign up here",
                    fontFamily = sfApple,
                    fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(2.dp))
            Button(onClick = onSignInClick) {
                Text("Sign In with google",
                    fontFamily = sfApple,
                    fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

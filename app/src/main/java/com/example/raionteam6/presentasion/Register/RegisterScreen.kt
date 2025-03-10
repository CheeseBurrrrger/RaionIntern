package com.example.raionteam6.presentasion.Register


import android.webkit.WebSettings.TextSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.raionteam6.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.w3c.dom.Text


@Composable
fun RegisterScreen() {
    var phonenumber by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var FirstName by remember { mutableStateOf("") }
    var LastName by remember { mutableStateOf("") }
    var Grade by remember { mutableStateOf("") }
    var checklist by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var term_and_policy by remember { mutableStateOf(false) }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    val formIsvalid = FirstName.isNotBlank() && LastName.isNotBlank()
            && email.isNotBlank() && username.isNotBlank() && password.isNotBlank()



    val PoppinsBold = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_black, FontWeight.Normal)
    )
    val Poppins = FontFamily(
        Font(R.font.poppins_medium, FontWeight.Bold),
    )
    val icon = if(passwordVisibility)
        painterResource(id = R.drawable.eye)
    else
        painterResource(id = R.drawable.hide)



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 19.dp)
            .background(color = Color.White)
    ) {

        //Sign Up text
        Text(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 8.dp),
            text = "Sign Up",
            fontSize = 31.96.sp,
            fontFamily = PoppinsBold
        )

        //New Column
        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            //FirstName containter
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "First Name",
                fontSize = 16.sp,
                fontFamily = Poppins
            )
            OutlinedTextField(
                value = FirstName,
                onValueChange = {FirstName = it},
                placeholder = {Text(text = "Enter the name" , color = Color(0xFF0078D7) , fontFamily = Poppins)},

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),
                singleLine = true,
                shape = RoundedCornerShape(24.dp)
            )

            //Lastname containter
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "Last Name",
                fontSize = 16.sp,
                fontFamily = Poppins
            )

            OutlinedTextField(
                value = LastName,
                onValueChange = {LastName = it},
                placeholder = {Text(text = "Enter the lastname" , color = Color(0xFF0078D7), fontFamily = Poppins)},

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),
                singleLine = true,
                shape = RoundedCornerShape(24.dp)
            )

            Row (
            ) {
                Checkbox(
                    checked = checklist,
                    onCheckedChange = { checklist = it }
                )

                Text(
                    text = "I Have School Membership Number \n" +
                            "( Optional )",
                    fontSize = 14.sp,
                    fontFamily = Poppins,
                    modifier = Modifier.padding(vertical = 5.dp)

                )
            }
            Spacer(modifier = Modifier.height(20.dp)) //enter
            //Phone Number containter
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "Phone Number (Optional)",
                fontSize = 16.sp,
                fontFamily = Poppins
            )
            OutlinedTextField(
                value = phonenumber,
                onValueChange = {phonenumber = it},
                placeholder = {Text(text = "Enter the phone number" , color = Color(0xFF0078D7), fontFamily = Poppins)},

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),
                singleLine = true,
                shape = RoundedCornerShape(24.dp)
            )
            //Email Adress containter
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "Email Address",
                fontSize = 16.sp,
                fontFamily = Poppins
            )
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                placeholder = {Text(text = "Enter the email" , color = Color(0xFF0078D7), fontFamily = Poppins)},

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),
                singleLine = true,
                shape = RoundedCornerShape(24.dp)
            )
            //Username containter
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "Username",
                fontSize = 16.sp,
                fontFamily = Poppins
            )
            OutlinedTextField(
                value = username,
                onValueChange = {username = it},
                placeholder = {Text(text = "Enter the username" , color = Color(0xFF0078D7), fontFamily = Poppins)},

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),
                singleLine = true,
                shape = RoundedCornerShape(24.dp)
            )
            //password container
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "Password",
                fontSize = 16.sp,
                fontFamily = Poppins
            )
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                placeholder = {Text(text = "Enter the Password" , color = Color(0xFF0078D7), fontFamily = Poppins)},
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

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(24.dp)
                    ),
                singleLine = true,
                shape = RoundedCornerShape(24.dp)
            )
            Row (

            ) {
                Checkbox(
                    checked = term_and_policy,
                    onCheckedChange = { term_and_policy = it }
                )

                Text(
                    text = "You have read understood and agree to our " + "Terms & Privacy Policy",
                    fontSize = 14.sp,
                    fontFamily = Poppins,
                    modifier = Modifier.padding(vertical = 5.dp)

                )
            }

            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick = {},
                    modifier = Modifier
                        .size(width = 372.19.dp, height = 80.dp)
                        .padding(vertical = 12.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF0078D7)),
                    shape = RoundedCornerShape(18.dp),
                    enabled = term_and_policy && formIsvalid
                ) {
                    Text(
                        text = "Sign Up",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp
                    )
                }
            }

        }

    }
}

@Preview
@Composable
fun registerscreenpreview(){
    RegisterScreen()
}



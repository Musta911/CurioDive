@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.curiodive.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.auth.result.AuthSignUpResult
import com.amplifyframework.core.Amplify
import com.example.curiodive.R
import com.example.curiodive.awsmanger.AmplifyManager
import com.example.curiodive.login.ui.theme.CurioDiveTheme

@Composable
fun LoginScreen(navController: NavController?) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(modifier = Modifier.size(250.dp), onDraw = {
            drawCircle(color = Color.Black)
        })
        Spacer(modifier = Modifier.size(100.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {email = it},
            label = { Text("Email")},
            leadingIcon = { Icon(imageVector = Icons.Filled.Person, null)}
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {password = it},
            label = { Text("Password")},
            leadingIcon = { Icon(imageVector = Icons.Filled.Lock, null)},
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, null)
                }
            }

        )
        TextButton(onClick = { navController?.navigate("remember_password") }, modifier = Modifier.align(Alignment.End)) {
            Text(text = "Don't remember password?")
        }
        Button(onClick = {
                        val result = AmplifyManager.Auth.signIn(email.text,password.text)
                        if (result) {
                            Toast.makeText(context,"Login effettuato",Toast.LENGTH_LONG).show()
                            navController?.navigate("home")
                        } else {
                            Toast.makeText(context,"Email o password errati", Toast.LENGTH_LONG).show()
                        }


                         /*GlobalScope.launch(Dispatchers.Main) {
                             val result = AmplifyManager.Auth.signIn(email.text,password.text)
                             if (result) {
                                 Toast.makeText(context,"Login effettuato",Toast.LENGTH_LONG).show()
                                 navController.navigate("home")
                             } else {
                                 Toast.makeText(context,"Email o password errati", Toast.LENGTH_LONG).show()
                             }
                         }

                          */
                         },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Sign in")
        }
        Text(text = "Or")
        Button(onClick = { /*TODO*/ }) {
            Row(modifier = Modifier.padding(5.dp)) {
                Image(painter = painterResource(id = R.drawable.google), contentDescription = null, modifier = Modifier.size(30.dp))

                Text(text = "Sign in with Google", modifier = Modifier.align(Alignment.CenterVertically))
            }
        }

        Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.Bottom) {
            Text(text = "Don't have an account?", modifier = Modifier.padding(10.dp))
            TextButton(onClick = { navController?.navigate("signup") }, modifier = Modifier.padding(0.dp)) {
                Text(text = "Sign up")
            }
        }


    }
}
 fun abc(a : Boolean) {

 }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }
    var checkPrivacy by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordConfirmVisible by remember { mutableStateOf(false) }
    var passwordError by rememberSaveable { mutableStateOf(false) }
    var emailError by rememberSaveable { mutableStateOf(false) }
    var usernameError by rememberSaveable { mutableStateOf(false) }
    var checkError by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current


    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {
        Spacer(modifier = Modifier.size(200.dp))
        OutlinedTextField(

            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {
                email = it
                emailError = false
                            },
            label = {Text("Email")},
            isError = emailError


        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = {
                username = it
                usernameError = false
                            },
            label = {Text("Username")},
            isError = usernameError

        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {password = it},
            label = {Text("Password")},
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                // Toggle button to hide or display password
                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, description)
                }
            }

        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            value = passwordConfirm,
            onValueChange = {
                passwordConfirm = it
                passwordError = false
                            },
            label = {Text("Confirm password")},
            visualTransformation = if (passwordConfirmVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordConfirmVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff


                if (!passwordError) {
                    IconButton(onClick = {passwordConfirmVisible = !passwordConfirmVisible}){
                        Icon(imageVector  = image, contentDescription = null)
                    }
                } else {
                    IconButton(onClick = {passwordConfirmVisible = !passwordConfirmVisible}){
                        Icon(imageVector  = image, contentDescription = null, tint = Color.Red)
                    }
                }

            },

            isError = passwordError,
        )
        if (passwordError) {
            Text(text = "Le password non sono uguali", color = Color.Red)
        }
        Row(verticalAlignment = Alignment.CenterVertically){
            Checkbox(checked = checkPrivacy, onCheckedChange = {
                checkPrivacy = it
                checkError = false
                                                               },
                colors = CheckboxDefaults.colors(uncheckedColor = if(checkError) Color.Red else MaterialTheme.colorScheme.primary))
            Text(text = "I have read and agree the Privacy Policy")
        }

        if (checkError) {
            Text(text = "Devi confermare di aver letto e di accettare i Termini d'Uso", color = Color.Red)
        }

        Button(onClick = {
            emailError = email.isBlank()
            usernameError = email.isBlank()
            checkError = !checkPrivacy
            passwordError = password != passwordConfirm
            if (!emailError && !usernameError && !checkError && !passwordError) {
                if (signUp(email,password,username)) {
                    navController.navigate("confirm_signup/$email")
                }
            }

        }) {
            Text(text = "Sign up")
        }
        Text("Or")
        Button(onClick = {}) {
            Text(text = "Sign up using Google")
        }
        Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxHeight()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Already have an account?")
                TextButton(onClick = {navController?.navigate("login")}) {
                    Text("Sign in")
                }
            }
        }
    }

}


fun signUp(email : String, password : String, username : String) : Boolean {
    try {
        Amplify.Auth.signUp(
            email,
            password,
            AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.preferredUsername(), username)
                .build(),
            { result: AuthSignUpResult ->
                Log.i(
                    "Amplify Auth",
                    "Result: $result"
                )
            }
        ) { error: AuthException? ->
            Log.e(
                "Amplify Auth",
                "Sign up failed",
                error
            )
        }
        return true
    } catch (e : Exception) {
        return false
    }
}

fun confirmSignUp(email: String, code : String) : Boolean {
    try {
        Amplify.Auth.confirmSignUp(
            email, code,
            { result ->
                if (result.isSignUpComplete) {
                    Log.i("AuthQuickstart", "Confirm signUp succeeded")
                } else {
                    Log.i("AuthQuickstart", "Confirm sign up not complete")
                }
            },
            { Log.e("AuthQuickstart", "Failed to confirm sign up", it) }
        )
        return true
    } catch (e: Exception) {
        return false
    }
}

@Composable
fun RegisterConfirmScreen(navController: NavController?, email: String) {
    var confirmCode by remember { mutableStateOf("") }
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(10.dp)) {
        Text("Inserisci il codice di conferma che abbiamo inviato all'indirizzo email:\n$email")
        OutlinedTextField(
            value = confirmCode,
            onValueChange = {confirmCode = it},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = {Text("Confirm code")}
        )
        Button(onClick = { confirmSignUp(email,confirmCode) }) {
            Text("Done")
        }
    }
}


@Composable
fun RememberScreen(navController: NavController) {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    CurioDiveTheme {
       RegisterConfirmScreen(navController = null,"a")
    }
}
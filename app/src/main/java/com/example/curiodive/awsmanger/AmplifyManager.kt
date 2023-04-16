package com.example.curiodive.awsmanger

import android.util.Log
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthUserAttribute
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.auth.result.AuthSignInResult
import com.amplifyframework.auth.result.AuthSignOutResult
import com.amplifyframework.auth.result.AuthSignUpResult
import com.amplifyframework.core.Amplify
import java.util.concurrent.CountDownLatch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object AmplifyManager {
    object Auth {
        private fun getUserAttributes(): List<AuthUserAttribute> {
            val attributes = mutableListOf<AuthUserAttribute>()
            val latch = CountDownLatch(1)
            Amplify.Auth.fetchUserAttributes(
                { result ->
                    attributes.addAll(result)
                    latch.countDown()
                },
                { error ->
                    Log.e("AuthQuickstart", error.toString())
                    latch.countDown()
                }
            )
            latch.await()
            return attributes
        }
        fun isSignedIn(): Boolean {
            var isSignedIn = false
            val latch = CountDownLatch(1)
            Amplify.Auth.fetchAuthSession( //è asincrono quindi si usa CountDownLatch
                { result ->
                    isSignedIn = result.isSignedIn
                    latch.countDown() //attende che la chiamata sia completata prima di restituire il risultato
                },
                { error ->
                    Log.e("AuthQuickstart", error.toString())
                    latch.countDown()
                }
            )
            latch.await()
            return isSignedIn
        }
        fun signIn(email: String, password: String): Boolean {
            var result = false
            val latch = CountDownLatch(1)
            Amplify.Auth.signIn(
                email,
                password,
                {
                    result = true
                    latch.countDown()
                },
                {
                    result = false
                    latch.countDown()
                }
            )
            latch.await()
            return result
        }

        /*suspend fun signIn(email: String, password: String): Boolean {
            return suspendCoroutine { continuation ->
                Amplify.Auth.signIn(
                    email,
                    password,
                    { continuation.resume(true) },
                    { continuation.resume(false) }
                )
            }
        }

         */
        fun signOut() {
            Amplify.Auth.signOut { onComplete: AuthSignOutResult? ->
                Log.i(
                    "Amplify Auth",
                    "Signed out successfully completed"
                )
            }
        }
        fun confirmSignUp(email: String, code : String) : Boolean {
            val latch = CountDownLatch(1)
            var res = false
            Amplify.Auth.confirmSignUp(
                email, code,
                { result ->
                    if (result.isSignUpComplete) {
                        Log.i("AuthQuickstart", "Confirm signUp succeeded")
                        res = true
                        latch.countDown()
                    } else {
                        Log.i("AuthQuickstart", "Confirm sign up not complete")
                        res = false
                        latch.countDown()
                    }
                },
                {
                    Log.e("AuthQuickstart", "Failed to confirm sign up", it)
                    res = false
                }
            )
            latch.await()
            return res
        }
        fun signUp(email : String, password : String, username : String) : Boolean {
            val latch = CountDownLatch(1)
            var res = false

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
                        latch.countDown()
                        res = true
                    }
                ) { error: AuthException? ->
                    Log.e(
                        "Amplify Auth",
                        "Sign up failed",
                        error
                    )
                    latch.countDown()
                    res = false

                }
                latch.await()
                return res
            } catch (e : Exception) {
                latch.await()
                return false
            }
        }
        fun getEmail(): String? {
            val attributes = getUserAttributes()
            return try {
                attributes.find { it.key == AuthUserAttributeKey.email() }?.value
            } catch (e : Exception) {
                null
            }

        }
        fun getUsername(): String? {
            val attributes = getUserAttributes()
            return try {
                attributes.find { it.key == AuthUserAttributeKey.preferredUsername() }?.value
            } catch (e : Exception) {
                null
            }
        }
    }

    object Database {

    }
}
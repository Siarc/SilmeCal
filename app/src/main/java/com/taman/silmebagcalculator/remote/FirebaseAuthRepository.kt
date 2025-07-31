package com.taman.silmebagcalculator.remote

import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthRepository(
    private val auth: FirebaseAuth
) : AuthRepository {

    override fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, "Login successful")
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }
}
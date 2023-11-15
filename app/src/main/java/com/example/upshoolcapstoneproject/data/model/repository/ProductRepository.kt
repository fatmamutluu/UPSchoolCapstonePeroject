package com.example.upshoolcapstoneproject.data.model.repository

import com.example.upshoolcapstoneproject.data.ProductService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProductRepository(private val firebaseAuth: ProductService,
                        private val firebaseFirestore: FirebaseFirestore
) {

    fun isUserLoggedIn() = firebaseAuth.currentUser != null

    fun logOut() = firebaseAuth.signOut()

    fun getCurrentUserId() = firebaseAuth.currentUser?.uid.orEmpty()

    suspend fun signIn(email: String, password: String): com.example.upshoolcapstoneproject.common.Resource<Boolean> =
        withContext(Dispatchers.IO) {
            try {
                val authSignIn = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                if (authSignIn.user != null) {
                    com.example.upshoolcapstoneproject.common.Resource.Success(true)
                } else {
                    com.example.upshoolcapstoneproject.common.Resource.Success(false)
                }
            } catch (e: Exception) {
                com.example.upshoolcapstoneproject.common.Resource.Error(e.message.orEmpty())
            }
        }

    suspend fun signUp(email: String, password: String): com.example.upshoolcapstoneproject.common.Resource<Boolean> =
        withContext(Dispatchers.IO) {
            try {
                val authSignUp =
                    firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                if (authSignUp.user != null) {
                    saveUserToFirestore(authSignUp.user!!.uid, email)
                    com.example.upshoolcapstoneproject.common.Resource.Success(false)
                } else {
                    com.example.upshoolcapstoneproject.common.Resource.Success(true)
                }
            } catch (e: Exception) {
                com.example.upshoolcapstoneproject.common.Resource.Error(e.message.orEmpty())
            }
        }

    suspend fun getUser(userId: String): com.example.upshoolcapstoneproject.common.Resource<User> = withContext(Dispatchers.IO) {
        try {
            val documentSnapshot =
                firebaseFirestore.collection("users").document(userId).get().await()

            if (documentSnapshot.exists()) {
                val user = documentSnapshot.toObject(User::class.java)
                if (user != null) {
                    com.example.upshoolcapstoneproject.common.Resource.Success(user.copy(userId = documentSnapshot.id))
                } else {
                    com.example.upshoolcapstoneproject.common.Resource.Error("User information is invalid.")
                }
            } else {
                com.example.upshoolcapstoneproject.common.Resource.Error("User not found")
            }
        } catch (e: Exception) {
            com.example.upshoolcapstoneproject.common.Resource.Error(e.message.orEmpty())
        }
    }

    suspend fun saveUserToFirestore(userId: String, email: String) {
        val userMap = hashMapOf(
            "email" to email
        )

        firebaseFirestore.collection("users").document(userId).set(userMap).await()
    }
}
package com.newspost.service

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.newspost.model.Post

class Api {

    var responseSucess: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var responseError: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var responseData: MutableLiveData<List<Post>> = MutableLiveData<List<Post>>()

    fun addPost(titulo: String, data: String, noticia: String, autor: String) {
        val db = Firebase.firestore
        val user = hashMapOf(
            "titulo" to titulo,
            "data" to data,
            "noticia" to noticia,
            "autor" to autor
        )

        db.collection("post")
            .add(user)
            .addOnSuccessListener { documentReference ->
                responseSucess.postValue(true)
                responseError.postValue(false)
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                responseSucess.postValue(false)
                responseError.postValue(true)
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun getPost() {
        val db = Firebase.firestore

        db.collection("post")
            .get()
            .addOnSuccessListener { result ->
                responseSucess.postValue(true)
                responseError.postValue(false)
                var post = mutableListOf<Post>()

                for (document in result) {
                    var data = document.data
                    post.add(
                        Post(
                            data["titulo"].toString(),
                            data["noticia"].toString(),
                            data["data"].toString(),
                            data["autor"].toString(),
                        )
                    )
                }
                responseData.postValue(post)
            }
            .addOnFailureListener { exception ->
                responseSucess.postValue(false)
                responseError.postValue(true)
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}
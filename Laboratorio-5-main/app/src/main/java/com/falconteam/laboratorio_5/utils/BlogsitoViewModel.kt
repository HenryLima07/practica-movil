package com.falconteam.laboratorio_5.utils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.laboratorio_5.data.database.InitDatabase
import com.falconteam.laboratorio_5.data.database.Entity.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class BlogsitoViewModel : ViewModel() {
    private val db = InitDatabase.database

    private val _listPosts = MutableStateFlow<List<Post>>(emptyList())
    val listPosts = _listPosts.asStateFlow()
    val showModal = mutableStateOf(false)
    val newPostTitle = mutableStateOf("")
    val newPostDescription = mutableStateOf("")

    fun showModal() {
        showModal.value = !showModal.value
    }

    private fun cleanFields() {
        newPostDescription.value = ""
        newPostTitle.value = ""
    }


    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = db.postDao().getAllPosts().firstOrNull()

            if (result?.isEmpty() == true) {
                return@launch
            }

            db.postDao().getAllPosts().collect { listPosts ->
                _listPosts.value = listPosts
            }
        }
    }

    fun addNewPost() {
        if (newPostDescription.value.isEmpty()) {
            return
        }
        if (newPostTitle.value.isEmpty()) {
            return
        }

        val newPost = Post(
            title = newPostTitle.value,
            description = newPostDescription.value,
            author = "EnSerMirro"
        )

        viewModelScope.launch(Dispatchers.IO) {
            db.postDao().insertPost(newPost)
            getPosts()
        }

        cleanFields()

    }

    fun updatePost(title: String, description: String, postId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            db.postDao().updateSelected(title, description, postId)
        }
    }

    fun deletePost(postId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            db.postDao().deletePostById(postId)
//           //TODO : refresh list getPosts()
        }
    }
}
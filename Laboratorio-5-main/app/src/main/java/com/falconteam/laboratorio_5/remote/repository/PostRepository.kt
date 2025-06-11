package com.falconteam.laboratorio_5.remote.repository

import com.falconteam.laboratorio_5.remote.resource.Resource
import com.falconteam.laboratorio_5.remote.services.AddCommentResponse
import com.falconteam.laboratorio_5.remote.services.AddPostResponse
import com.falconteam.laboratorio_5.remote.services.DeletePostResponse
import com.falconteam.laboratorio_5.remote.services.GetAllPostWithCommentsResponse

interface PostRepository {
    suspend fun getAllPostsWithComments(token: String): Resource<List<GetAllPostWithCommentsResponse>>
    suspend fun addPost(token: String, title: String, description: String): Resource<AddPostResponse>
    suspend fun addComment(token: String, postId: String, comment: String): Resource<AddCommentResponse>
    suspend fun deletePost(token: String, postId: String): Resource<DeletePostResponse>
}
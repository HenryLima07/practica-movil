package com.falconteam.laboratorio_5.remote.repository
import com.falconteam.laboratorio_5.remote.resource.Resource
import com.falconteam.laboratorio_5.remote.services.AddCommentRequest
import com.falconteam.laboratorio_5.remote.services.AddCommentResponse
import com.falconteam.laboratorio_5.remote.services.AddPostRequest
import com.falconteam.laboratorio_5.remote.services.AddPostResponse
import com.falconteam.laboratorio_5.remote.services.DeletePostRequest
import com.falconteam.laboratorio_5.remote.services.DeletePostResponse
import com.falconteam.laboratorio_5.remote.services.GetAllPostWithCommentsResponse
import com.falconteam.laboratorio_5.remote.services.PostService
import com.google.gson.Gson

class PostRepositoryImpl(
    private val postService: PostService,
    private val gson: Gson
) : PostRepository {
    override suspend fun getAllPostsWithComments(token: String): Resource<List<GetAllPostWithCommentsResponse>> {
        try {
            val response = postService.getAllPostsWithComments("Bearer $token")
            if (response.isSuccessful) {
                val getAllPostWithCommentsResponse = response.body()!!
                return Resource.Success(getAllPostWithCommentsResponse)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = if (errorBody != null) {
                    try {
                        val errorResponse = gson.fromJson(errorBody, GetAllPostWithCommentsResponse::class.java)
                        errorResponse.messages
                    } catch (e: Exception) {
                        "Error message could not be obtained"
                    }
                } else {
                    "Error message not found"
                }
                return Resource.Error(errorMessage.toString())
            }
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage ?: "Network error")
        }
    }

    override suspend fun addPost(
        token: String,
        title: String,
        description: String
    ): Resource<AddPostResponse> {
        val addPostRequest = AddPostRequest(
            tittle = title,
            description = description
        )

        try {
            val response = postService.addPost("Bearer $token", addPostRequest)
            if (response.isSuccessful) {
                val addPostResponse = response.body()!!
                return Resource.Success(addPostResponse)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = if (errorBody != null) {
                    try {
                        val errorResponse = gson.fromJson(errorBody, AddPostResponse::class.java)
                        errorResponse.message
                    } catch (e: Exception) {
                        "Error message could not be obtained"
                    }
                } else {
                    "Error message not found"
                }
                return Resource.Error(errorMessage)
            }
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage ?: "Network error")
        }
    }

    override suspend fun addComment(
        token: String,
        postId: String,
        comment: String
    ): Resource<AddCommentResponse> {
        val addCommentRequest = AddCommentRequest(
            postId = postId,
            comment = comment
        )

        try {
            val response = postService.addComment("Bearer $token", addCommentRequest)
            if (response.isSuccessful) {
                val addCommentResponse = response.body()!!
                return Resource.Success(addCommentResponse)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = if (errorBody != null) {
                    try {
                        val errorResponse = gson.fromJson(errorBody, AddCommentResponse::class.java)
                        errorResponse.message
                    } catch (e: Exception) {
                        "Error message could not be obtained"
                    }
                } else {
                    "Error message not found"
                }
                return Resource.Error(errorMessage)
            }
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage ?: "Network error")
        }
    }

    override suspend fun deletePost(token: String, postId: String): Resource<DeletePostResponse> {
        val deletePostRequest = DeletePostRequest(
            postId = postId
        )

        try {
            val response = postService.deletePost("Bearer $token", deletePostRequest)
            if (response.isSuccessful) {
                val deletePostResponse = response.body()!!
                return Resource.Success(deletePostResponse)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = if (errorBody != null) {
                    try {
                        val errorResponse = gson.fromJson(errorBody, DeletePostResponse::class.java)
                        errorResponse.message
                    } catch (e: Exception) {
                        "Error message could not be obtained"
                    }
                } else {
                    "Error message not found"
                }
                return Resource.Error(errorMessage)
            }
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage ?: "Network error")
        }
    }

}

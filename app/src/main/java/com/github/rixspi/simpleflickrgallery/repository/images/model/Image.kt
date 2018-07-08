package com.github.rixspi.simpleflickrgallery.repository.images.model

import com.google.gson.annotations.SerializedName


data class Image(
        val description: String?,
        val title: String?,
        val link: String?,
        val media: Media?,
        @SerializedName("date_taken")
        val dateTaken: String?,
        val author: String?,
        @SerializedName("author_id")
        val authorId: String?,
        val tags: String?
){
    fun id(): String = authorId + media?.m
}

data class Media(
        val m: String
)

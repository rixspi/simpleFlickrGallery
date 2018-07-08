package com.github.rixspi.simpleflickrgallery.repository.images.model

import com.google.gson.annotations.SerializedName


data class Image(
        val description: String? = null,
        val title: String? = null,
        val link: String? = null,
        val media: Media? = null,
        @SerializedName("date_taken")
        val dateTaken: String? = null,
        val author: String? = null,
        @SerializedName("author_id")
        val authorId: String? = null,
        val tags: String? = null
) {
    fun id(): String = authorId + media?.m
}

data class Media(
        val m: String
)

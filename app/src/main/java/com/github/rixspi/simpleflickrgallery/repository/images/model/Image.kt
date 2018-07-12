package com.github.rixspi.simpleflickrgallery.repository.images.model

import com.github.rixspi.simpleflickrgallery.repository.base.RepositoryModel
import com.google.gson.annotations.SerializedName
import java.util.*


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
) : RepositoryModel {
    fun id(): String =
            if (authorId.isNullOrBlank() || media?.m.isNullOrBlank()) {
                UUID.randomUUID().toString()
            } else {
                authorId + media!!.m
            }

    fun getBiggerUrl(): String? = media?.m?.let {
        if (it.length > 5) it.dropLast(5).plus("b.jpg")
        else it
    }
}

data class Media(
        val m: String? = null
)

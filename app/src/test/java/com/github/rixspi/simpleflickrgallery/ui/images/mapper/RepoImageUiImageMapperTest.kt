package com.github.rixspi.simpleflickrgallery.ui.images.mapper


import com.github.rixspi.simpleflickrgallery.BaseTest
import com.github.rixspi.simpleflickrgallery.repository.images.model.Image
import com.github.rixspi.simpleflickrgallery.repository.images.model.Media
import junit.framework.Assert.assertEquals
import org.junit.Test


class RepoImageUiImageMapperTest : BaseTest() {
    private val repoImageMapper = RepoImageMapper()


    val validImage = Image(
            author = "author",
            authorId = "authorId",
            media = Media(m = "mediaLink")
    )

    val invalidImage = Image(
            author = "author",
            authorId = "authorId",
            media = Media(m = null)
    )

    val invalidImage2 = Image(
            author = "author",
            authorId = "authorId",
            media = null
    )

    @Test
    fun `valid image is mapped correctly`() {
        val uiImage = repoImageMapper.mapToEntity(validImage)

        assertEquals(uiImage.id, validImage.id())
        assertEquals(uiImage.author, validImage.author)
        assertEquals(uiImage.url, validImage.media!!.m)
    }

    @Test(expected = IllegalStateException::class)
    fun `invalid image throws exception`() {
        repoImageMapper.mapToEntity(invalidImage)
    }

    @Test(expected = IllegalStateException::class)
    fun `invalid image the second throws exception`() {
        repoImageMapper.mapToEntity(invalidImage2)
    }

}
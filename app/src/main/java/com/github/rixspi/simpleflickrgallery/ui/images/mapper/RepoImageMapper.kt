package com.github.rixspi.simpleflickrgallery.ui.images.mapper

import com.github.rixspi.simpleflickrgallery.repository.images.model.Image
import com.github.rixspi.simpleflickrgallery.ui.base.Mapper
import com.github.rixspi.simpleflickrgallery.ui.images.model.UiImage
import javax.inject.Inject


class RepoImageMapper @Inject constructor() : Mapper<Image, UiImage>() {
    override fun mapToEntity(resultModel: Image): UiImage =
            resultModel.filterRequiredParametersOrThrow()
                    .let {
                        UiImage(
                                id = it.id(),
                                url = it.media!!.m!!,
                                urlToBiggerImage = it.getBiggerUrl(),
                                author = it.author,
                                description = it.description,
                                name = it.title,
                                tags = it.tags
                        )
                    }

    override fun mapToResultModel(entity: UiImage): Image = Image()

    private fun Image.filterRequiredParametersOrThrow() =
            if (this.media != null &&
                    this.media.m != null) this
            else throw IllegalStateException("Mapping error")

}
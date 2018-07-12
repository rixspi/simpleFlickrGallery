package com.github.rixspi.simpleflickrgallery.ui.base

import com.github.rixspi.simpleflickrgallery.repository.base.RepositoryModel


abstract class Mapper<ModelClass : RepositoryModel, EntityClass : UiModel> {

    abstract fun mapToEntity(resultModel: ModelClass): EntityClass
    abstract fun mapToResultModel(entity: EntityClass): ModelClass

    fun mapToEntity(resultModelList: List<ModelClass>) =
            resultModelList.map { mapToEntity(it) }

    fun mapToResultModel(entityList: List<EntityClass>) =
            entityList.map { mapToResultModel(it) }
}
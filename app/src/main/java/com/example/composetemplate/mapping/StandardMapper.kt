package com.example.composetemplate.mapping

import com.example.composetemplate.database.StandardEntity
import com.example.composetemplate.models.GetStandardJSONResponseItem

object StandardMapper {

    fun buildFrom(response: GetStandardJSONResponseItem): StandardEntity{
        return StandardEntity(
            id = response.id,
            userId = response.userId,
            title = response.title,
            completed = response.completed
        )

    }
}
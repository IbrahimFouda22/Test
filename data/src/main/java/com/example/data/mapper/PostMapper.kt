package com.example.data.mapper

import com.example.data.dto.PostDto
import com.example.domain.entity.Post

fun List<PostDto>.toEntity():List<Post>{
    return map {
        Post(
            id = it.id,
            userId = it.userId,
            title = it.title,
            body = it.body,
        )
    }
}

fun PostDto.toEntity():Post{
    return Post(
        id = id,
        userId = userId,
        title = title,
        body = body,
    )
}
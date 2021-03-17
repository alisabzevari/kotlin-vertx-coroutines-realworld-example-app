package conduit.model

import java.time.OffsetDateTime

data class ArticleDto(
    val slug: ArticleSlug,
    val title: ArticleTitle,
    val description: ArticleDescription,
    val body: ArticleBody,
    val tagList: List<ArticleTag>,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
    val favorited: Boolean,
    val favoritesCount: Int,
    val author: Profile
)

data class CommentDto(
    val id: Int,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
    val body: CommentBody,
    val author: Profile
)

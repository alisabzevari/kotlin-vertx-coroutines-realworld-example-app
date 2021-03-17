package conduit

import conduit.model.*
import conduit.utils.*
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import org.intellij.lang.annotations.Language
import java.time.OffsetDateTime

class JsonMapperTest : StringSpec() {
    val time = OffsetDateTime.now()

    override fun beforeEach(testCase: TestCase) {
        configureJson()
    }

    init {
        "ArticleDto model" {
            @Language("JSON")
            val jsonArticle = """
                {
                  "slug": "article-title",
                  "title": "article title",
                  "description": "article description",
                  "body": "article body",
                  "tagList": ["tag-1", "tag-2"],
                  "createdAt": "${time.iso}",
                  "updatedAt": "${time.iso}",
                  "favorited": false,
                  "favoritesCount": 0,
                  "author": {
                    "username": "johnjacob",
                    "bio": "",
                    "following": false
                  }
               }
            """.trimIndent()

            val expectedArticle = ArticleDto(
                ArticleSlug("article-title"),
                ArticleTitle("article title"),
                ArticleDescription("article description"),
                ArticleBody("article body"),
                listOf(ArticleTag("tag-1"), ArticleTag("tag-2")),
                createdAt = time.toUtc(),
                updatedAt = time.toUtc(),
                favorited = false,
                favoritesCount = 0,
                Profile(
                    Username("johnjacob"),
                    Bio(""),
                    image = null,
                    following = false
                )
            )

            val article = jsonArticle.fromJson<ArticleDto>()

            article shouldBe expectedArticle
            article.toJsonString() shouldEqualJson jsonArticle
        }
    }
}

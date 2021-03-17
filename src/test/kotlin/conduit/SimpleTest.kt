package conduit

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SimpleTest: StringSpec({
    "should work" {
        "value" shouldBe "value"
    }
})

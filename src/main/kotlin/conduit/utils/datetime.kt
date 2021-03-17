package conduit.utils

import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

val OffsetDateTime.iso: String
    get() = DateTimeFormatter.ISO_INSTANT.format(this)

fun OffsetDateTime.toUtc() = this.withOffsetSameInstant(ZoneOffset.UTC)

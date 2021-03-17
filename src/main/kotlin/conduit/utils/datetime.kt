package conduit.utils

import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

val OffsetDateTime.isoFormat: String
    get() = DateTimeFormatter.ISO_INSTANT.format(this)

fun OffsetDateTime.toUtc() = this.withOffsetSameInstant(ZoneOffset.UTC)

fun String.toOffsetDateTime() = Instant.parse(this).atOffset(ZoneOffset.UTC)

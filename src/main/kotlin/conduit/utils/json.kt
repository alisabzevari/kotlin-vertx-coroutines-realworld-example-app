package conduit.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.afterburner.AfterburnerModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.vertx.core.json.Json

fun ObjectMapper.config() {
    this.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    this.registerModule(KotlinModule())
    this.registerModule(AfterburnerModule())

    this.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
    this.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
    this.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true)
    this.registerModule(JavaTimeModule())
    this.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
}

fun configureJson() {
    val mapper = io.vertx.core.json.jackson.DatabindCodec.mapper()
    mapper.config()
}

inline fun <reified T : Any> String.fromJson(): T = Json.decodeValue(this, T::class.java)

fun Any.toJsonString() = Json.encode(this)

fun String.asJsonTree() = io.vertx.core.json.jackson.DatabindCodec.mapper().readTree(this)


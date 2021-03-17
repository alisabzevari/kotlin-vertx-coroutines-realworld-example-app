package conduit

import conduit.config.AppConfig
import conduit.utils.configureJson
import io.vertx.core.Vertx
import org.apache.logging.log4j.core.config.Configurator
import org.slf4j.LoggerFactory

fun main() {
    startApp(conduit.config.local)
}

fun startApp(config: AppConfig) {
    Configurator.initialize(null, config.logConfig)
    val logger = LoggerFactory.getLogger("main")

    configureJson()

    val vertx = Vertx.vertx().exceptionHandler { logger.error("Exception thrown creating vertx", it) }
    val routes = routes(vertx)

    val server = vertx.createHttpServer()

    server.requestHandler(routes).listen(config.port) {
        if (it.succeeded()) {
            logger.info("Listening on http://localhost:${config.port}")
        } else {
            logger.error("Exception thrown creating http server", it.cause())
        }
    }

}

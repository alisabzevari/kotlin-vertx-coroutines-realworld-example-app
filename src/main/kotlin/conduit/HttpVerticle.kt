package conduit

import conduit.utils.handle
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

fun routes(vertx: Vertx): Router {
    val router = Router.router(vertx)

    router.get("/healthcheck").handle { ctx -> ctx.response().setStatusCode(200).end("Healthy") }

    return router
}

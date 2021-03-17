package conduit.utils

import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Route.handle(handler: suspend (RoutingContext) -> Unit): Route {
    return this.handler { ctx ->
        val exceptionHandler = CoroutineExceptionHandler { _, e -> ctx.fail(e) }
        val dispatcher = ctx.vertx().dispatcher()
        val scope = CoroutineScope(dispatcher + exceptionHandler)
        scope.launch {
            handler(ctx)
        }
    }
}

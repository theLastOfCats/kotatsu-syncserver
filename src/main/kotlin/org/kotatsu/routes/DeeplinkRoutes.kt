package org.kotatsu.routes

import io.ktor.http.*
import io.ktor.server.mustache.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deeplinkRoutes() {
    get("/deeplink/reset-password") {
        val baseUrl = application.environment.config.property("kotatsu.base_url").getString()

        val token = call.request.queryParameters["token"]
        if (token.isNullOrBlank()) {
            call.respond(HttpStatusCode.BadRequest, "Missing token")
            return@get
        }

        val deepLink = "kotatsu://reset-password?base_url=$baseUrl&token=$token"

        call.respond(MustacheContent("pages/reset-password.hbs", mapOf("deep_link" to deepLink)))
    }
}

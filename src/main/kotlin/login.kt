import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.httpPost
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun login() {
    getApiToken()
}

fun getApiToken() {
    val marker = "Getting API token"
    logger.info { "$marker..." }
    val (_, response, result) = AUTH
            .httpPost(listOf(
                    "spring-security-redirect" to PERSONAL,
                    "j_username" to ACCOUNT_EMAIL,
                    "j_password" to ACCOUNT_PASSWORD
            ))
            .header("Host" to "maxdone.micromiles.co",
                    Headers.USER_AGENT to UA,
                    Headers.ACCEPT to "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
                    Headers.ACCEPT_LANGUAGE  to "en-US,en;q=0.5",
                    Headers.ACCEPT_ENCODING to "gzip, deflate, br",
                    "Content-Type" to "application/x-www-form-urlencoded",
                    "Origin" to "https://maxdone.micromiles.co",
                    "DNT" to "1",
                    "Connection" to "keep-alive",
                    Headers.COOKIE to cookieStore.allCookies(),
                    "Referer" to "https://maxdone.micromiles.co/services/login?r=https%3A%2F%2Fmaxdone.micromiles.co%2Fpersonal",
                    "Upgrade-Insecure-Requests" to "1"
            )
            .allowRedirects(false)
            .response()
    result.fold(
            success = {
                logger.info { "$marker Success" }
                val collection = response[Headers.SET_COOKIE]
                cookieStore.addAll(collection)
            },
            failure = { error ->
                logger.error { "$marker Failure: $error" }
                throw error
            })
}

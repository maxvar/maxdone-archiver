import com.github.kittinunf.fuel.core.HeaderValues
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import mu.KLogger
import java.net.CookieManager
import java.net.CookieStore
import java.net.HttpCookie

/**
 * storage for cookies (don't want to bother with handling those myself)
 */
val cookieStore = CookieManager().cookieStore!!

@Suppress("unused")
fun dumpCookies(logger: KLogger, purpose: String) {
    logger.debug { "$purpose Cookies collected: (${cookieStore.cookies.size})" }
    cookieStore.cookies.forEachIndexed { i, cookie -> logger.debug { "$i: $cookie path=${cookie.path}" } }
}

@Suppress("unused")
fun dumpInfo(logger: KLogger, purpose: String, request: Request, response: Response) {
    logger.debug { "$purpose Request URL: ${request.url} ${request.method}" }
    logger.debug { "$purpose  status code ${response.statusCode}" }
    logger.debug { "$purpose Request headers (${request.headers.size})" }
    request.headers.entries.forEach { pair ->
        logger.debug { " ${pair.key}: ${pair.value}" }
    }
    logger.debug { "$purpose Response headers (${response.headers.size})" }
    response.headers.entries.forEach { pair ->
        logger.debug { " ${pair.key}: ${pair.value}" }
    }
}

fun CookieStore.addAll(setCookiesHeader: HeaderValues) =
        setCookiesHeader.forEach { headerValue ->
            HttpCookie.parse(headerValue).forEach { cookie ->
                this.add(null, cookie)
            }
        }

fun CookieStore.allCookies(): String = this.cookies.joinToString(";", transform = HttpCookie::toString)
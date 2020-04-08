import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.failure
import mu.KotlinLogging
import java.io.File

private val logger = KotlinLogging.logger {}

fun purge() {
    val completedTasks = getCompletedTasks()
    logger.debug { "Result: ${completedTasks.size} row(s)" }
    val tasksFile = File("tasks")
    if (!tasksFile.exists()) tasksFile.mkdir()
    if (tasksFile.isDirectory && tasksFile.canWrite())
        completedTasks.forEach { task ->
            logger.debug { " ${task.id}" }
            dumpAndRemove(task)
        }
}

fun dumpAndRemove(task: Task) {
    File("tasks/${task.id}.json").writeText(task.json.toString())
    removeTask(task.id)
}

fun removeTask(id: String) {
    val (_, response, result) = "$TASKS/$id"
            .httpDelete()
            .header(
                    "Host" to "maxdone.micromiles.co",
                    Headers.USER_AGENT to UA,
                    Headers.ACCEPT to "application/json, text/javascript, */*; q=0.01",
                    Headers.ACCEPT_LANGUAGE to "en-US,en;q=0.5",
                    Headers.ACCEPT_ENCODING to "gzip, deflate, br",
                    "X-Requested-With" to "XMLHttpRequest",
                    "Origin" to "https://maxdone.micromiles.co",
                    "DNT" to "1",
                    "Connection" to "keep-alive",
                    "Referer" to "https://maxdone.micromiles.co/personal",
                    Headers.COOKIE to cookieStore.allCookies()
            )
            .response()
    result.fold(
            success = {
                logger.debug { " delete resulted in ${response.statusCode}" }
            },
            failure = {
                logger.error { it }
                throw it
            }
    )
}

private fun getCompletedTasks(): List<Task> {
    val (_, _, result) = TASKS_COMPLETED
            .httpGet(listOf("size" to PAGE_SIZE.toString(), "page" to "0", "format" to "json"))
            .header(
                    Headers.ACCEPT to "application/json, text/javascript, */*; q=0.01",
                    Headers.ACCEPT_ENCODING to "gzip, deflate, br",
                    Headers.ACCEPT_LANGUAGE to "en-US,en;q=0.5",
                    "Connection" to "keep-alive",
                    Headers.COOKIE to cookieStore.allCookies(),
                    "DNT" to "1",
                    "Host" to "maxdone.micromiles.co",
                    "Referer" to PERSONAL,
                    Headers.USER_AGENT to UA,
                    "X-Requested-With" to "XMLHttpRequest"
            )
            .responseObject(Task.CompletedListDeserializer())
    result.failure { throw it }
    return result.get()
}

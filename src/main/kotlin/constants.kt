@file:Suppress("unused")

/**
 * base url for all maxdone-related
 */
const val ROOT = "https://maxdone.micromiles.co"

/**
 * url for personal UI
 */
const val PERSONAL = "$ROOT/personal"

/**
 * base for all backend services
 */
const val SERVICES = "$ROOT/services"

/**
 * url for session management
 */
const val SESSION = "$SERVICES/session"

/**
 * url for obtaining security cookie
 */
const val AUTH = "$SERVICES/j_spring_security_check"

/**
 * base url for backend api
 */
const val API = "$SERVICES/v1"

/**
 * url for tasks manipulation
 */
const val TASKS = "$API/tasks"

/**
 * url for getting completed tasks list
 */
const val TASKS_COMPLETED = "$TASKS/completed"

/**
 * User-Agent string
 */
const val UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0"

/**
 * size of the page for obtaining the list of completed tasks
 */
const val PAGE_SIZE = 1000

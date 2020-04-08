import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.JsonObject

data class Task(val id: String, val json: JsonObject) {

    class CompletedListDeserializer : ResponseDeserializable<List<Task>> {
        override fun deserialize(content: String): List<Task> {
            val jsonResponse = Gson().fromJson(content, JsonObject::class.java)
            val jsonTaskList = jsonResponse["content"].asJsonArray
            return jsonTaskList.map {
                it as JsonObject
                Task(it["id"].asString, it)
            }
        }
    }
}

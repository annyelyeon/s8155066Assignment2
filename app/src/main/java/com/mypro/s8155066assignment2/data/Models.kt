package com.mypro.s8155066assignment2.data

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val keypass: String
)

data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)

// Remove hardcoding entities, allowing to access with anyone's keypass and show their details
typealias Entity = Map<String, Any?>
fun Any?.toDisplayText(): String =
    if (this is Double && this % 1.0 == 0.0) this.toInt().toString() else this?.toString() ?: ""
fun Entity.summaryFields(): List<Pair<String, String>> =
    entries.filter { it.key != "description" }.map { it.key to it.value.toDisplayText() }
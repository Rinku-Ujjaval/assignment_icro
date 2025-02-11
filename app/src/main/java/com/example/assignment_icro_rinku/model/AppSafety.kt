package com.example.assignment_icro_rinku.model

import java.io.Serializable

data class AppSafety(
    val query_status: String? = null,
    val urls: List<Data>? = emptyList(),
) : Serializable {
}

data class Data(
    val id: Int? = null,
    val urlhaus_reference: String? = null,
    val url_status: String? = null,
    val host: String? = null,
    val date_added: String? = null,
    val threat: String? = null
) : Serializable
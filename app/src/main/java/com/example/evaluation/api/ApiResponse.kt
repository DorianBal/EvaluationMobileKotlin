package com.example.evaluation.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @Expose @SerializedName("count") val count: Int, // Nombre total de résultats disponibles
    @Expose @SerializedName("next") val next: String?, // URL de la page suivante pour la pagination
    @Expose @SerializedName("previous") val previous: String?, // URL de la page précédente pour la pagination
    @Expose @SerializedName("results") val results: List<PokeResult>
)

data class PokeResult(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)


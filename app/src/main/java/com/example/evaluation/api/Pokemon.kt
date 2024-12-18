package com.example.evaluation.api

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("types") val types: List<PokemonType>,
    @SerializedName("stats") val stats: List<PokemonStat>,
    @SerializedName("sprites") val sprites: Sprites
)

data class PokemonType(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: TypeDetail
)

data class TypeDetail(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class PokemonStat(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("effort") val effort: Int,
    @SerializedName("stat") val stat: StatDetail
)

data class StatDetail(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String?
)

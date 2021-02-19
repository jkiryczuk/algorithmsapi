package pl.jerzykiryczuk.algorithmapi.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class Point(@JsonProperty("name") var name: String,@JsonProperty("lat") var lat: Double,@JsonProperty("long") var long: Double, var visited: Boolean = false)

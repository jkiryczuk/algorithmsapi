package pl.jerzykiryczuk.algorithmapi.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class NeighbourResponse(@JsonProperty("path")var path: String, @JsonProperty("debug") var debugMessage: String, @JsonProperty("distance") var distance: Double)

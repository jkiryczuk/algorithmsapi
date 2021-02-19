package pl.jerzykiryczuk.algorithmapi.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class NearestNeighbourRequest(@JsonProperty("pointList") var pointList: List<Point>) {
}
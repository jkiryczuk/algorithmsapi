package pl.jerzykiryczuk.algorithmapi.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class ControlNumberResponse(@JsonProperty("number")var number: String) {
}

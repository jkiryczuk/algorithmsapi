package pl.jerzykiryczuk.algorithmapi.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class BadRequestResponse(@JsonProperty("message") var message: String)

package pl.jerzykiryczuk.algorithmapi.entities

import com.fasterxml.jackson.annotation.JsonProperty

class ValidityResponse(@JsonProperty("valid")var valid: Boolean) {
}
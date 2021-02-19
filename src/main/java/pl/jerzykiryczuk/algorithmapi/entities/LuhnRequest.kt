package pl.jerzykiryczuk.algorithmapi.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class LuhnRequest(@JsonProperty("number")var number: String)

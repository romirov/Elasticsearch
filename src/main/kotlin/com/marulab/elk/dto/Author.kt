package com.marulab.elk.dto

import arrow.optics.optics
import kotlinx.serialization.Serializable

@Serializable
@optics
data class Author(
	val firstName: String,
	val lastName: String
){
	companion object
}
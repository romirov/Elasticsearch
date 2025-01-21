package com.marulab.elk.dto

import arrow.optics.optics
import kotlinx.serialization.Serializable
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDateTime
import java.util.*

@Serializable
@optics
@Document(indexName = "messages")
data class Message(
	@Id
	val id: UUID,
	val title: String,
	val content: String,
	@Field(type = FieldType.Nested, includeInParent = true)
	val author: Author,
	@Field(type = FieldType.Date)
	val createdDate: LocalDateTime
) {
	companion object
}
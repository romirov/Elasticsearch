package com.marulab.elk.dto

import arrow.optics.optics
import kotlinx.serialization.Serializable
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.*
import java.time.LocalDateTime
import java.util.*

@Serializable
@optics
@Document(indexName = "messages")
@Setting(
	sortFields = ["createdDate", "title"],
	sortModes = [Setting.SortMode.max, Setting.SortMode.min],
	sortOrders = [Setting.SortOrder.desc, Setting.SortOrder.asc],
	sortMissingValues = [Setting.SortMissing._last, Setting.SortMissing._first]
)
data class Message(
	@Id
	val id: UUID,
	@Field(type = FieldType.Keyword)
	val title: String,
	val content: String,
	@Field(type = FieldType.Nested, includeInParent = true)
	val author: Author,
	@Field(type = FieldType.Date, pattern = ["yyyy-MM-dd'T'HH:mm:ss"], format = [DateFormat.date_hour_minute_second])
	val createdDate: LocalDateTime
) {
	companion object
}
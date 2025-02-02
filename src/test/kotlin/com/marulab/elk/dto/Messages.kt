package com.marulab.elk.dto

import com.marulab.elk.dto.Authors.author1
import com.marulab.elk.dto.Authors.author10
import com.marulab.elk.dto.Authors.author11
import com.marulab.elk.dto.Authors.author2
import com.marulab.elk.dto.Authors.author3
import com.marulab.elk.dto.Authors.author4
import com.marulab.elk.dto.Authors.author5
import com.marulab.elk.dto.Authors.author6
import com.marulab.elk.dto.Authors.author7
import com.marulab.elk.dto.Authors.author8
import com.marulab.elk.dto.Authors.author9
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Messages {
	val id1: UUID = UUID.randomUUID()
	val id2: UUID = UUID.randomUUID()
	val id3: UUID = UUID.randomUUID()
	val id4: UUID = UUID.randomUUID()
	val id5: UUID = UUID.randomUUID()
	val id6: UUID = UUID.randomUUID()
	val id7: UUID = UUID.randomUUID()
	val id8: UUID = UUID.randomUUID()
	val id9: UUID = UUID.randomUUID()
	val id10: UUID = UUID.randomUUID()
	val id11: UUID = UUID.randomUUID()

	val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

	val msg1 = Message(id1, "test", "test", author1, LocalDateTime.parse("2020-01-01T00:00:00", dateTimeFormatter))
	val msg2 = Message(id2, "test", "test", author2, LocalDateTime.parse("2021-01-01T00:00:00", dateTimeFormatter))
	val msg3 = Message(id3, "test", "test", author3, LocalDateTime.parse("2022-01-01T00:00:00", dateTimeFormatter))
	val msg4 = Message(id4, "test", "test", author4, LocalDateTime.parse("2023-01-01T00:00:00", dateTimeFormatter))
	val msg5 = Message(id5, "test", "test", author5, LocalDateTime.parse("2024-01-01T00:00:00", dateTimeFormatter))
	val msg6 = Message(id6, "test", "test", author6, LocalDateTime.parse("2025-01-01T00:00:00", dateTimeFormatter))
	val msg7 = Message(id7, "test", "test", author7, LocalDateTime.parse("2026-01-01T00:00:00", dateTimeFormatter))
	val msg8 = Message(id8, "test", "test", author8, LocalDateTime.parse("2027-01-01T00:00:00", dateTimeFormatter))
	val msg9 = Message(id9, "test", "test", author9, LocalDateTime.parse("2028-01-01T00:00:00", dateTimeFormatter))
	val msg10 = Message(id10, "test", "test", author10, LocalDateTime.parse("2029-01-01T00:00:00", dateTimeFormatter))
	val msg11 = Message(id11, "test", "test", author11, LocalDateTime.parse("2030-01-01T00:00:00", dateTimeFormatter))

	val messages: List<Message> = listOf(msg1, msg2, msg3, msg4, msg5, msg6, msg7, msg8, msg9, msg10, msg11)
}
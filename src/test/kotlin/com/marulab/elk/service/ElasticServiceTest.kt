package com.marulab.elk.service

import com.marulab.elk.ApplicationTest
import com.marulab.elk.dto.Author
import com.marulab.elk.dto.Message
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

class ElasticServiceTest : ApplicationTest() {

	@Test
	fun `test save`() {
		val result = elasticService.save(msg1)
		Assertions.assertEquals(msg1, result)
	}

	@Test
	fun `test findById`() {
		elasticService.save(msg1)
		val result = elasticService.findById(id1).get()
		Assertions.assertEquals(msg1, result)
	}

	@Test
	fun `test findAll`() {
		elasticService.save(msg1)
		elasticService.save(msg2)
		elasticService.save(msg3)
		val result = elasticService.findAll().toList()
		Assertions.assertEquals(listOf(msg1, msg2, msg3), result)
	}

	@Test
	fun `test update`() {
		elasticService.save(msg1)
		val updatedMsg = msg1.copy(author = Author("firstName2", "lastName2"))
		val result = elasticService.update(updatedMsg)
		Assertions.assertEquals(updatedMsg, result)
	}

	@Test
	fun `test delete by id`() {
		elasticService.save(msg1)
		elasticService.deleteById(id1)
		val result = elasticService.findById(id1)
		Assertions.assertTrue(result.isEmpty)
	}

	@Test
	fun `test deleteAll`() {
		elasticService.save(msg1)
		elasticService.save(msg2)
		elasticService.save(msg3)
		elasticService.deleteAll()
		val result = elasticService.findAll().toList()
		Assertions.assertEquals(listOf<Message>(), result)
	}

	companion object {
		val id1: UUID = UUID.randomUUID()
		val id2: UUID = UUID.randomUUID()
		val id3: UUID = UUID.randomUUID()
		val author = Author("firstName1", "lastName1")
		val msg1 = Message(
			id = id1,
			title = "testMessage1",
			content = "testContent1",
			author = author,
			createdDate = LocalDateTime.parse("2007-12-03T10:15:30")
		)
		val msg2 = msg1.copy(id = id2, title = "testMessage2", content = "testContent2")
		val msg3 = msg1.copy(id = id3, title = "testMessage3", content = "testContent3")
	}
}
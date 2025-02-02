package com.marulab.elk.service

import com.marulab.elk.ApplicationTest
import com.marulab.elk.dto.Author
import com.marulab.elk.dto.Authors.author2
import com.marulab.elk.dto.Message
import com.marulab.elk.dto.Messages.id1
import com.marulab.elk.dto.Messages.messages
import com.marulab.elk.dto.Messages.msg1
import com.marulab.elk.dto.Messages.msg2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageRequest

class ElasticAnnotationQueryServiceTest : ApplicationTest() {

	@Test
	fun `test save`() {
		val result = elasticAnnotationQueryService.save(msg1)
		Assertions.assertEquals(msg1, result)
	}

	@Test
	fun `test findById`() {
		saveMsg()
		val result = elasticAnnotationQueryService.findById(id1).get()
		Assertions.assertEquals(msg1, result)
	}

	@Test
	fun `test findByAuthor`() {
		saveMsg()
		val addedMessage = msg1.copy(author = author2)
		elasticAnnotationQueryService.save(addedMessage)
		elasticAnnotationQueryService
		val result = elasticAnnotationQueryService
			.findByAuthorFirstName(
				firstName = author2.firstName,
				pageable = PageRequest.of(0, 10)
			)
		Assertions.assertEquals(msg2.id, result.first().id)
		Assertions.assertEquals(msg1.id, result.last().id)
	}

	@Test
	fun `test findByTitle`() {
		saveMsg()
		elasticAnnotationQueryService
		val result = elasticAnnotationQueryService
			.findByTitle(
				title = "test",
				pageable = PageRequest.of(0, 20)
			)
		Assertions.assertEquals(result.size, 11)
	}

	@Test
	fun `test update`() {
		saveMsg()
		val updatedMsg = msg1.copy(author = Author("firstName22", "lastName22"))
		val result = elasticAnnotationQueryService.update(updatedMsg)
		Assertions.assertEquals(updatedMsg, result)
	}

	@Test
	fun `test deleteAll`() {
		saveMsg()
		elasticAnnotationQueryService.deleteAll()
		val result = elasticAnnotationQueryService.findAll().toList()
		Assertions.assertEquals(listOf<Message>(), result)
	}

	private fun saveMsg() = messages.forEach { elasticAnnotationQueryService.save(it) }
}
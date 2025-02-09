package com.marulab.elk.repository

import com.marulab.elk.ElasticRepoTest
import com.marulab.elk.dto.Authors
import com.marulab.elk.dto.Message
import com.marulab.elk.dto.Messages
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates

class ElasticAnnotationQueryRepoTest : ElasticRepoTest() {

	@AfterEach
	fun cleanUp() {
		elasticAnnotationQueryRepo.deleteAll()
	}

	@Test
	fun `test index name`() {
		val result = elasticsearchTemplate.indexOps(
			IndexCoordinates.of("messages")
		).exists()
		Assertions.assertEquals(true, result)
	}

	@Test
	fun `test save`() {
		val result = elasticAnnotationQueryRepo.save(Messages.msg1)
		Assertions.assertEquals(Messages.msg1, result)
	}

	@Test
	fun `test findById`() {
		saveMsg()
		val result = elasticAnnotationQueryRepo.findById(Messages.id1).get()
		Assertions.assertEquals(Messages.msg1, result)
	}

	@Test
	fun `test findByAuthor`() {
		saveMsg()
		val addedMessage = Messages.msg1.copy(author = Authors.author2)
		elasticAnnotationQueryRepo.save(addedMessage)
		elasticAnnotationQueryRepo
		val result = elasticAnnotationQueryRepo
			.findByAuthorFirstName(
				firstName = Authors.author2.firstName,
				pageable = PageRequest.of(0, 10)
			)
		Assertions.assertEquals(Messages.msg2.id, result.first().id)
		Assertions.assertEquals(Messages.msg1.id, result.last().id)
	}

	@Test
	fun `test findByTitle`() {
		saveMsg()
		elasticAnnotationQueryRepo
		val result = elasticAnnotationQueryRepo
			.findByTitle(
				title = "test",
				pageable = PageRequest.of(0, 20)
			)
		Assertions.assertEquals(result.size, 11)
	}

	@Test
	fun `test deleteAll`() {
		saveMsg()
		elasticAnnotationQueryRepo.deleteAll()
		val result = elasticAnnotationQueryRepo.findAll().toList()
		Assertions.assertEquals(listOf<Message>(), result)
	}

	private fun saveMsg() = Messages.messages.forEach { elasticAnnotationQueryRepo.save(it) }
}
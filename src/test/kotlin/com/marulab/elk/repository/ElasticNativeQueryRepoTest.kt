package com.marulab.elk.repository

import com.marulab.elk.ElasticRepoTest
import com.marulab.elk.dto.Authors
import com.marulab.elk.dto.Messages
import org.junit.jupiter.api.*

class ElasticNativeQueryRepoTest : ElasticRepoTest() {

	@BeforeAll
	fun createIndex() {
		elasticNativeQueryRepo.createIndex()
	}

	@AfterAll
	fun deleteIndex() {
		elasticNativeQueryRepo.deleteIndex()
	}

	@AfterEach
	fun cleanUp() {
		elasticNativeQueryRepo.deleteAll(getIndexName())
	}

	@Test
	fun `save test`() {
		val result = elasticNativeQueryRepo.save(Messages.msg1, getIndexName())
		Assertions.assertEquals(Messages.msg1, result)
	}

	@Test
	fun `update test`() {
		elasticNativeQueryRepo.save(Messages.msg1, getIndexName())
		val updatedMsg = Messages.msg1.copy(author = Authors.author2)
		val result = elasticNativeQueryRepo.update(updatedMsg, getIndexName())
		Assertions.assertEquals(updatedMsg, result)
	}

	@Test
	fun `findAll test`() {
		elasticNativeQueryRepo.save(Messages.msg1, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg2, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg3, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg4, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg5, getIndexName())
		val result = elasticNativeQueryRepo.findAll(getIndexName())
		Assertions.assertEquals(5, result.size)
		Assertions.assertEquals(Messages.msg1, result.first())
		Assertions.assertEquals(Messages.msg5, result.last())
	}

	@Test
	fun `findByTitle test`() {
		elasticNativeQueryRepo.save(Messages.msg1, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg2, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg3, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg4, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg5, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg6, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg7, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg8, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg9, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg10, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg11, getIndexName())
		val result = elasticNativeQueryRepo.findByTitle("test", getIndexName())
		Assertions.assertEquals(11, result.size)
	}

	@Test
	fun `findByAuthorFirstName test`() {
		elasticNativeQueryRepo.save(Messages.msg1, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg2, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg3, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg4, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg5, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg6, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg7, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg8, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg9, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg10, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg11, getIndexName())
		val result = elasticNativeQueryRepo
			.findByAuthorFirstName(
				firstName = Authors.author2.firstName,
				indexName = getIndexName()
			)
		Assertions.assertEquals(1, result.size)
		Assertions.assertEquals(Messages.msg2, result.single())
	}
}
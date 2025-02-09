package com.marulab.elk.repository

import com.marulab.elk.ElasticRepoTest
import com.marulab.elk.dto.Authors
import com.marulab.elk.dto.Messages
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("native-test")
class ElasticNativeQueryRepoTest : ElasticRepoTest() {

	@AfterEach
	fun cleanUp() {
		elasticNativeQueryRepo.deleteAll(getIndexName())
		Thread.sleep(1000)
	}

	@Test
	fun `test index name`() {
		val index = IndexCoordinates.of("messages-native")
		val result = elasticsearchTemplate.indexOps(index).exists()
		Assertions.assertEquals(true, result)
		Assertions.assertEquals(getIndexName(), index.indexName)
	}

	@Test
	fun `save test`() {
		val result = elasticNativeQueryRepo.save(Messages.msg1, getIndexName())
		Assertions.assertEquals(Messages.msg1, result)
	}

	@Test
	fun `update test`() {
		elasticNativeQueryRepo.save(Messages.msg1, getIndexName())
		Thread.sleep(1000)
		val savedMsg = elasticNativeQueryRepo.findById(Messages.id1, getIndexName())
		Assertions.assertEquals(Messages.msg1, savedMsg)

		val updatedMsg = Messages.msg1.copy(author = Authors.author2)
		elasticNativeQueryRepo.update(updatedMsg, getIndexName())
		Thread.sleep(1000)
		val result = elasticNativeQueryRepo.findById(Messages.id1, getIndexName())
		Assertions.assertEquals(updatedMsg, result)
	}

	@Test
	fun `findAll test`() {
		elasticNativeQueryRepo.save(Messages.msg1, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg2, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg3, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg4, getIndexName())
		elasticNativeQueryRepo.save(Messages.msg5, getIndexName())
		Thread.sleep(1000)
		val result = elasticNativeQueryRepo.findAll(getIndexName())
		Assertions.assertEquals(5, result.size)
		Assertions.assertTrue { Messages.msg1 in result }
		Assertions.assertTrue { Messages.msg5 in result }
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
		Thread.sleep(1000)
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
		Thread.sleep(1000)
		val result = elasticNativeQueryRepo
			.findByAuthorFirstName(
				firstName = Authors.author2.firstName,
				indexName = getIndexName()
			)
		Assertions.assertEquals(1, result.size)
		Assertions.assertEquals(Messages.msg2, result.single())
	}
}
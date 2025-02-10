package com.marulab.elk.repository

import com.marulab.elk.ElasticRepoTest
import com.marulab.elk.dto.Messages
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@ActiveProfiles("criteria-test")
class ElasticCriteriaQueryRepoTest : ElasticRepoTest() {
	@AfterEach
	fun cleanUp() {
		Thread.sleep(1000)
		elasticCriteriaQueryRepo.deleteAll(getIndexName())
	}

	@Test
	fun `test index name`() {
		val index = IndexCoordinates.of("messages-criteria")
		val result = elasticsearchTemplate.indexOps(index).exists()
		Assertions.assertEquals(true, result)
		Assertions.assertEquals(getIndexName(), index.indexName)
	}

	@Test
	fun `save test`() {
		val result = elasticCriteriaQueryRepo.save(Messages.msg1, getIndexName())
		Assertions.assertEquals(Messages.msg1, result)
	}

	@Test
	fun `findByIdAndIndexName test`() {
		elasticCriteriaQueryRepo.save(Messages.msg1, getIndexName())
		elasticCriteriaQueryRepo.save(Messages.msg2, getIndexName())
		elasticCriteriaQueryRepo.save(Messages.msg3, getIndexName())
		elasticCriteriaQueryRepo.save(Messages.msg4, getIndexName())
		elasticCriteriaQueryRepo.save(Messages.msg5, getIndexName())
		Thread.sleep(1000)
		val result = elasticCriteriaQueryRepo.findByIdAndIndexName(Messages.msg4.id, getIndexName())
		Assertions.assertEquals(Messages.msg4, result)
	}

	@Test
	fun `findBetweenDatesAndIndexName test`() {
		elasticCriteriaQueryRepo.save(Messages.msg1, getIndexName())
		elasticCriteriaQueryRepo.save(Messages.msg2, getIndexName())
		elasticCriteriaQueryRepo.save(Messages.msg3, getIndexName())
		elasticCriteriaQueryRepo.save(Messages.msg4, getIndexName())
		elasticCriteriaQueryRepo.save(Messages.msg5, getIndexName())
		Thread.sleep(1000)
		val result = elasticCriteriaQueryRepo.findBetweenDatesAndIndexName(
			lowerBound = LocalDateTime.parse("2019-12-31T00:00:00"),
			upperBound = LocalDateTime.parse("2020-01-02T00:00:00"),
			indexName = getIndexName()
		)
		Assertions.assertEquals(Messages.msg1, result.single())
	}
}
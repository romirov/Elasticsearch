package com.marulab.elk.repository

import com.marulab.elk.ElasticRepoTest
import com.marulab.elk.dto.Messages
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("template-test")
class ElasticSearchTemplateQueryRepoTest : ElasticRepoTest() {
	@AfterEach
	fun cleanUp() {
		Thread.sleep(1000)
		elasticSearchTemplateQueryRepo.deleteAll(getIndexName())
	}

	@Test
	fun `test index name`() {
		val index = IndexCoordinates.of("messages-template")
		val result = elasticsearchTemplate.indexOps(index).exists()
		Assertions.assertEquals(true, result)
		Assertions.assertEquals(getIndexName(), index.indexName)
	}

	@Test
	fun `save test`() {
		val result = elasticSearchTemplateQueryRepo.save(Messages.msg1, getIndexName())
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
		val result = elasticSearchTemplateQueryRepo.findById(Messages.msg4.id, getIndexName())
		Assertions.assertEquals(Messages.msg4, result)
	}
}
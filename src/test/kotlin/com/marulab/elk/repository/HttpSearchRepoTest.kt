package com.marulab.elk.repository

import com.marulab.elk.ElasticRepoTest
import com.marulab.elk.dto.Messages
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.testcontainers.elasticsearch.ElasticsearchContainer

class HttpSearchRepoTest: ElasticRepoTest() {
	@Autowired
	lateinit var httpSearchRepo: HttpSearchRepo

	@Autowired
	lateinit var container: ElasticsearchContainer

	@AfterEach
	fun cleanUp() {
		elasticAnnotationQueryRepo.deleteAll()
	}

	@Test
	fun `test save`() {
		val result = elasticAnnotationQueryRepo.save(Messages.msg1)
		val searchUrl = """http://${container.host}:${container.firstMappedPort}/_search"""
		println(httpSearchRepo.search(searchUrl))
		Assertions.assertNotNull(result)
	}
}
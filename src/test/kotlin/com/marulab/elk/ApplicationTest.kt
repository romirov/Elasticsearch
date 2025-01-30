package com.marulab.elk

import com.marulab.elk.configuration.ElasticTestConfig
import com.marulab.elk.configuration.TestContainersConfig
import com.marulab.elk.service.*
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestContainersConfig::class, ElasticTestConfig::class)
@SpringBootTest
abstract class ApplicationTest {
	@Autowired
	lateinit var elasticAnnotationQueryService: ElasticAnnotationQueryService

	@Autowired
	lateinit var elasticCriteriaQueryService: ElasticCriteriaQueryService

	@Autowired
	lateinit var elasticNativeQueryService: ElasticNativeQueryService

	@Autowired
	lateinit var elasticSearchTemplateQueryService: ElasticSearchTemplateQueryService

	@Autowired
	lateinit var elasticStringQueryService: ElasticStringQueryService

	@AfterEach
	fun cleanup() = elasticAnnotationQueryService.deleteAll()
}
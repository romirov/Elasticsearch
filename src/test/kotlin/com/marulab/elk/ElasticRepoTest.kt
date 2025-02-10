package com.marulab.elk

import com.marulab.elk.configuration.ElasticTestConfig
import com.marulab.elk.configuration.TestContainersConfig
import com.marulab.elk.configuration.properties.ElasticProp
import com.marulab.elk.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate

@Import(TestContainersConfig::class, ElasticTestConfig::class)
@SpringBootTest
abstract class ElasticRepoTest {

	@Autowired
	lateinit var elasticProp: ElasticProp

	@Autowired
	lateinit var elasticAnnotationQueryRepo: ElasticAnnotationQueryRepo

	@Autowired
	lateinit var elasticCriteriaQueryRepo: ElasticCriteriaQueryRepo

	@Autowired
	lateinit var elasticNativeQueryRepo: ElasticNativeQueryRepo

	@Autowired
	lateinit var elasticSearchTemplateQueryRepo: ElasticSearchTemplateQueryRepo

	@Autowired
	lateinit var elasticStringQueryRepo: ElasticStringQueryRepo

	@Autowired
	lateinit var elasticsearchTemplate: ElasticsearchTemplate

	fun getIndexName() = elasticProp.indexNamePattern
}
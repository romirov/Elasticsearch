package com.marulab.elk.repository

import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.stereotype.Repository

@Repository
class ElasticNativeQueryRepo(
	val elasticOperations: ElasticsearchOperations
) {
}
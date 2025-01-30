package com.marulab.elk.service

import com.marulab.elk.repository.ElasticCriteriaQueryRepo
import org.springframework.stereotype.Service

@Service
class ElasticCriteriaQueryService(
	private val repo: ElasticCriteriaQueryRepo
) {
}
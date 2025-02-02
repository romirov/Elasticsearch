package com.marulab.elk.service

import com.marulab.elk.repository.ElasticSearchTemplateQueryRepo
import org.springframework.stereotype.Service

@Service
class ElasticSearchTemplateQueryService(
	private val repo: ElasticSearchTemplateQueryRepo
) {
}
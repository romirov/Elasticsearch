package com.marulab.elk.service

import com.marulab.elk.repository.ElasticStringQueryRepo
import org.springframework.stereotype.Service

@Service
class ElasticStringQueryService(
	private val repo: ElasticStringQueryRepo
) {
}
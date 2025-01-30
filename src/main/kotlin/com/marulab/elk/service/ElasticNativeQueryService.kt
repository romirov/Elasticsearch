package com.marulab.elk.service

import com.marulab.elk.repository.ElasticNativeQueryRepo
import org.springframework.stereotype.Service

@Service
class ElasticNativeQueryService(
	private val repo: ElasticNativeQueryRepo
) {
}
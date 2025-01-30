package com.marulab.elk.service

import com.marulab.elk.dto.Message
import com.marulab.elk.repository.ElasticAnnotationQueryRepo
import org.springframework.stereotype.Service

@Service
class ElasticAnnotationQueryService(
	private val repo: ElasticAnnotationQueryRepo
) {

	fun save(message: Message) = repo.save<Message>(message)

	fun findByAuthorFirstName(name: String) = repo.findByAuthorFirstName(name)

	fun deleteAll() = repo.deleteAll()
}
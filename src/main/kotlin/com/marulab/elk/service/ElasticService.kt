package com.marulab.elk.service

import com.marulab.elk.dto.Message
import com.marulab.elk.repository.ElasticRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class ElasticService(
	private val elasticRepo: ElasticRepo
) {

	fun save(message: Message) = elasticRepo.save<Message>(message)

	fun findById(id: UUID) = elasticRepo.findById(id)

	fun findAll() = elasticRepo.findAll()

	fun update(message: Message) = elasticRepo.save(message)

	fun deleteById(id: UUID) = elasticRepo.deleteById(id)

	fun deleteAll() = elasticRepo.deleteAll()
}
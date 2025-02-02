package com.marulab.elk.service

import com.marulab.elk.dto.Message
import com.marulab.elk.repository.ElasticAnnotationQueryRepo
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class ElasticAnnotationQueryService(
	private val repo: ElasticAnnotationQueryRepo
) {

	fun save(message: Message): Message = repo.save<Message>(message)

	fun update(message: Message): Message = if (findById(message.id).isPresent) save(message)
	else error("Cannot find message with id ${message.id}")

	fun findAll() = repo.findAll()

	fun findById(id: UUID): Optional<Message> = repo.findById(id)

	fun findByAuthorFirstName(firstName: String, pageable: Pageable): List<Message> =
		repo.findByAuthorFirstName(firstName, pageable)

	fun findByTitle(title: String, pageable: Pageable): List<Message> =
		repo.findByTitle(title, pageable)

	fun deleteAll() = repo.deleteAll()
}
package com.marulab.elk.repository

import com.marulab.elk.dto.Message
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ElasticAnnotationQueryRepo : ElasticsearchRepository<Message, UUID> {
	@Query(
		"""
			{
				"match": {
					"author.firstName": "?0"
					
				}
			}
		"""
	)
	fun findByAuthorFirstName(firstName: String, pageable: Pageable): List<Message>

	@Query(
		"""
			{
				"match": {
					"title": "?0"
				}	
			}
		"""
	)
	fun findByTitle(title: String, pageable: Pageable): List<Message>
}
package com.marulab.elk.repository

import com.marulab.elk.dto.Message
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ElasticAnnotationQueryRepo : ElasticsearchRepository<Message, UUID> {
	@Query(
		"""
			{
				"bool": {
					"must": {
						"author": {
							"firstName": "#{#name}"
						}
					}
				}
			}
		"""
	)
	fun findByAuthorFirstName(name: String): List<Message>
}
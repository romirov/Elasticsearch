package com.marulab.elk.repository

import com.marulab.elk.dto.Message
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.data.elasticsearch.core.query.DeleteQuery
import org.springframework.data.elasticsearch.core.query.Query
import org.springframework.data.elasticsearch.core.query.StringQuery
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ElasticStringQueryRepo(
	val elasticOperations: ElasticsearchOperations
) {
	fun save(message: Message, indexName: String): Message {
		val index = IndexCoordinates.of(indexName)
		return elasticOperations.save(message, index)
	}

	fun deleteAll(indexName: String) {
		val index = IndexCoordinates.of(indexName)
		val dq = DeleteQuery.builder(Query.findAll()).build()
		elasticOperations.delete(dq, Message::class.java, index)
	}

	fun search(queryStr: String, indexName: String): List<Message> {
		val index = IndexCoordinates.of(indexName)
		val query = StringQuery.builder(queryStr).build()
		val searchHits = elasticOperations.search(query, Message::class.java, index)
		return searchHits.map { it.content }.toList()
	}

	fun findById(id: UUID, indexName: String): Message {
		val query = """
			{
				"match": {
					"id": "$id"
				}	
			}
		"""
		return search(queryStr = query, indexName = indexName).single()
	}
}
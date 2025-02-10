package com.marulab.elk.repository

import com.marulab.elk.dto.Message
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.data.elasticsearch.core.query.CriteriaQueryBuilder
import org.springframework.data.elasticsearch.core.query.DeleteQuery
import org.springframework.data.elasticsearch.core.query.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
class ElasticCriteriaQueryRepo(
	val eo: ElasticsearchOperations
) {

	fun save(message: Message, indexName: String): Message {
		val index = IndexCoordinates.of(indexName)
		return eo.save(message, index)
	}

	fun deleteAll(indexName: String) {
		val index = IndexCoordinates.of(indexName)
		val dq = DeleteQuery.builder(Query.findAll()).build()
		eo.delete(dq, Message::class.java, index)
	}

	fun findByIdAndIndexName(id: UUID, indexName: String): Message =
		execByCriteriaAndIndexName(
			criteria = Criteria("id").matchesAll(id),
			indexName = indexName
		).single()

	fun findBetweenDatesAndIndexName(lowerBound: LocalDateTime, upperBound: LocalDateTime, indexName: String): List<Message> =
		execByCriteriaAndIndexName(
			Criteria.where("createdDate").between(lowerBound, upperBound),
			indexName
		)

	private fun execByCriteriaAndIndexName(criteria: Criteria, indexName: String): List<Message> {
		val index = IndexCoordinates.of(indexName)
		val query = CriteriaQueryBuilder(criteria).build()
		val result = eo.searchForStream(query, Message::class.java, index)
		val resultList = mutableListOf<Message>()
		result.use { searchHitsIterator ->
			while (searchHitsIterator.hasNext())
				resultList.add(searchHitsIterator.next().content)
		}
		return resultList
	}
}
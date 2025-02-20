package com.marulab.elk.repository

import com.marulab.elk.dto.Message
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.data.elasticsearch.core.query.DeleteQuery
import org.springframework.data.elasticsearch.core.query.Query
import org.springframework.data.elasticsearch.core.query.SearchTemplateQuery
import org.springframework.data.elasticsearch.core.script.Script
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ElasticSearchTemplateQueryRepo(
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

	private fun search(
		script: String,
		params: Map<String, String>,
		pageable: Pageable,
		indexName: String
	): List<Message> {
		val scriptId = UUID.randomUUID().toString()
		elasticOperations.putScript(
			Script.builder()
				.withId(scriptId)
				.withLanguage(SCRIPT_LANGUAGE)
				.withSource(script)
				.build()
		)

		val index = IndexCoordinates.of(indexName)
		val query = SearchTemplateQuery.builder()
			.withId(scriptId)
			.withParams(params)
			.withPageable(pageable)
			.build()

		val searchHits = elasticOperations.search(query, Message::class.java, index)
//		val searchPage = SearchHitSupport.searchPageFor(searchHits, pageable)
		val result = searchHits.map { it.content }.toList()

		return result
	}

	fun findById(id: UUID, indexName: String): Message {
		val script = """
				{
			        "query": {
			          "bool": {
			            "must": [
			              {
			                "match": {
			                  "id": "{{id}}"   
			                }
			              }
			            ]
			          }
			        }                   
			    }
				"""
		return search(
			script = script,
			params = mapOf("id" to id.toString()),
			pageable = Pageable.unpaged(),
			indexName = indexName
		).single()
	}

	private companion object {
		const val SCRIPT_LANGUAGE = "mustache"
	}
}
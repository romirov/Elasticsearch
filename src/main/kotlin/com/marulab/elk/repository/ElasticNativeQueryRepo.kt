package com.marulab.elk.repository

import co.elastic.clients.elasticsearch._types.SortOptions
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery
import co.elastic.clients.elasticsearch._types.query_dsl.Query
import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery
import co.elastic.clients.util.ObjectBuilder
import com.marulab.elk.dto.Message
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.data.elasticsearch.core.query.DeleteQuery
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class ElasticNativeQueryRepo(
	val elasticOperations: ElasticsearchOperations
) {

	private fun find(
		indexName: String,
		query: (Query.Builder) -> ObjectBuilder<Query>,
		filter: ((Query.Builder) -> ObjectBuilder<Query>)?,
		fields: String?,
		pageable: Pageable?,
		sort: ((SortOptions.Builder) -> ObjectBuilder<SortOptions>)?,
	): List<Message> {
		val index = IndexCoordinates.of(indexName)
		val searchQuery = with(NativeQueryBuilder()) {
			fields?.let { withFields(fields) }
			filter?.let { withFilter(filter) }
			pageable?.let { withPageable(pageable) }
			sort?.let { withSort(sort) }
			withQuery(query)
		}.build()
		val result = mutableListOf<Message>()
		elasticOperations.searchForStream(searchQuery, Message::class.java, index).use {
			while (it.hasNext())
				result.add(it.next().content)
		}
		return result
	}

	fun save(message: Message, indexName: String): Message {
		val index = IndexCoordinates.of(indexName)
		return elasticOperations.save(message, index)
	}

	fun update(message: Message, indexName: String) {
		val index = IndexCoordinates.of(indexName)
		elasticOperations.update(message, index).result
	}

	fun deleteAll(indexName: String) {
		val index = IndexCoordinates.of(indexName)
		val query = with(NativeQueryBuilder()) {
			withQuery { builder -> builder.matchAll(MatchAllQuery.Builder().build()) }
		}.build()
		val deleteQuery = DeleteQuery.builder(query).build()
		elasticOperations.delete(deleteQuery, Message::class.java, index)
	}

	fun findAll(indexName: String) =
		find(
			indexName = indexName,
			query = { builder -> builder.matchAll(MatchAllQuery.Builder().build()) },
			filter = null,
			fields = null,
			pageable = null,
			sort = null
		)

	fun findByAuthorFirstName(firstName: String, indexName: String) =
		find(
			indexName = indexName,
			query = { builder -> builder.queryString(QueryStringQuery.Builder().fields("author.firstName").query(firstName).build()) },
			filter = null,
			fields = "author.firstName",
			pageable = null,
			sort = null
		)

	fun findByTitle(title: String, indexName: String) =
		find(
			indexName = indexName,
			query = { builder -> builder.queryString(QueryStringQuery.Builder().fields("title").query(title).build()) },
			filter = null,
			fields = "title",
			pageable = null,
			sort = null
		)

	fun findById(id: UUID, indexName: String) =
		find(
			indexName = indexName,
			query = { builder -> builder.queryString(QueryStringQuery.Builder().query(id.toString()).build()) },
			filter = null,
			fields = "id",
			pageable = null,
			sort = null
		).single()
}
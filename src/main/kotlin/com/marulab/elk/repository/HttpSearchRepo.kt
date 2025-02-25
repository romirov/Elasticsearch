package com.marulab.elk.repository

import com.marulab.elk.configuration.properties.ElasticProp
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Repository

@Repository
class HttpSearchRepo(
	private val elasticProp: ElasticProp,
) {
	fun search(url: String): String? {
		val credentials: String = Credentials.basic(username = elasticProp.username, password = elasticProp.password)
		val request = Request.Builder()
			.url(url = url)
			.header("Authorization", credentials)
			.build()
		val client = OkHttpClient.Builder().build()
		return client.newCall(request).execute().use { response ->
			response.body?.string()
		}
	}
}
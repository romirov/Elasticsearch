package com.marulab.elk.service

import com.marulab.elk.ApplicationTest
import com.marulab.elk.dto.Author
import com.marulab.elk.dto.Message
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import java.util.*

class ElasticServiceTest : ApplicationTest {
	@Autowired
	lateinit var elasticService: ElasticService

	@Test
	fun `test save`() {
		val result = elasticService.save(msg1)
		println(result)
	}

	companion object {
		val id1: UUID = UUID.randomUUID()
		val id2: UUID = UUID.randomUUID()
		val id3: UUID = UUID.randomUUID()
		val author = Author("firstName1", "lastName1")
		val msg1 = Message(
			id = id1,
			title = "testMessage1",
			content = "testContent1",
			author = author,
			createdDate = LocalDateTime.now()
		)
		val msg2 = msg1.copy(id = id2, title = "testMessage2", content = "testContent2")
		val msg3 = msg1.copy(id = id3, title = "testMessage3", content = "testContent3")
	}
}
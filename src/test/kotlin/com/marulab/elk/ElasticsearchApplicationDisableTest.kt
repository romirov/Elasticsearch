package com.marulab.elk

import com.marulab.elk.configuration.ElasticTestConfig
import com.marulab.elk.configuration.TestContainersConfig
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Import
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation
import org.springframework.test.context.ActiveProfiles

@Import(TestContainersConfig::class, ElasticTestConfig::class)
@SpringBootTest(classes = [ElasticsearchEntityInformation::class])
@ActiveProfiles("disable-elastic")
class ElasticsearchApplicationDisableTest {
	@Autowired
	lateinit var applicationContext: ApplicationContext

	@Test
	fun contextLoads() {
		assertThrows<Throwable> { applicationContext.getBean("TestContainersConfig") }
		assertThrows<Throwable> { applicationContext.getBean("ElasticTestConfig") }
	}
}

package com.marulab.elk

import com.marulab.elk.configuration.ElasticTestConfig
import com.marulab.elk.configuration.TestContainersConfig
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestContainersConfig::class, ElasticTestConfig::class)
@SpringBootTest
class ElasticsearchApplicationTests {

	@Test
	fun contextLoads() {
	}

}

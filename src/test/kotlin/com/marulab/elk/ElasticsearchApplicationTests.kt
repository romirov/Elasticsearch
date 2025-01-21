package com.marulab.elk

import com.marulab.elk.configuration.ElasticTestConfig
import com.marulab.elk.configuration.TestcontainersConfig
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestcontainersConfig::class, ElasticTestConfig::class)
@SpringBootTest
class ElasticsearchApplicationTests {

	@Test
	fun contextLoads() {
	}

}

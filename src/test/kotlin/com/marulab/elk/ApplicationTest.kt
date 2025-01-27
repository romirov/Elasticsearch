//package com.marulab.elk
//
//import com.marulab.elk.configuration.ElasticTestConfig
//import com.marulab.elk.configuration.TestContainersConfig
//import com.marulab.elk.service.ElasticService
//import org.junit.jupiter.api.AfterEach
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.context.annotation.Import
//
//@Import(TestContainersConfig::class, ElasticTestConfig::class)
//@SpringBootTest
//abstract class ApplicationTest {
//	@Autowired
//	lateinit var elasticService: ElasticService
//
//	@AfterEach
//	fun cleanup() = elasticService.deleteAll()
//}
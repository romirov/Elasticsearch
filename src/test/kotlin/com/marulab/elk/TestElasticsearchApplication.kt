package com.marulab.elk

import com.marulab.elk.configuration.TestContainersConfig
import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<ElasticsearchApplication>().with(TestContainersConfig::class).run(*args)
}

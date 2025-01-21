package com.marulab.elk

import com.marulab.elk.configuration.TestcontainersConfig
import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<ElasticsearchApplication>().with(TestcontainersConfig::class).run(*args)
}

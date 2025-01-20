package com.marulab.elk

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<ElasticsearchApplication>().with(TestcontainersConfiguration::class).run(*args)
}

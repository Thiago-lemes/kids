package com.idf.kids

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KidsApplication

fun main(args: Array<String>) {
	runApplication<KidsApplication>(*args)
}

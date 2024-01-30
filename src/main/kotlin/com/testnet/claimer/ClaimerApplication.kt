package com.testnet.claimer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ClaimerApplication

fun main(args: Array<String>) {
	runApplication<ClaimerApplication>(*args)
}

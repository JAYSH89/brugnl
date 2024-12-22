package nl.jaysh.brugnl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BrugnlApplication

fun main(args: Array<String>) {
	runApplication<BrugnlApplication>(*args)
}

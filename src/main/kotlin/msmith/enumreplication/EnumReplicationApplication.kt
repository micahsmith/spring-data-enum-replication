package msmith.enumreplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class EnumReplicationApplication

fun main(args: Array<String>) {
  runApplication<EnumReplicationApplication>(*args)
}

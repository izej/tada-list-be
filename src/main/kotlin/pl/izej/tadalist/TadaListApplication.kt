package pl.izej.tadalist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class TadaListApplication

fun main(args: Array<String>) {
	runApplication<TadaListApplication>(*args)
}

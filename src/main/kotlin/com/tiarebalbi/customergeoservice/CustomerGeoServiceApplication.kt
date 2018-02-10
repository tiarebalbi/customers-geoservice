package com.tiarebalbi.customergeoservice

import com.tiarebalbi.customergeoservice.services.impl.CustomerEventSelectionService
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class CustomerGeoServiceApplication {

    /**
     * Printing all customers in the range defined
     */
    @Bean
    fun initialization(eventSelectionService: CustomerEventSelectionService): InitializingBean = InitializingBean {
        eventSelectionService.getListOfCustomersAvailable()
            .forEach {
                println("\nName: ${it.name} || ID: ${it.userId}")
            }
        println("\n-----\n")
    }

}

/**
 * Application start-up
 */
fun main(args: Array<String>) {
    SpringApplication.run(CustomerGeoServiceApplication::class.java, *args)
}

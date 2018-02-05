package com.tiarebalbi.customergeoservice

import com.tiarebalbi.customergeoservice.services.impl.CustomerEventSelectionService
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class CustomerGeoServiceApplication {

    @Bean
    fun initialization(eventSelectionService: CustomerEventSelectionService): InitializingBean = InitializingBean {
        eventSelectionService.getListOfCustomersAvailable()
            .forEach {
                println("Name: ${it.name} || ID: ${it.userId}")
            }
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(CustomerGeoServiceApplication::class.java, *args)
}

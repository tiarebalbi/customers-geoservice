/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.tiarebalbi.customergeoservice.services.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tiarebalbi.customergeoservice.model.Customer
import com.tiarebalbi.customergeoservice.services.InputSource
import net.jodah.failsafe.Failsafe
import net.jodah.failsafe.RetryPolicy
import org.slf4j.LoggerFactory
import java.io.File
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit


/**
 * Read data from a specific file
 *
 * @author Tiarê Balbi Bonamini
 * @since 2/5/18T6:43 PM
 * @version 0.1
 */
class CustomerInputSource : InputSource<String, List<Customer>> {

    private val logger = LoggerFactory.getLogger(CustomerInputSource::class.java)

    private val objectMapper = jacksonObjectMapper()

    private val retryPolicy = RetryPolicy()
        .retryOn(Exception::class.java)
        .withDelay(100, TimeUnit.MILLISECONDS)
        .withMaxRetries(2)

    /**
     * Read a classpath file and convert to a string
     *
     * @param input classpath file
     * @throws InputSourceException error on exceed the numbers of attempts to parse the file
     */
    override fun convert(input: String): List<Customer> {
        return Failsafe.with<List<Customer>>(retryPolicy)
            .onSuccess { customers -> logger.info("Detected ${customers.size} in the file") }
            .onFailedAttempt { t: Throwable -> onFailure(t) }
            .get(Callable<List<Customer>> { readFile(input) })
    }

    /**
     * Default fallback plan in case of any error
     */
    override fun onFailure(throwable: Throwable) {
        val message = "Error during process: '${throwable.message}'"
        logger.error(message)
    }

    /**
     * Read and parse each line of the file to customer
     *
     * @return list of customer
     */
    private fun readFile(input: String): List<Customer> {
        try {
            return File(ClassLoader.getSystemResource(input).file).readLines()
                .map { line -> this.parseLineToCustomer(line) }
                .sorted()
        } catch (exception: IllegalStateException) {
            throw InputSourceException("Unable to read the file")
        }
    }

    /**
     * Parse of a JSON-encoded line to a Customer
     */
    private fun parseLineToCustomer(line: String): Customer = try {
        objectMapper.readValue(line, Customer::class.java)
    } catch (exception: Exception) {
        throw InputSourceException("Unable to convert values to a customer")
    }

}

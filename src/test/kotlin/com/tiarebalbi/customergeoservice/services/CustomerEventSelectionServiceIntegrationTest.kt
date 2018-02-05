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

package com.tiarebalbi.customergeoservice.services

import com.tiarebalbi.customergeoservice.ApplicationProperties
import com.tiarebalbi.customergeoservice.services.impl.CustomerEventSelectionService
import com.tiarebalbi.customergeoservice.services.impl.InputSourceException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author Tiarê Balbi Bonamini
 * @version 0.1
 * @since 2/5/18T10:46 PM
 */
@SpringBootTest
@RunWith(SpringRunner::class)
class CustomerEventSelectionServiceIntegrationTest {

    @Autowired
    private lateinit var customerEventSelectionService: CustomerEventSelectionService

    @Autowired
    private lateinit var applicationProperties: ApplicationProperties

    @Test
    fun `should get list of customer available within limit`() {
        applicationProperties.fileWithCustomerList = "files/valid.txt"

        val result = customerEventSelectionService.getListOfCustomersAvailable()
        assertThat(result).hasSize(16)
        assertThat(result[0].userId).isEqualTo(4)
        assertThat(result[0].name).isEqualTo("Ian Kehoe")
    }

    @Test
    fun `should fail if file not found`() {
        applicationProperties.fileWithCustomerList = "not-found.txt"

        assertThatThrownBy { customerEventSelectionService.getListOfCustomersAvailable() }
            .isInstanceOf(InputSourceException::class.java)
            .hasMessageContaining("Unable to read the file")
    }

    @Test
    fun `should fail if file has invalid data`() {
        applicationProperties.fileWithCustomerList = "files/invalid.txt"

        assertThatThrownBy { customerEventSelectionService.getListOfCustomersAvailable() }
            .isInstanceOf(InputSourceException::class.java)
            .hasMessageContaining("Unable to convert values to a customer")
    }

    @Test
    fun `should be empty in case of no customer available`() {
        applicationProperties.fileWithCustomerList = "files/empty.txt"

        val result = customerEventSelectionService.getListOfCustomersAvailable()
        assertThat(result)
            .isNotNull
            .isEmpty()
    }
}
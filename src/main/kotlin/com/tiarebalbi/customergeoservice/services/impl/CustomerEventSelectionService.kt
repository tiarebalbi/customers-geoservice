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

import com.tiarebalbi.customergeoservice.ApplicationProperties
import com.tiarebalbi.customergeoservice.model.Customer
import com.tiarebalbi.customergeoservice.model.Distance
import com.tiarebalbi.customergeoservice.model.Location
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * Service responsible in verify customers availability
 *
 * @author Tiarê Balbi Bonamini
 * @since 2/5/18T10:29 PM
 * @version 0.1
 */
@Service
class CustomerEventSelectionService(private val applicationProperties: ApplicationProperties) {

    private val logger = LoggerFactory.getLogger(CustomerEventSelectionService::class.java)

    private val customerInputSource = CustomerInputSource()

    /**
     * Get list of customers available based in the preferences defined in the application properties
     *
     * @return customers List of customer within default range
     * @see ApplicationProperties
     */
    fun getListOfCustomersAvailable(): List<Customer> {
        val customers = customerInputSource.convert(applicationProperties.fileWithCustomerList)
        val distanceLimit = applicationProperties.defaultRangeInKm

        logger.info("Checking the list of customer available within $distanceLimit km from the office")

        return customers.filter {
            val distance = Distance.calculate {
                from { applicationProperties.location }
                to { Location(it.latitude, it.longitude) }
            }

            distance.kilometers <= distanceLimit
        }
    }
}
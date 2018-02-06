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

package com.tiarebalbi.customergeoservice

import com.tiarebalbi.customergeoservice.model.Location
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Custom properties used across the application
 *
 * @author Tiarê Balbi Bonamini
 * @version 0.1
 */
@Configuration
@ConfigurationProperties(prefix = "application")
class ApplicationProperties {

    /**
     * Base location to detect all customers within a range
     */
    var location = Location(0.0, 0.0)

    /**
     * Range used to detect users from a specific Location
     */
    var defaultRangeInKm = 100

    /**
     * Location of the file with all customers detail
     */
    var fileWithCustomerList: String = "classpath:files/customers.txt"
}
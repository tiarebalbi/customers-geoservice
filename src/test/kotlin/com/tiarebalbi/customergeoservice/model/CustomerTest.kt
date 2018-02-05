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

package com.tiarebalbi.customergeoservice.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * @author Tiarê Balbi Bonamini
 * @version 0.1
 * @since 2/5/18T9:23 PM
 */
class CustomerTest {
    @Test
    fun `should compare customers greater than the second option`() {
        val customer1 = Customer(12, "Customer 12", 10.0, 10.1)
        val customer2 = Customer(1, "Customer 1", 10.0, 10.1)

        val result = customer1.compareTo(customer2)

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `should compare customers lower than the second option`() {
        val customer1 = Customer(1, "Customer 1", 10.0, 10.1)
        val customer2 = Customer(20, "Customer 20", 10.0, 10.1)

        val result = customer1.compareTo(customer2)

        assertThat(result).isEqualTo(-1)
    }

    @Test
    fun `should compare two customer with the same ID`() {
        val customer1 = Customer(1, "Customer 1", 10.0, 10.1)
        val customer2 = Customer(1, "Customer 20", 10.0, 10.1)

        val result = customer1.compareTo(customer2)

        assertThat(result).isEqualTo(0)
    }
}
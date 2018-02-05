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

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

/**
 * @author Tiarê Balbi Bonamini
 * @version 0.1
 * @since 2/5/18T7:17 PM
 */
class CustomerInputSourceTest {

    private val inputSource = CustomerInputSource()

    @Test
    fun `should read valid file`() {
        val content = inputSource.convert("files/valid.txt")
        assertThat(content).hasSize(32)
    }

    @Test
    fun `should fail with invalid file`() {
        assertThatThrownBy { inputSource.convert("files/invalid.txt") }
            .isInstanceOf(InputSourceException::class.java)
    }

    @Test
    fun `should verify if valid values are sorted`() {
        val content = inputSource.convert("files/valid.txt")

        assertThat(content[0].userId).isEqualTo(1)
        assertThat(content[1].userId).isEqualTo(2)
        assertThat(content[2].userId).isEqualTo(3)
    }
}
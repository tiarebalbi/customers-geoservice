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

package com.tiarebalbi.customergeoservice.extentions

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Percentage.withPercentage
import org.junit.Test

/**
 * @author Tiarê Balbi Bonamini
 * @version 0.1
 * @since 2/5/18T9:17 PM
 */
class DegreeTest {

    @Test
    fun `should convert a degree value to radian`() {
        val radian = 180.0.toRadians()
        assertThat(radian).isCloseTo(3.1415926536, withPercentage(1.0))
    }
}
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
import org.assertj.core.api.Assertions.withinPercentage
import org.junit.Test

/**
 * @author Tiarê Balbi Bonamini
 * @version 0.1
 * @since 2/5/18T10:07 PM
 */
class DistanceTest {

    @Test
    fun `should calculate the distance from two points`() {
        val distance = Distance.calculate {
            from { Location(52.986375, -6.043701) }
            to { Location(51.92893, -10.27699) }
        }

        assertThat(distance.kilometers).isCloseTo(309.92, withinPercentage(1))
    }

    @Test
    fun `should have no distance between same location`() {
        val distance = Distance.calculate {
            from { Location(52.986375, -6.043701) }
            to { Location(52.986375, -6.043701) }
        }

        assertThat(distance.kilometers).isEqualTo(0.0)
    }
}
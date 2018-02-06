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

import com.tiarebalbi.customergeoservice.extentions.toRadians

/**
 * Model responsible in represent the distance from one location to another in kilometers
 *
 * @author Tiarê Balbi Bonamini
 * @since 2/5/18T9:50 PM
 * @version 0.1
 */
data class Distance(val from: Location, val to: Location, val kilometers: Double) {

    companion object {
        /**
         * Creates build interface to allow you define the initial location (from) and final location (to)
         *
         * @return {@code Distance} details about the distance
         */
        fun calculate(init: Builder.() -> Unit) = Builder(init).build()
    }

    /**
     * Build class
     */
    class Builder private constructor() {

        constructor(init: Builder.() -> Unit) : this() {
            init()
        }

        /**
         * Location one
         */
        private lateinit var from: Location

        /**
         * Location two
         */
        private lateinit var to: Location

        /**
         * Initial Location
         */
        fun from(init: Builder.() -> Location) = apply { from = init() }

        /**
         * Final Location
         */
        fun to(init: Builder.() -> Location) = apply { to = init() }

        /**
         * Build method to create the Distance Object
         *
         * @return {@code Distance}
         */
        fun build(): Distance = Distance(from, to, calculateDistanceBetween(from, to))

        /**
         * Calculate the distance from point A to B
         *
         * @return {@code Double} distance in kilometers
         */
        private fun calculateDistanceBetween(from: Location, to: Location): Double {
            val earthRadius = 6371.0

            val latitudeDiff = (to.latitude - from.latitude).toRadians()
            val longitudeDiff = (to.longitude - from.longitude).toRadians()

            val firstSide =
                Math.pow(Math.sin(latitudeDiff / 2), 2.0) +
                    Math.pow(Math.sin(longitudeDiff / 2), 2.0) *
                    Math.cos(from.latitude.toRadians()) *
                    Math.cos(to.latitude.toRadians())

            val secondSide = 2 * Math.asin(Math.sqrt(firstSide))

            return earthRadius * secondSide
        }
    }
}
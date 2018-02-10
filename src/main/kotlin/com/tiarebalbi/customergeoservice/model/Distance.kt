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
class Distance(val from: Location, val to: Location) {
    /**
     * Distance from point A to B in kilometers
     */
    val inKilometers: Double

    init {
        inKilometers = inKilometers(from, to)
    }

    companion object {
        /**
         * Calculate the distance from one point (from) to another location (to)
         *
         * @return {@code Distance} details about the distance
         */
        private fun inKilometers(from: Location, to: Location): Double {
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Distance

        if (from != other.from) return false
        if (to != other.to) return false

        return true
    }

    override fun hashCode(): Int {
        var result = from.hashCode()
        result = 31 * result + to.hashCode()
        return result
    }

    override fun toString(): String {
        return "Distance(from=$from, to=$to) {" +
            "inKilometers=$inKilometers" +
            "}"
    }
}
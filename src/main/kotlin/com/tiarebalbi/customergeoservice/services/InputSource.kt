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

/**
 * Input source responsible to map the source data to a specific type {@code SOURCE}
 *
 * @author Tiarê Balbi Bonamini
 * @since 2/5/18T5:43 PM
 * @version 0.1
 */
interface InputSource<in INPUT, out SOURCE> {

    /**
     * Convert a {@code INPUT} to a specific {@code SOURCE} type
     *
     * @param input of any type of data
     */
    fun convert(input: INPUT): SOURCE

    /**
     * Fallback method in case of any error during the process of read a input
     *
     * @param throwable error detected during execution
     */
    fun onFailure(throwable: Throwable)
}
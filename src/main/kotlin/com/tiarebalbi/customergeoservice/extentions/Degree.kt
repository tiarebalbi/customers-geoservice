package com.tiarebalbi.customergeoservice.extentions

/**
 * Unit used to represent Degree
 */
typealias Degree = Double

/**
 * Unit used to represent Radian
 */
typealias Radian = Double

/**
 * Convert Degree to Radian
 *
 * @return Degree converted to Radian
 */
fun Degree.toRadians(): Radian = Math.toRadians(this)
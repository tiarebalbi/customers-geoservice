package com.tiarebalbi.customergeoservice.model

import com.tiarebalbi.customergeoservice.extentions.Degree

/**
 * Model responsible to represent the latitude and longitude from a given location
 */
data class Location(var latitude: Degree, var longitude: Degree)
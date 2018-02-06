# Customer GeoService

Service responsible to detect customer within a range of a specific point

[![CircleCI](https://circleci.com/gh/tiarebalbi/customers-geoservice.svg?style=svg)](https://circleci.com/gh/tiarebalbi/customers-geoservice)
[![Kotlin](https://img.shields.io/badge/kotlin-1.2.x-blue.svg)](https://kotlinlang.org)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them
 
```
1. Java 8 or higher
```

### Installing

To install the project in your machine you need to follow the following steps:

 1. Install Java 8 on your machine
 2. Download the project files
 3. Execute the the command: ./gradlew build

## Running the tests

To execute all test you can use the following command:
   
    ./gradlew test

## Running the Project

To get the list of customers available to a given location you can use the following command:

    ./gradlew bootRun
    
Note to customize the preferences used in the application you can add the execute line above the following parameters

Preference | Parameter
--- | --- 
Latitude of the base location | -Dapplication.location.latitude={MY LATITUDE}
Longitude of the base location | -Dapplication.location.longitude={MY LONGITUDE}
Range used to find the customer | -Dapplication.default-range-in-km={NUMBER IN KM}

Example:

    ./gradlew bootRun -Dapplication.location.latitude=52.3232 \ 
       -Dapplication.location.longitude=-6.328382 \
       -Dapplication.default-range-in-km=100

## Built With

* [Kotlin](https://kotlinlang.org/) - Kotlin Language
* [Sprint Boot](https://projects.spring.io/spring-boot/) - Spring Boot

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/tiarebalbi/customers-geoservice/tags). 

## Authors

* **Tiarê Balbi Bonamini** - *Initial work* - [TiareBalbi](https://github.com/tiarebalbi)

See also the list of [contributors](https://github.com/tiarebalbi/customers-geoservice/contributors) who participated in this project.

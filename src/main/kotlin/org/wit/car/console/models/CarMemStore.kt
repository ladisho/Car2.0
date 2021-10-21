package org.wit.car.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class CarMemStore : CarStore {

    val cars = ArrayList<CarModel>()

    override fun findAll(): List<CarModel> {
        return cars
    }

    override fun findOne(id: Long): CarModel? {
        var foundCar: CarModel? = cars.find { c -> c.id == id }
        return foundCar
    }

    override fun create(car: CarModel) {
        car.id = getId()
        if (car.year == "") {
            car.year = "1900"
        }
        cars.add(car)
        logAll()

    }

    override fun update(car: CarModel) {
        var foundCar = findOne(car.id!!)
        if (foundCar != null) {
            foundCar.model = foundCar.model
            foundCar.brand = foundCar.brand
            foundCar.year = foundCar.year
            foundCar.plateNumber = foundCar.plateNumber

        }
    }

    internal fun logAll() {
        cars.forEach { logger.info("${it}") }
    }

    override fun delete(car: CarModel) {
        cars.remove(car)
    }
}

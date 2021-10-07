package org.wit.car.console.controllers

import mu.KotlinLogging
import org.wit.car.console.models.CarMemStore
import org.wit.car.console.models.CarModel
import org.wit.car.console.views.CarView

class CarController {


    val cars = CarMemStore()
    val carView = CarView()
    private val logger = KotlinLogging.logger {}

    fun init() {
        logger.info { "Launching Car Console App" }
        println("Car Kotlin App ")
    }

    fun menu() :Int { return carView.menu() }

    fun add(){
        val aCar = CarModel()

        if (carView.addCarData(aCar))
            cars.create(aCar)
        else
            logger.info("Car Not Added")
    }

    fun list() {
        carView.listCars(cars)
    }

    fun update() {
        carView.listCars(cars)
        var searchId = carView.getId()
        val aCar = search(searchId)

        if(aCar != null) {
            if(carView.updateCarData(aCar)) {
                cars.update(aCar)
                carView.showCar(aCar)
                logger.info("Car Updated : [ $aCar ]")
            }
            else
                logger.info("Car Not Updated")
        }
        else
            println("Car Not Updated...")
    }

    fun search(id: Long) : CarModel? {
        val foundCar = cars.findOne(id)
        return foundCar
    }

    fun search(){
        val aCar = search(carView.getId())!!
        carView.showCar(aCar)

    }
}

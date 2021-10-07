package org.wit.car.console.main

import mu.KotlinLogging
import org.wit.car.console.models.CarMemStore
import org.wit.car.console.models.CarModel
import org.wit.car.console.views.CarView

private val logger = KotlinLogging.logger {}

val cars = CarMemStore()
val carView = CarView()

fun main(args: Array<String>){
    logger.info { "Launching Car Console App" }
    println("Car Kotlin App ")

    var input: Int

    do {
        input = carView.menu()
        when(input) {
            1 -> addCar()
            2 -> updateCar()
            3 -> carView.listCars(cars)
            4 -> searchCar()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Car Console App" }
}


fun addCar(){
    val aCar = CarModel()

    if (carView.addCarData(aCar))
        cars.create(aCar)
    else
        logger.info("Car Not Added")

}

fun updateCar(){
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
    val foundCar: CarModel? = cars.findOne(id)
    return foundCar
}

fun searchCar(){
    val aCar = search(carView.getId())!!
    carView.showCar(aCar)

}







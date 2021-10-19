package org.wit.car.console.controllers

import mu.KotlinLogging
import org.wit.car.console.models.CarJSONStore
import org.wit.car.console.models.CarModel
import org.wit.car.console.views.CarView

class CarController {


//    val cars = CarMemStore()
    val cars = CarJSONStore()
    val carView = CarView()
    private val logger = KotlinLogging.logger {}

    fun start() {
        init()

        var input: Int

        do {
            input = menu()
            when(input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                6 -> searchbyYear()
                7 -> searchbyMultiple()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Car Console App" }
    }

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

    fun searchbyYear(){
        cars.listdistinct()
        println()
        print("Enter a year : ")
        var y = readLine()!!
        println()
        println(cars.findbyYear(y))
    }

    fun searchbyMultiple(){

        print("Enter a model : ")
        var m = readLine()!!
        print("Enter a brand : ")
        var b = readLine()!!
        print("Enter a year : ")
        var y = readLine()!!
        println()
        println(cars.findbyMultiple(m,b,y))
    }


    fun delete() {
        carView.listCars(cars)
        var searchId = carView.getId()
        val aCar = search(searchId)

        if(aCar != null) {
            cars.delete(aCar)
            println("Car Deleted...")
            carView.listCars(cars)
        }
        else
            println("Car Not Deleted...")
    }
}

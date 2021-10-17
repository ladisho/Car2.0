package org.wit.placemark.console.controllers

import mu.KotlinLogging
import org.wit.car.console.models.CarJSONStore
import org.wit.car.console.models.CarModel
//import org.wit.placemark.console.models.PlacemarkJSONStore
//import org.wit.placemark.console.models.PlacemarkModel
import org.wit.placemark.console.views.*
import tornadofx.*

class CarUIController : Controller() {

    val cars = CarJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Car TornadoFX UI App" }
    }
    fun add(_model : String, _brand : String, _year : String, _plateNumber : String){

        var aCar = CarModel(model = _model, brand = _brand, year = _year, plateNumber = _plateNumber)
            cars.create(aCar)
            logger.info("Car Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListCarScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        cars.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddCarScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddCarScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListCarScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}
package org.wit.car.console.main

import mu.KotlinLogging
import org.wit.car.console.controllers.CarController


private val logger = KotlinLogging.logger {}
val controller = CarController()


fun main(args: Array<String>) {
    controller.start()
}










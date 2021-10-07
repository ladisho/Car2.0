package org.wit.car.console.main

import mu.KotlinLogging
import org.wit.car.console.controllers.CarController


private val logger = KotlinLogging.logger {}
val controller = CarController()


fun main(args: Array<String>){
    controller.init()

    var input: Int

    do {
        input = controller.menu()
        when(input) {
            1 -> controller.add()
            2 -> controller.update()
            3 -> controller.list()
            4 -> controller.search()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Car Console App" }
}










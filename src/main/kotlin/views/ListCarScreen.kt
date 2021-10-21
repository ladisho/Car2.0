package org.wit.placemark.console.views

import org.wit.car.console.models.CarModel
import org.wit.placemark.console.controllers.CarUIController

import tornadofx.*

class ListCarScreen : View("List Cars") {

    val carUIController: CarUIController by inject()
    val tableContent = carUIController.cars.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", CarModel::id)
            readonlyColumn("MODEL", CarModel::model)
            readonlyColumn("BRAND", CarModel::brand)
            readonlyColumn("YEAR", CarModel::year)
            readonlyColumn("PLATENUMBER", CarModel::plateNumber)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    carUIController.closeList()
                }
            }
        }
    }

}
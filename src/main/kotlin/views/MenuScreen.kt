package org.wit.placemark.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.placemark.console.controllers.CarUIController
//import org.wit.placemark.console.controllers.PlacemarkUIController
import tornadofx.*

class MenuScreen : View("Placemark Main Menu") {

    val carUIController: CarUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Car") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        carUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Cars") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        carUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}
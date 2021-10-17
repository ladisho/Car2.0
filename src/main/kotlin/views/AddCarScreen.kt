package org.wit.placemark.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.placemark.console.controllers.CarUIController
//import org.wit.placemark.console.controllers.PlacemarkUIController
import tornadofx.*
import kotlin.reflect.KClass

class AddCarScreen : View("Add Car") {
    val model = ViewModel()
    val _model = model.bind { SimpleStringProperty() }
    val _brand = model.bind { SimpleStringProperty() }
    val _year = model.bind { SimpleStringProperty() }
    val _plateNumber = model.bind { SimpleStringProperty() }
    val carUIController: CarUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Model") {
                textfield(_model).required()
            }
            field("Brand") {
                textfield(_brand).required()
            }
            field("Year") {
                textfield(_year).required()
            }
            field("PlateNumber") {
                textfield(_plateNumber).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        carUIController.add(_model.toString(),_brand.toString(),_year.toString(),_plateNumber.toString())

                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        carUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _model.value = ""
        _brand.value = ""
        _year.value = ""
        _plateNumber.value = ""
        model.clearDecorators()
    }
}
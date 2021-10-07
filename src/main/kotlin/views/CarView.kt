package org.wit.car.console.views

import org.wit.car.console.main.carView
import org.wit.car.console.main.cars
import org.wit.car.console.models.CarMemStore
import org.wit.car.console.models.CarModel

class CarView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Car")
        println(" 2. Update Car")
        println(" 3. List All Cars")
        println(" 4. Search Cars")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listCars(cars : CarMemStore) {
        println("List All Cars")
        println()
        cars.logAll()
        println()
    }

    fun showCar(car : CarModel) {
        if(car != null)
            println("Car Details [ $car ]")
        else
            println("Car Not Found...")
    }

    fun addCarData(car : CarModel) : Boolean {

        println()
        print("Enter the Model : ")
        car.model = readLine()!!
        print("Enter the Brand : ")
        car.brand = readLine()!!
        print("Enter the Year : ")
        car.year = readLine()!!
        print("Enter the PlateNumber : ")
        car.plateNumber = readLine()!!

        return car.model.isNotEmpty() && car.brand.isNotEmpty() && car.year.isNotEmpty() && car.plateNumber.isNotEmpty()
    }

    fun updateCarData(car : CarModel) : Boolean {

        var tempModel: String?
        var tempBrand: String?
        var tempYear: String?
        var tempPlateNumber: String?

        if (car != null) {
            print("Enter a new Model for [ " + car.model + " ] : ")
            tempModel = readLine()!!
            print("Enter a new Brand for [ " + car.brand + " ] : ")
            tempBrand = readLine()!!
            print("Enter a new Year for [ " + car.year + " ] : ")
            tempYear = readLine()!!
            print("Enter a new PlateNumber for [ " + car.plateNumber + " ] : ")
            tempPlateNumber = readLine()!!

            if (!tempModel.isNullOrEmpty() && !tempBrand.isNullOrEmpty() && !tempYear.isNullOrEmpty() && !tempPlateNumber.isNullOrEmpty()) {
                car.model = tempModel
                car.brand = tempBrand
                car.year = tempYear
                car.plateNumber = tempPlateNumber
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}
package org.wit.car.console.main

import mu.KotlinLogging
import org.wit.car.console.models.CarMemStore
import org.wit.car.console.models.CarModel

private val logger = KotlinLogging.logger {}

//var model : String = ""
//var brand : String = ""
//var year : Int = 0
//var plateNumber : String = ""

//var car = CarModel()

val cars = CarMemStore()

fun main(args: Array<String>){
    logger.info { "Launching Car Console App" }
    println("Car Kotlin App ")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addCar()
            2 -> updateCar()
            3 -> listAllCars()
            4 -> searchCars()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Car Console App" }
}

fun menu() : Int {

    val option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Add Car")
    println(" 2. Update a Car")
    println(" 3. List All Cars")
    println(" 4. Search Cars")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9

    return option
}

fun addCar(){

    val aCar = CarModel()
    println("Add Car")
    println()
    print("Enter the Model : ")
    aCar.model = readLine()!!
    print("Enter the Brand : ")
    aCar.brand = readLine()!!
    print("Enter the Year : ")

    aCar.year = readLine()!!
    print("Enter the PlateNumber : ")
    aCar.plateNumber = readLine()!!
    if ( aCar.brand.isNotEmpty() && aCar.model.isNotEmpty() && aCar.plateNumber.isNotEmpty() && aCar.year.isNotEmpty()  ){
        aCar.id = cars.findAll().size.toLong()
        cars.create(aCar.copy())
//        aCar.id++
        logger.info("Car Added : [ $aCar ]")
//        println()
//        println("You entered [ ${car.model} ] for model" + "\n"
//                + "You entered [ ${car.brand} ] for brand" + "\n"
//                + "You entered [ ${car.year} ] for year" + "\n"
//                + "You entered [ ${car.plateNumber} ] for plateNumber"
//        )
    }else{
        logger.info("Car Not Added")
    }

}

fun updateCar(){


    println("Update Car")
    println()
    searchCar()

}

fun listAllCars(){
    println("List Of All Cars")
    println()
//    cars.forEach { logger.info("${it}") }
    println(cars)
}

fun getId() : Long {
    val strId : String? // String to hold user input
    val searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : CarModel? {
    val foundCar: CarModel? = cars.findOne(id)
    return foundCar
}

fun searchCars(){
    val id = getId()
    println(search(id))

}

fun searchCar(){
    println("List Of All Cars")
    println(cars)
    println()
    val id = getId()
    val aCar = search(id)
    if (aCar != null){
        print("Enter a new Model for [ ${aCar.model} ] : ")
        val mo = readLine()!!
        print("Enter a new Brand for [ ${aCar.brand} ] : ")
        val br = readLine()!!
        print("Enter a new Year for [ ${aCar.year} ] : ")
        val yr = readLine()!!
        print("Enter a new PlateNumber for [ ${aCar.plateNumber} ] : ")
        val pl = readLine()!!
        if (mo != "" && br != "" && pl != "" && yr != ""){
            aCar.model = mo
            aCar.brand = br
            aCar.year = yr
            aCar.plateNumber = pl
            println()
            println("You updated [ ${aCar.model} ] for model" + "\n"
                    + "You updated [ ${aCar.brand} ] for brand" + "\n"
                    + "You updated [ ${aCar.year} ] for year" + "\n"
                    + "You updated [ ${aCar.plateNumber} ] for plateNumber"
            )

        }else{
            logger.info { "Car Not Updated" }
        }


    } else{
        println("Car Not Updated...")
    }




}
package org.wit.car.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.placemark.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "cars.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<CarModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class CarJSONStore : CarStore {

    var cars = mutableListOf<CarModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<CarModel> {
        return cars
    }

    fun listdistinct() {
        var list: List<CarModel> = cars.distinctBy { c -> c.year }
        println("list of cars by yr")
        println()
        println(list)
    }


    fun findbyYear(year: String): List<CarModel> {
        var foundCars: List<CarModel> = cars.filter { c -> c.year.equals(year) }
        return foundCars
    }

    fun findbyMultiple(model: String, brand: String, year: String): List<CarModel> {
        var foundCars: List<CarModel> =
            cars.filter { c -> (c.model.equals(model) && c.brand.equals(brand) && c.year.equals(year)) }
        return foundCars
    }

    override fun findOne(id: Long): CarModel? {
        var foundCar: CarModel? = cars.find { c -> c.id == id }
        return foundCar
    }

    override fun create(car: CarModel) {
        car.id = generateRandomId()
        cars.add(car)
        serialize()
    }

    override fun update(car: CarModel) {
        var foundCar = findOne(car.id!!)
        if (foundCar != null) {
            foundCar.model = car.model
            foundCar.brand = car.brand
            foundCar.year = car.year
            foundCar.plateNumber = car.plateNumber
        }
        serialize()
    }

    internal fun logAll() {
        cars.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(cars, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        cars = Gson().fromJson(jsonString, listType)
        if (cars.size == 0) {
            dummyData()
        }
    }

    override fun delete(car: CarModel) {
        cars.remove(car)
        serialize()
    }

    fun dummyData() {
        cars.add(CarModel(0, "123", "audi", "2000", "audi123"))
        cars.add(CarModel(1, "123", "audi", "2000", "audi123"))
        cars.add(CarModel(2, "123", "audi", "2000", "audi123"))

    }
}


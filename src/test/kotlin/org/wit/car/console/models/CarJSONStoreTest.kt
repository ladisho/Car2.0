package org.wit.car.console.models

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CarJSONStoreTest {
    val cars = CarJSONStore()
    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun findOne() {
        var foundCar = cars.findOne(7911894293649374434);
        if (foundCar != null) {
            assertEquals("audi",foundCar.brand)
        };
    }

    @Test
    fun createMain(){
        var car1 = CarModel(0,"uio","venza","2010","uio890")
        cars.create(car1)
        assertEquals("venza",car1.brand)

    }

    @Test
    fun updateMain(){
        var car = CarModel(8870529816667162457,"uio","venza","2010","uio890")
        var foundCar = cars.findOne(car.id)
        if (foundCar != null){
            foundCar.model = "zxc"
            foundCar.brand = "jaguar"
            foundCar.year = "2021"
            foundCar.plateNumber = "zxc123"
        }
        if (foundCar != null) {
            cars.update(foundCar)
        }
        if (foundCar != null) {
            assertEquals("2021",foundCar.year)
        }
        if (foundCar != null) {
            assertNotEquals(car.model,foundCar.model)
        }
    }

    @Test
    fun deleteMain(){
        var car = CarModel(0,"uio","venza","2010","uio890")
        cars.create(car)
        cars.delete(car)
        cars.findOne(car.id)
    }

}
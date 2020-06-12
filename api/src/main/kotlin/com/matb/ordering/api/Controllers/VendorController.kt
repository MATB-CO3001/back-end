package com.matb.ordering.api.Controllers

import com.matb.ordering.api.models.entities.Food
import com.matb.ordering.api.models.entities.Vendor
import com.matb.ordering.api.models.repositories.FoodRepository
import com.matb.ordering.api.models.repositories.VendorRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class VendorController(private val vendorRepository: VendorRepository, private val foodRepository: FoodRepository){
    @PostMapping("/vendor")
    fun createVendor(@RequestBody vendor: Vendor) = vendorRepository.save(vendor)

    @GetMapping("/vendor/{id}")
    fun getVendorByName(@PathVariable(value = "id") vendorId: Int): Vendor{
        return vendorRepository.findAllById(vendorId)
    }
    @PostMapping("/vendor/{id}")
    fun createFood(@PathVariable (value = "id") vendorID: Int, @RequestBody food: Food): Food {
        var vendor = vendorRepository.findAllById(vendorID)
        food.vendor = vendor
        return foodRepository.save(food)
    }
    @GetMapping("/vendor")
    fun getAllVendor(): List<Vendor> = vendorRepository.findAll()
}
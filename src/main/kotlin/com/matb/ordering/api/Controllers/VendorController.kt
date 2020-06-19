package com.matb.ordering.api.Controllers

import com.matb.ordering.api.models.ActivityState
import com.matb.ordering.api.models.entities.Food
import com.matb.ordering.api.models.entities.Vendor
import com.matb.ordering.api.models.repositories.FoodRepository
import com.matb.ordering.api.models.repositories.VendorRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class VendorController(private val vendorRepository: VendorRepository, private val foodRepository: FoodRepository){
    @PostMapping("/vendor")
    fun createVendor(@RequestBody vendor: Vendor) : ResponseEntity<Vendor>{
        vendor.state = ActivityState.ACTIVE
        return ResponseEntity(vendorRepository.save(vendor), HttpStatus.OK)
    }

    @GetMapping("/vendor/{id}")
    fun getVendorById(@PathVariable(value = "id") vendorId: Int): ResponseEntity<Vendor>{
        return ResponseEntity(vendorRepository.findAllById(vendorId),  HttpStatus.OK)
    }

    @PostMapping("/vendor/{id}")
    fun createFood(@PathVariable (value = "id") vendorID: Int, @RequestBody food: Food): ResponseEntity<Food> {
        var vendor = vendorRepository.findAllById(vendorID)
        food.vendor = vendor
        return ResponseEntity(foodRepository.save(food), HttpStatus.OK)
    }

    @GetMapping("/vendor")
    fun getAllVendor(): ResponseEntity<List<Vendor>>{
        return ResponseEntity(vendorRepository.findAll(), HttpStatus.OK)
    }
}
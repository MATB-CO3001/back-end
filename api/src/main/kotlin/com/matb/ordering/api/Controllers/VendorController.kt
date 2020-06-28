package com.matb.ordering.api.Controllers

import com.matb.ordering.api.models.ActivityState
import com.matb.ordering.api.models.entities.Food
import com.matb.ordering.api.models.entities.Vendor
import com.matb.ordering.api.models.repositories.FoodRepository
import com.matb.ordering.api.models.repositories.VendorRepository
import com.matb.ordering.api.models.responses.VendorResponse
import com.matb.ordering.api.models.toVendorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/vendor")
class VendorController(private val vendorRepository: VendorRepository, private val foodRepository: FoodRepository){
    @PostMapping
    fun createVendor(@RequestBody vendor: Vendor) : ResponseEntity<Vendor>{
        vendor.state = ActivityState.ACTIVE
        return ResponseEntity(vendorRepository.save(vendor), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getVendorById(@PathVariable(value = "id") vendorId: Int): ResponseEntity<VendorResponse>{
        var vendor = vendorRepository.findAllById(vendorId)
        return ResponseEntity(
                vendor.toVendorResponse(
                        foodRepository.findAllByVendorId(vendor.id!!)
                ),
                HttpStatus.OK)
    }

    @PostMapping("/{id}")
    fun createFood(@PathVariable (value = "id") vendorId: Int, @RequestBody food: Food): ResponseEntity<Food> {
        food.vendorId = vendorId
        return ResponseEntity(foodRepository.save(food), HttpStatus.OK)
    }

    @GetMapping
    fun getAllVendor(): ResponseEntity<List<VendorResponse>>{
        return ResponseEntity(
                vendorRepository.findAll().map {
                    it.toVendorResponse(foodRepository.findAllByVendorId(it.id!!))
                },
                HttpStatus.OK
        )
    }
}
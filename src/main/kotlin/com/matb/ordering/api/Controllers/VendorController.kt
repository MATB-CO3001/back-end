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
    fun createVendor(@RequestBody vendor: Vendor) : ResponseEntity<Any>{
//        if (vendorRepository.existsByUsername(vendor.username)){
//            return ResponseEntity("Username is already taken!", HttpStatus.CONFLICT)
//        }
//        vendor.state = ActivityState.ACTIVE
//        return ResponseEntity(vendorRepository.save(vendor), HttpStatus.OK)
        return ResponseEntity("àdll", HttpStatus.CONFLICT)
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
        //food.vendorId = vendorId
        return ResponseEntity(foodRepository.save(food), HttpStatus.OK)
    }

    @PostMapping("/{id}/update")
    fun updateVendor(@PathVariable (value = "id") vendorId: Int, @RequestBody vendor: Vendor): ResponseEntity<Vendor> {
        var vendorCur = vendorRepository.findById(vendorId).get()
        vendorCur.name = vendor.name
        return ResponseEntity(vendorRepository.save(vendorCur), HttpStatus.OK)
    }
    @PostMapping("/{foodId}/update")
    fun updateFood(@PathVariable (value = "foodId") foodId: Int, @RequestBody food: Food): ResponseEntity<Food> {
        var foodCur = foodRepository.findById(foodId).get()
        if (food.name != "") {
            foodCur.name = food.name
        }
        if (food.price != 0) {
            foodCur.price = food.price
        }
        if (food.image != "") {
            foodCur.image = food.image
        }
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
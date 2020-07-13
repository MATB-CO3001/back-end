package com.matb.ordering.api.Controllers

import com.matb.ordering.api.models.entities.Food
import com.matb.ordering.api.models.entities.Report
import com.matb.ordering.api.models.entities.Vendor
import com.matb.ordering.api.models.repositories.FoodRepository
import com.matb.ordering.api.models.repositories.ReportRepository
import com.matb.ordering.api.models.repositories.VendorRepository
import com.matb.ordering.api.models.requests.creation.FoodRequest
import com.matb.ordering.api.models.requests.creation.VendorRequest
import com.matb.ordering.api.models.responses.VendorResponse
import com.matb.ordering.api.models.toFood
import com.matb.ordering.api.models.toVendor
import com.matb.ordering.api.models.toVendorResponse
import org.apache.tomcat.jni.Local
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.YearMonth
import javax.transaction.Transactional

@RestController
@RequestMapping("/api/vendor")
class VendorController(
        private val encoder: BCryptPasswordEncoder,
        private val vendorRepository: VendorRepository,
        private val foodRepository: FoodRepository,
        private val reportRepository: ReportRepository
){

    @PostMapping
    fun createVendor(@RequestBody vendor: VendorRequest) : ResponseEntity<Any>{
        if (vendorRepository.existsByUsername(vendor.username)){
            return ResponseEntity("Username is already taken!", HttpStatus.CONFLICT)
        }
        vendor.password = encoder.encode(vendor.password)
        return ResponseEntity(vendorRepository.save(vendor.toVendor()), HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllVendor(): ResponseEntity<List<VendorResponse>>{
        return ResponseEntity(
                vendorRepository.findAll().map {
                    it.toVendorResponse()
                }, HttpStatus.OK)
    }

    @GetMapping("/{username}")
    fun getVendorByUsername(@PathVariable(name = "username", required = true) username: String): ResponseEntity<VendorResponse>{
        return ResponseEntity(
                vendorRepository.findByUsername(username).get().toVendorResponse(),
                HttpStatus.OK)
    }

    @PutMapping
    fun updateVendor(@RequestBody vendor: VendorRequest) : ResponseEntity<Any>{
        if (!vendorRepository.existsByUsername(vendor.username)){
            return ResponseEntity("User not found!", HttpStatus.NOT_FOUND)
        }
        var oldVendor = vendorRepository.findByUsername(vendor.username).get()
        if (vendor.password != "") {
            oldVendor.password = encoder.encode(vendor.password)
        }
        oldVendor.name = vendor.name
        return ResponseEntity(vendorRepository.save(oldVendor), HttpStatus.OK)
    }

    @DeleteMapping("/{username}")
    fun deleteVendor(@PathVariable(name = "username") username: String) : ResponseEntity<Void> {
        vendorRepository.deleteById(vendorRepository.findByUsername(username).get().id!!)
        return ResponseEntity(HttpStatus.OK)
    }

    // Food
    @PostMapping("/{username}")
    fun createFood(@PathVariable(name = "username", required = true) username: String, @RequestBody foodRequest: FoodRequest): ResponseEntity<Any> {
        var vendor = vendorRepository.findByUsername(username)
        if (!vendor.isPresent)
            return ResponseEntity("Vendor not found", HttpStatus.NOT_FOUND)
        return ResponseEntity(
                foodRepository.save(
                        foodRequest.toFood(vendor.get())), HttpStatus.OK)
    }

    @PutMapping("/food/{id}")
    fun updateFood(
            @PathVariable (value = "id") foodId: Int,
            @RequestBody foodRequest: FoodRequest): ResponseEntity<Any> {
        var food = foodRepository.findById(foodId)
        if (!food.isPresent) {
            return ResponseEntity("Food not found", HttpStatus.NOT_FOUND)
        }
        var foodUpdate = foodRequest.toFood(food.get().vendor!!)
        foodUpdate.id = foodId
        foodUpdate.state = foodRequest.state
        return ResponseEntity(foodRepository.save(foodUpdate), HttpStatus.OK)
    }



    @DeleteMapping("/food/{id}")
    fun deleteFood(@PathVariable(value = "id") foodId: Int): ResponseEntity<Void> {
        foodRepository.deleteById(foodId)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/{username}/createall")
    fun createAllFood(
            @PathVariable(name = "username", required = true) username: String,
            @RequestBody listFood: List<FoodRequest>
    ): String {
        var vendor = vendorRepository.findByUsername(username).get()
        for (item in listFood) {
            ResponseEntity(foodRepository.save(item.toFood(vendor)), HttpStatus.OK)
        }
        return "All foods have been created :')"
    }

    @GetMapping("/report/{username}/{yearmonth}")
    fun getReport(@PathVariable(name = "username", required = true) username: String,
                  @PathVariable(name = "yearmonth", required = true) yearmonth: String
    ):ResponseEntity<List<Report>> {
        var startDate = LocalDate.parse(yearmonth+"-01")
        var endDate = startDate.plusDays(startDate.lengthOfMonth().toLong()-1)
        return ResponseEntity(reportRepository.findAllByDateBetween(startDate,endDate), HttpStatus.OK)
    }
}
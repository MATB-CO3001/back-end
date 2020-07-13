package com.matb.ordering.api.Controllers

import com.matb.ordering.api.models.CartState
import com.matb.ordering.api.models.entities.Cart
import com.matb.ordering.api.models.entities.Report
import com.matb.ordering.api.models.entities.ReportItem
import com.matb.ordering.api.models.repositories.CartRepository
import com.matb.ordering.api.models.repositories.ChefRepository
import com.matb.ordering.api.models.repositories.ReportItemRepository
import com.matb.ordering.api.models.repositories.ReportRepository
import com.matb.ordering.api.models.requests.CartStateUpdatingRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.ZoneId

@RestController
@RequestMapping("/api/chef")
class ChefController (
        private val cartRepository: CartRepository,
        private val chefRepository: ChefRepository,
        private val reportRepository: ReportRepository,
        private val reportItemRepository: ReportItemRepository
){
    @GetMapping("/{username}/pending")
    fun getAllPendingCart(@PathVariable (value = "username") username: String): ResponseEntity<List<Cart>> {
        return ResponseEntity(cartRepository.findAllByVendorAndState(username, CartState.PENDING), HttpStatus.OK)
    }

    @GetMapping("/{username}/inprogress")
    fun getAllInprogressCart(@PathVariable (value = "username") username: String): ResponseEntity<List<Cart>> {
        return ResponseEntity(cartRepository.findAllByVendorAndState(username, CartState.INPROGRESS), HttpStatus.OK)
    }

    @PostMapping
    fun updateCartState(@RequestBody cartStateUpdatingRequest: CartStateUpdatingRequest): ResponseEntity<Cart> {
        val newUpdatedStateCart = cartRepository.findById(cartStateUpdatingRequest.cartId).get()
        newUpdatedStateCart.state = cartStateUpdatingRequest.newState

        var date = LocalDate.now(ZoneId.of("GMT+07:00"))
        var report: Report
        if (reportRepository.existsByDate(date)) {
            report = reportRepository.findByDate(date).get()
        } else {
            report = reportRepository.save(Report(date,0))
        }
        var reportItem: ReportItem
        for (item in newUpdatedStateCart.cartItem!!){
            if (reportItemRepository.existsByReportAndFood(report,item.food!!)) {
                reportItem = reportItemRepository.findByReportAndFood(report,item.food!!).get()
            } else {
                reportItem = reportItemRepository.save(ReportItem(item.food!!,report))
            }
            reportItem.quantity+=item.quantity;
        }
        report.total+=newUpdatedStateCart.total
        return ResponseEntity(cartRepository.save(newUpdatedStateCart), HttpStatus.OK)
    }
}
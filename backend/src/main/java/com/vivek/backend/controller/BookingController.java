package com.vivek.backend.controller;

import com.vivek.backend.model.Booking;
import com.vivek.backend.sevice.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/request")
    public Booking request(@RequestParam Long userId,
                           @RequestParam String from,
                           @RequestParam String to) {
        return bookingService.createRequest(userId, from, to);
    }

    @PostMapping("/{bookingId}/offer")
    public Booking offer(@PathVariable Long bookingId,
                         @RequestParam Long providerId,
                         @RequestParam double price) {
        return bookingService.offerPrice(bookingId, providerId, price);
    }

    @PostMapping("/{bookingId}/confirm")
    public Booking confirm(@PathVariable Long bookingId,
                           @RequestParam Long userId) {
        return bookingService.confirmBooking(bookingId, userId);
    }
}


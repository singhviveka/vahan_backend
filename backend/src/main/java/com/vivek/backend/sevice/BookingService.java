package com.vivek.backend.sevice;

import com.vivek.backend.model.Booking;
import com.vivek.backend.model.User;
import com.vivek.backend.repo.BookingRepository;
import com.vivek.backend.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepo;
    private final UserRepository userRepo;

    public Booking createRequest(Long userId, String from, String to) {
        User user = userRepo.findById(userId).orElseThrow();
        if (user.getRole() != User.Role.USER) {
            throw new IllegalArgumentException("Only users can create requests");
        }
        return bookingRepo.save(Booking.builder()
                .user(user)
                .fromLocation(from)
                .toLocation(to)
                .confirmed(false)
                .build());
    }

    public Booking offerPrice(Long bookingId, Long providerId, double price) {
        Booking booking = bookingRepo.findById(bookingId).orElseThrow();
        User provider = userRepo.findById(providerId).orElseThrow();
        if (provider.getRole() != User.Role.PROVIDER) {
            throw new IllegalArgumentException("Only providers can offer prices");
        }
        booking.setProvider(provider);
        booking.setOfferPrice(price);
        return bookingRepo.save(booking);
    }

    public Booking confirmBooking(Long bookingId, Long userId) {
        Booking booking = bookingRepo.findById(bookingId).orElseThrow();
        if (!booking.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Only the request creator can confirm booking");
        }
        booking.setConfirmed(true);
        return bookingRepo.save(booking);
    }
}

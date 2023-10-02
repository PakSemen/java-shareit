package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingShortDto;

import java.util.List;

import static ru.practicum.shareit.util.Constants.REQUEST_HEADER_USER_ID;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Slf4j
public class BookingController {
    private final BookingService bookingService;

    @PostMapping()
    public BookingDto addBooking(@RequestHeader(REQUEST_HEADER_USER_ID) Long userId,
                                 @RequestBody BookingShortDto bookingShortDto) {
        log.info("Add booking: {} for user with id = {}",bookingShortDto, userId);
        return bookingService.createBooking(userId, bookingShortDto);
    }

    @GetMapping("/{bookingId}")
    public BookingDto getBookingById(@RequestHeader(REQUEST_HEADER_USER_ID) Long userId,
                                     @PathVariable Long bookingId) {
        log.info("Get booking with id = {} from user with id = {}", bookingId, userId);
        return bookingService.getBookingById(userId, bookingId);
    }

    @GetMapping()
    public List<BookingDto> getAllBookingByState(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                 @RequestParam(defaultValue = "ALL") String state,
                                                 @RequestParam(defaultValue = "0") Integer from,
                                                 @RequestParam(defaultValue = "10") Integer size) {
        log.info("Get all booking with state = {} for user with id = {}",state, userId);
        return bookingService.getAllBookingByState(userId, state, from, size);
    }

    @GetMapping("/owner")
    public List<BookingDto> getAllItemsBookings(@RequestHeader("X-Sharer-User-Id") Long id,
                                                @RequestParam(defaultValue = "ALL") String state,
                                                @RequestParam(defaultValue = "0") Integer from,
                                                @RequestParam(defaultValue = "10") Integer size) {
        log.info("Get all owners booking with states = {}",state);
        return bookingService.getAllOwnersBookingByState(id, state, from, size);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto approveBooking(@RequestHeader(REQUEST_HEADER_USER_ID) Long userId,
                                     @PathVariable Long bookingId,
                                     @RequestParam Boolean approved) {
        log.info("Approve booking id = {} for user with id = {} is {}",bookingId, userId, approved);
        return bookingService.approveBooking(userId, bookingId, approved);
    }

}

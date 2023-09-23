package ru.practicum.shareit.booking.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.shareit.booking.Status;
import ru.practicum.shareit.booking.model.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByBookerId(Long bookerId, Sort sort);

    List<Booking> findAllByBookerIdAndStartBeforeAndEndAfter(Long bookerId, LocalDateTime startTime,
                                                             LocalDateTime endDate, Sort sort);

    List<Booking> findAllByBookerIdAndEndBefore(Long bookerId, LocalDateTime endDate, Sort sort);

    List<Booking> findAllByBookerIdAndStartAfter(Long bookerId, LocalDateTime startDate, Sort sort);

    List<Booking> findAllByBookerIdAndStatus(Long bookerId, Status status, Sort sort);

    Boolean existsByBookerIdAndEndBeforeAndStatus(Long bookerId, LocalDateTime endDate, Status status);

    List<Booking> findAllByItemOwnerId(Long ownerId, Sort sort);

    List<Booking> findAllByItemOwnerIdAndEndBefore(Long bookerId, LocalDateTime endDate, Sort sort);

    List<Booking> findAllByItemOwnerIdAndStartBeforeAndEndAfter(Long bookerId, LocalDateTime startDate,
                                                                LocalDateTime endDate, Sort sort);

    List<Booking> findAllByItemOwnerIdAndStartAfter(Long bookerId, LocalDateTime startDate, Sort sort);

    List<Booking> findAllByItemOwnerIdAndStatus(Long bookerId, Status status, Sort sort);

    Optional<Booking> findFirstByItemIdAndStartBeforeAndStatusOrderByStartDesc(Long itemId, LocalDateTime startDate,
                                                                               Status status);

    Optional<Booking> findFirstByItemIdAndStartAfterAndStatusOrderByStartAsc(Long itemId, LocalDateTime startDate,
                                                                             Status status);

    Optional<Booking> findFirstByItemIdInAndStartLessThanEqualAndStatus(List<Long> idItems, LocalDateTime startDate,
                                                                        Status approved, Sort sort);

    Optional<Booking> findFirstByItemIdInAndStartAfterAndStatus(List<Long> idItems, LocalDateTime startDate,
                                                                Status approved, Sort sort);

}


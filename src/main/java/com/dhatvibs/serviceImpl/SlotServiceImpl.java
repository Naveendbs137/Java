package com.dhatvibs.serviceImpl;

import com.dhatvibs.entity.*;
import com.dhatvibs.repository.*;
import com.dhatvibs.service.SlotService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SlotServiceImpl implements SlotService {

    private final WeeklySlotRepository weeklyRepo;
    private final DailySlotRepository dailyRepo;
    private final SlotBookingRepository bookingRepo;

    public SlotServiceImpl(
        WeeklySlotRepository weeklyRepo,
        DailySlotRepository dailyRepo,
        SlotBookingRepository bookingRepo
    ) {
        this.weeklyRepo = weeklyRepo;
        this.dailyRepo = dailyRepo;
        this.bookingRepo = bookingRepo;
    }

    @Override
    public List<DailySlot> getWeekSlots(int week, int year, String city, String zone) {
        WeeklySlot ws = weeklyRepo
            .findByWeekNumberAndYearAndCityAndZone(week, year, city, zone)
            .orElseThrow(() -> new RuntimeException("Weekly slots not found"));
        return ws.getSlots();
    }

    @Override
    public List<DailySlot> getDaySlots(LocalDate date) {
        return dailyRepo.findByDateAndIsVisibleTrue(date);
    }

    @Override
    public SlotBooking bookSlot(Long slotId, Long riderId) {
        DailySlot slot = dailyRepo.findById(slotId)
            .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.getBookedRiders() >= slot.getMaxRiders())
            throw new RuntimeException("Slot full");

        slot.setBookedRiders(slot.getBookedRiders() + 1);
        dailyRepo.save(slot);

        SlotBooking booking = new SlotBooking();
        booking.setRiderId(riderId);
        booking.setDailySlot(slot);
        booking.setDate(slot.getDate());
        booking.setStatus(SlotBookingStatus.BOOKED);
        booking.setBookedFrom("APP");

        return bookingRepo.save(booking);
    }

    @Override
    public void cancelSlot(Long bookingId, String reason) {
        SlotBooking booking = bookingRepo.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(SlotBookingStatus.CANCELLED_BY_RIDER);
        booking.setCancellationReason(reason);
        bookingRepo.save(booking);
    }

    @Override
    public SlotBooking getCurrentSlot(Long riderId) {
        return bookingRepo
            .findByRiderIdAndStatus(riderId, SlotBookingStatus.BOOKED)
            .orElse(null);
    }

    @Override
    public List<SlotBooking> getHistory(Long riderId) {
        return bookingRepo.findByRiderIdOrderByCreatedAtDesc(riderId);
    }
    
    @Override
    public SlotBooking getSlotStatus(Long riderId) {
        return bookingRepo
            .findByRiderIdAndStatus(riderId, SlotBookingStatus.BOOKED)
            .orElse(null);
    }

}

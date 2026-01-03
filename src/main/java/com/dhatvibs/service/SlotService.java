package com.dhatvibs.service;

import com.dhatvibs.entity.*;

import java.time.LocalDate;
import java.util.List;

public interface SlotService {

    List<DailySlot> getWeekSlots(int week, int year, String city, String zone);

    List<DailySlot> getDaySlots(LocalDate date);

    SlotBooking bookSlot(Long slotId, Long riderId);

    void cancelSlot(Long bookingId, String reason);

    SlotBooking getCurrentSlot(Long riderId);

    List<SlotBooking> getHistory(Long riderId);
    
    SlotBooking getSlotStatus(Long riderId);
}

package com.dhatvibs.controller;

import com.dhatvibs.entity.*;
import com.dhatvibs.service.SlotService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/slots")
@Tag(name = "Slot Booking APIs")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @GetMapping("/week")
    public List<DailySlot> week(
        @RequestParam int week,
        @RequestParam int year,
        @RequestParam String city,
        @RequestParam String zone) {
        return slotService.getWeekSlots(week, year, city, zone);
    }

    @GetMapping("/day")
    public List<DailySlot> day(@RequestParam String date) {
        return slotService.getDaySlots(LocalDate.parse(date));
    }

    @PostMapping("/{id}/book")
    public SlotBooking book(
        @PathVariable Long id,
        @RequestParam Long riderId) {
        return slotService.bookSlot(id, riderId);
    }

    @PostMapping("/{id}/cancel")
    public void cancel(
        @PathVariable Long id,
        @RequestParam String reason) {
        slotService.cancelSlot(id, reason);
    }

    @GetMapping("/current")
    public SlotBooking current(@RequestParam Long riderId) {
        return slotService.getCurrentSlot(riderId);
    }

    @GetMapping("/history")
    public List<SlotBooking> history(@RequestParam Long riderId) {
        return slotService.getHistory(riderId);
    }
    
    @GetMapping("/status")
    public SlotBooking slotStatus(@RequestParam Long riderId) {
        return slotService.getSlotStatus(riderId);
    }
}

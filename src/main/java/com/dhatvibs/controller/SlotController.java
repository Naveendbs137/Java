/*
 * package com.dhatvibs.controller;
 * 
 * import com.dhatvibs.entity.*; import com.dhatvibs.service.SlotService; import
 * io.swagger.v3.oas.annotations.tags.Tag; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import java.time.LocalDate; import java.util.List;
 * 
 * @RestController
 * 
 * @RequestMapping("/slots")
 * 
 * @Tag(name = "Slot Booking APIs") public class SlotController {
 * 
 * private final SlotService slotService;
 * 
 * public SlotController(SlotService slotService) { this.slotService =
 * slotService; }
 * 
 * @GetMapping("/week") public List<DailySlot> week(
 * 
 * @RequestParam int week,
 * 
 * @RequestParam int year,
 * 
 * @RequestParam String city,
 * 
 * @RequestParam String zone) { return slotService.getWeekSlots(week, year,
 * city, zone); }
 * 
 * @GetMapping("/day") public List<DailySlot> day(@RequestParam String date) {
 * return slotService.getDaySlots(LocalDate.parse(date)); }
 * 
 * @PostMapping("/{id}/book") public SlotBooking book(
 * 
 * @PathVariable Long id,
 * 
 * @RequestParam Long riderId) { return slotService.bookSlot(id, riderId); }
 * 
 * @PostMapping("/{id}/cancel") public void cancel(
 * 
 * @PathVariable Long id,
 * 
 * @RequestParam String reason) { slotService.cancelSlot(id, reason); }
 * 
 * @GetMapping("/current") public SlotBooking current(@RequestParam Long
 * riderId) { return slotService.getCurrentSlot(riderId); }
 * 
 * @GetMapping("/history") public List<SlotBooking> history(@RequestParam Long
 * riderId) { return slotService.getHistory(riderId); }
 * 
 * @GetMapping("/status") public SlotBooking slotStatus(@RequestParam Long
 * riderId) { return slotService.getSlotStatus(riderId); } }
 */  

package com.dhatvibs.controller;

import com.dhatvibs.entity.*;
import com.dhatvibs.service.SlotService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    // 🔓 Public (no rider context needed)
    @GetMapping("/week")
    public List<DailySlot> week(
            @RequestParam int week,
            @RequestParam int year,
            @RequestParam String city,
            @RequestParam String zone) {

        return slotService.getWeekSlots(week, year, city, zone);
    }

    // 🔓 Public
    @GetMapping("/day")
    public List<DailySlot> day(@RequestParam String date) {
        return slotService.getDaySlots(LocalDate.parse(date));
    }

    // 🔐 Book slot (JWT)
    @PostMapping("/{id}/book")
    public SlotBooking book(@PathVariable Long id) {

        Long riderId = getRiderIdFromToken();

        return slotService.bookSlot(id, riderId);
    }

    // 🔐 Cancel slot (slot belongs to rider internally)
    @PostMapping("/{id}/cancel")
    public void cancel(
            @PathVariable Long id,
            @RequestParam String reason) {

        slotService.cancelSlot(id, reason);
    }

    // 🔐 Current slot
    @GetMapping("/current")
    public SlotBooking current() {

        Long riderId = getRiderIdFromToken();

        return slotService.getCurrentSlot(riderId);
    }

    // 🔐 Slot history
    @GetMapping("/history")
    public List<SlotBooking> history() {

        Long riderId = getRiderIdFromToken();

        return slotService.getHistory(riderId);
    }

    // 🔐 Slot status
    @GetMapping("/status")
    public SlotBooking slotStatus() {

        Long riderId = getRiderIdFromToken();

        return slotService.getSlotStatus(riderId);
    }

    // 🔁 Helper (clean + reusable)
    private Long getRiderIdFromToken() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        return (Long) auth.getPrincipal();
    }
}

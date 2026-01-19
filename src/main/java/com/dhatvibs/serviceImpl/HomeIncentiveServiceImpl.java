package com.dhatvibs.serviceImpl;

import com.dhatvibs.entity.*;
import com.dhatvibs.repository.*;
import com.dhatvibs.service.HomeIncentiveService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class HomeIncentiveServiceImpl implements HomeIncentiveService {

    private final IncentiveRepository incentiveRepo;
    private final RiderIncentiveProgressRepository progressRepo;

    public HomeIncentiveServiceImpl(
            IncentiveRepository incentiveRepo,
            RiderIncentiveProgressRepository progressRepo) {
        this.incentiveRepo = incentiveRepo;
        this.progressRepo = progressRepo;
    }

    @Override
    public Map<String, Object> getPeakHourIncentives() {

        List<Incentive> incentives =
                incentiveRepo.findByIncentiveTypeAndStatus(
                        IncentiveType.PEAK_HOUR,
                        IncentiveStatus.ACTIVE
                );

        List<Map<String, Object>> list = new ArrayList<>();

        for (Incentive i : incentives) {
            list.add(Map.of(
                    "title", i.getTitle(),
                    "description", i.getDescription(),
                    "incentiveType", i.getIncentiveType(),
                    "rewardType", i.getRewardType(),
                    "rewardValue", i.getRewardValue(),
                    "maxRewardPerRider", i.getMaxRewardPerRider(),
                    "condition", Map.of(
                            "minOrders", i.getMinOrders(),
                            "startTime", i.getStartTime(),
                            "endTime", i.getEndTime()
                    ),
                    "status", i.getStatus()
            ));
        }

        return Map.of(
                "success", true,
                "count", list.size(),
                "incentives", list
        );
    }

    @Override
    public Map<String, Object> getWeeklyEarning(Long riderId) {

        LocalDate weekStart =
                LocalDate.now().with(java.time.DayOfWeek.MONDAY);
        LocalDate weekEnd = weekStart.plusDays(6);

        Incentive weekly =
                incentiveRepo.findByIncentiveTypeAndStatus(
                        IncentiveType.WEEKLY,
                        IncentiveStatus.ACTIVE
                ).get(0);

        RiderIncentiveProgress p =
                progressRepo.findByRiderIdAndIncentiveIdAndWeekStart(
                        riderId,
                        weekly.getId(),
                        weekStart
                ).orElse(new RiderIncentiveProgress());

        return Map.of(
                "success", true,
                "data", Map.of(
                        "weekStart", weekStart,
                        "weekEnd", weekEnd,
                        "title", weekly.getTitle(),
                        "completedOrders", p.getCompletedOrders(),
                        "achieved", p.getAchieved(),
                        "earnedAmount", p.getEarnedAmount(),
                        "maxRewardPerRider", weekly.getMaxRewardPerRider()
                )
        );
    }

    @Override
    public Map<String, Object> getDailyEarning(Long riderId) {

        LocalDate today = LocalDate.now();

        Incentive daily =
                incentiveRepo.findByIncentiveTypeAndStatus(
                        IncentiveType.DAILY,
                        IncentiveStatus.ACTIVE
                ).get(0);

        RiderIncentiveProgress p =
                progressRepo.findByRiderIdAndIncentiveIdAndDate(
                        riderId,
                        daily.getId(),
                        today
                ).orElse(new RiderIncentiveProgress());

        return Map.of(
                "success", true,
                "data", Map.of(
                        "date", today,
                        "title", daily.getTitle(),
                        "requiredOrders", daily.getMinOrders(),
                        "completedOrders", p.getCompletedOrders(),
                        "achieved", p.getAchieved(),
                        "earnedAmount", p.getEarnedAmount(),
                        "maxRewardPerRider", daily.getMaxRewardPerRider()
                )
        );
    }
}

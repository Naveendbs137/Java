package com.dhatvibs.service;

import java.util.Map;

public interface HomeIncentiveService {

    Map<String, Object> getPeakHourIncentives();

    Map<String, Object> getWeeklyEarning(Long riderId);

    Map<String, Object> getDailyEarning(Long riderId);
}

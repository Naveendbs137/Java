package com.dhatvibs.service;

import java.util.List;

public interface LocationService {

    List<String> getCities();

    List<String> getAreasByCity(String cityName);
}

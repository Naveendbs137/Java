package com.dhatvibs.serviceImpl;

import com.dhatvibs.entity.City;
import com.dhatvibs.entity.Area;
import com.dhatvibs.repository.CityRepository;
import com.dhatvibs.repository.AreaRepository;
import com.dhatvibs.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    private final CityRepository cityRepo;
    private final AreaRepository areaRepo;

    public LocationServiceImpl(CityRepository cityRepo, AreaRepository areaRepo) {
        this.cityRepo = cityRepo;
        this.areaRepo = areaRepo;
    }

    @Override
    public List<String> getCities() {
        return cityRepo.findByIsActiveTrueOrderByNameAsc()
                .stream()
                .map(City::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAreasByCity(String cityName) {

        City city = cityRepo.findByIsActiveTrueOrderByNameAsc()
                .stream()
                .filter(c -> c.getName().equalsIgnoreCase(cityName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("City not found"));

        return areaRepo.findByCityIdAndIsActiveTrueOrderByNameAsc(city.getId())
                .stream()
                .map(Area::getName)
                .collect(Collectors.toList());
    }
}

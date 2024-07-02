package com.easy.tour.service;

import com.easy.tour.dto.DepartureDateDTO;
import com.easy.tour.dto.TourDTO;
import com.easy.tour.entity.departure.DepartureDate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DepartureDateService {
    DepartureDateDTO createDepartureDate(DepartureDateDTO departureDateDTO);

    List<DepartureDateDTO> getAllDepartureDate();
    List<DepartureDateDTO> getAllDepartureDateTour();
    DepartureDateDTO findById(int id);

}

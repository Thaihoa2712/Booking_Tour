package com.easy.tour.service.Impl;

import com.easy.tour.dto.DepartureDateDTO;
import com.easy.tour.dto.PriceDTO;
import com.easy.tour.dto.TourDTO;
import com.easy.tour.entity.Price.PriceDetail;
import com.easy.tour.entity.Tour.Tour;
import com.easy.tour.entity.departure.DepartureDate;
import com.easy.tour.mapper.DepartureDateMapper;
import com.easy.tour.repository.DepartureDateRepository;
import com.easy.tour.repository.TourRepository;
import com.easy.tour.service.DepartureDateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DepartureDateServiceImpl extends AbstractBaseServiceImpl<DepartureDateDTO> implements DepartureDateService {
    private static final Logger log = LoggerFactory.getLogger(DepartureDateServiceImpl.class);
    @Autowired
    DepartureDateMapper departureDateMapper;

    @Autowired
    DepartureDateRepository departureDateRepository;

    @Autowired
    TourRepository tourRepository;

    public DepartureDateServiceImpl() {
        super.setMapper(new DepartureDateMapper());
    }

    @Override
    public void setRepository() {
        AbstractBaseServiceImpl.setRepository(departureDateRepository);
    }

    @Override
    public DepartureDateDTO createDepartureDate(DepartureDateDTO departureDateDTO) {
        DepartureDate departureDate = departureDateMapper.convertDTOToEntity(departureDateDTO);

        Tour tour = tourRepository.findByTourCode(departureDateDTO.getTourCode());

        departureDate.setTour(tour);

        return departureDateMapper.convertEntityToDTO(departureDateRepository.save(departureDate));
    }

    @Override
    public List<DepartureDateDTO> getAllDepartureDate() {
        try {
            List<DepartureDate> departureDateList = departureDateRepository.findAll();
            List<DepartureDateDTO> departureDateDTOList = new ArrayList<>();
            for (DepartureDate departureDate : departureDateList) {
                DepartureDateDTO departureDateDTO = departureDateMapper.convertEntityToDTO(departureDate);
                departureDateDTO.setTourCode(departureDate.getTour().getTourCode());
                departureDateDTOList.add(departureDateDTO);
            }
            return departureDateDTOList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
    //Get All Departure FE
    @Override
    public List<DepartureDateDTO> getAllDepartureDateTour() {
        try {
            List<DepartureDate> departureDateList = departureDateRepository.findAll();
            List<DepartureDateDTO> departureDateDTOList = new ArrayList<>();
            for (DepartureDate departureDate : departureDateList) {
                DepartureDateDTO departureDateDTO = departureDateMapper.convertEntityToDTO(departureDate);
                departureDateDTO.setTourCode(departureDate.getTour().getTourCode());
                departureDateDTO.setTourName(departureDate.getTour().getTourName());
                departureDateDTO.setAdult(departureDate.getTour().getPrice().getPriceDetail().getAdult());
                departureDateDTO.setDescription(departureDate.getTour().getDescription());
                departureDateDTO.setMaximumSize(departureDate.getTour().getMaximumSize());
                departureDateDTO.setTourImg1(departureDate.getTour().getTourImg1());
                departureDateDTOList.add(departureDateDTO);
            }
            return departureDateDTOList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public DepartureDateDTO findById(int id) {
        DepartureDate departureDate = departureDateRepository.findById(id);

        if (departureDate != null) {

            DepartureDateDTO departureDateDTO = departureDateMapper.convertEntityToDTO(departureDate);
            departureDateDTO.setTourCode(departureDate.getTour().getTourCode());
            departureDateDTO.setDescription(departureDate.getTour().getDescription());
            departureDateDTO.setMaximumSize(departureDate.getTour().getMaximumSize());
            departureDateDTO.setTourImg1(departureDate.getTour().getTourImg1());
            departureDateDTO.setTourImg2(departureDate.getTour().getTourImg2());
            departureDateDTO.setDepartureDate(departureDate.getDepartureDate());
            departureDateDTO.setAdult(departureDate.getTour().getPrice().getPriceDetail().getAdult());
            departureDateDTO.setChildren(departureDate.getTour().getPrice().getPriceDetail().getChildren());
            departureDateDTO.setTourName(departureDate.getTour().getTourName());

            log.info("departureDate: "+departureDateDTO);
            return departureDateDTO;
        }
        return null;
    }





}

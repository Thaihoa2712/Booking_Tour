package com.easy.tour.repository;

import com.easy.tour.entity.Price.Price;
import com.easy.tour.entity.departure.DepartureDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartureDateRepository extends JpaRepository<DepartureDate, Integer> {
        DepartureDate findById(int id);
//    @Query("SELECT dd, t, pd FROM DepartureDate dd " +
//            "JOIN dd.tour t " +
//            "JOIN t.price p " +
//            "JOIN p.priceDetail pd " +
//            "WHERE dd.departureDate = :departureDate")
//    List<Object[]> findByDepartureDate(@Param("departureDate") LocalDate departureDate);
}

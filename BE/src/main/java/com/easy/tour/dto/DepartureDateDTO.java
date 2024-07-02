package com.easy.tour.dto;

import com.easy.tour.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
@ToString
public class DepartureDateDTO extends BaseEntity {
    int id;
    String tourCode;
    LocalDate departureDate;
    String tourName;
    String description;
    Integer maximumSize;
    String tourImg1;
    String tourImg2;
    BigDecimal adult;
    BigDecimal children;

}

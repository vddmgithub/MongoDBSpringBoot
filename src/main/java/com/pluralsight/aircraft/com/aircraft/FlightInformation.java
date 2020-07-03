package com.pluralsight.aircraft.com.aircraft;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document("flights")
public class FlightInformation {
    @Id
    private String id;

    @Field("departure")
    @Indexed
    private String departureCity;

    @Field("destination")
    @Indexed
    private String destinationCity;

    private FlightType type;
    private boolean  isDelayed;
    private int durationMin;
    private LocalDate departureDate;
    private Aircraft aircraft;

    @Transient
    private LocalDate createdAt;
}

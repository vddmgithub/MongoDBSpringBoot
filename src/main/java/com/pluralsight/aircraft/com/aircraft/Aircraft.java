package com.pluralsight.aircraft.com.aircraft;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Aircraft {
    private String model;
    private int nSeats;
}

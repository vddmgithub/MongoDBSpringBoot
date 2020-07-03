package com.pluralsight.aircraft.com.aircraft;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Aircraft {
    private String model;
    private int nSeats;
}

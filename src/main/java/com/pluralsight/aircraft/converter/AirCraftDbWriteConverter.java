package com.pluralsight.aircraft.converter;

import com.pluralsight.aircraft.com.aircraft.Aircraft;
import org.springframework.core.convert.converter.Converter;

import java.util.Objects;

public class AirCraftDbWriteConverter implements Converter<Aircraft, String> {

    @Override
    public String convert(Aircraft aircraft) {
        if (!Objects.nonNull(aircraft)) return null;
        else {
            return aircraft.getModel().concat("/")+aircraft.getNSeats();
        }
    }
}

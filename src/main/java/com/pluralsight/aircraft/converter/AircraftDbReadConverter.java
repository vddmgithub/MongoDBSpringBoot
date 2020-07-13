package com.pluralsight.aircraft.converter;

import com.pluralsight.aircraft.com.aircraft.Aircraft;
import org.springframework.core.convert.converter.Converter;

public class AircraftDbReadConverter implements Converter<String, Aircraft> {
    @Override
    public Aircraft convert(String s) {
        if(s == null)
            return null;

        String[] tokens = s.split("/");
        return new Aircraft(tokens[0], Integer.parseInt(tokens[1]));
    }
}

package com.pluralsight.aircraft;

import com.pluralsight.aircraft.converter.AirCraftDbWriteConverter;
import com.pluralsight.aircraft.converter.AircraftDbReadConverter;
import org.bson.json.Converter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AircraftdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AircraftdemoApplication.class, args);
	}

	@Bean
	public MongoCustomConversions customConversions(){
		List converters = new ArrayList();
		converters.add(new AirCraftDbWriteConverter());
		converters.add(new AircraftDbReadConverter());
		return new MongoCustomConversions(converters);
	}
}

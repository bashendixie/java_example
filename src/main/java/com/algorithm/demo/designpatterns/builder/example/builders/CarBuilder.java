package com.algorithm.demo.designpatterns.builder.example.builders;

import com.algorithm.demo.designpatterns.builder.example.cars.Car;
import com.algorithm.demo.designpatterns.builder.example.cars.Type;
import com.algorithm.demo.designpatterns.builder.example.components.Engine;
import com.algorithm.demo.designpatterns.builder.example.components.GPSNavigator;
import com.algorithm.demo.designpatterns.builder.example.components.Transmission;
import com.algorithm.demo.designpatterns.builder.example.components.TripComputer;

/**
 * EN: Concrete builders implement steps defined in the common interface.
 *
 * RU: Конкретные строители реализуют шаги, объявленные в общем интерфейсе.
 */
public class CarBuilder implements Builder {
    private Type type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
    }

    @Override
    public void setGPSNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
    }

    public Car getResult() {
        return new Car(type, seats, engine, transmission, tripComputer, gpsNavigator);
    }
}

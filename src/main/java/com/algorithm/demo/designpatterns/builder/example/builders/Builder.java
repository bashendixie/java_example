package com.algorithm.demo.designpatterns.builder.example.builders;

import com.algorithm.demo.designpatterns.builder.example.cars.Type;
import com.algorithm.demo.designpatterns.builder.example.components.Engine;
import com.algorithm.demo.designpatterns.builder.example.components.GPSNavigator;
import com.algorithm.demo.designpatterns.builder.example.components.Transmission;
import com.algorithm.demo.designpatterns.builder.example.components.TripComputer;

/**
 * EN: Builder interface defines all possible ways to configure a product.
 *
 * RU: Интерфейс Строителя объявляет все возможные этапы и шаги конфигурации
 * продукта.
 */
public interface Builder {
    void setType(Type type);
    void setSeats(int seats);
    void setEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);
}

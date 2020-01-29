package pl.akademiaspring.ksb2pracadomowa3.service;

import org.springframework.stereotype.Service;
import pl.akademiaspring.ksb2pracadomowa3.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CarService {

    private List<Car> carList = new ArrayList<>();

    CarService() {
        carList.add(new Car(1L,"Volvo", "S60", "Black"));
        carList.add(new Car(2L,"Peugeot", "406", "White"));
        carList.add(new Car(3L,"Audi", "Q7", "Blue"));
    }


    public List<Car> getCars() {
        return this.carList;
    }

    public Car getCarById(long id) {
        return carList
                .stream()
                .filter(x -> (x.getId() == id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Nie znaleziono samochodu o ID: " + id));
    }

    public List<Car> getCarsByColor(String color) {
        return carList
                .stream()
                .filter(car -> car.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public boolean addCar(Car car) {
        return carList.add(car);
    }

    public boolean modCar(Car car) {
        Car carFromList = carList
                .stream()
                .filter(x -> (x.getId() == car.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Nie znaleziono samochodu o ID: " + car.getId()));
        carList.remove(carFromList);
        return carList.add(car);
    }

    public boolean removeCar(long id) {
        Car carFromList = carList
                .stream()
                .filter(car -> (car.getId() == id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Nie znaleziono samochodu o ID: " + id));
        return carList.remove(carFromList);
    }


}

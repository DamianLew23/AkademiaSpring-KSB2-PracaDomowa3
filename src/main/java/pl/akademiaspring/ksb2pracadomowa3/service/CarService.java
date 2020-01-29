package pl.akademiaspring.ksb2pracadomowa3.service;

import org.springframework.stereotype.Service;
import pl.akademiaspring.ksb2pracadomowa3.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private List<Car> carList = new ArrayList<>();

    CarService() {
        carList.add(new Car(1L,"Volvo", "S60", "Black"));
        carList.add(new Car(2L,"Peugeot", "406", "White"));
        carList.add(new Car(3L,"Audi", "Q7", "Blue"));
        carList.forEach(System.out::println);
    }
}

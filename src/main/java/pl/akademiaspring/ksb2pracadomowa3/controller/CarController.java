package pl.akademiaspring.ksb2pracadomowa3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademiaspring.ksb2pracadomowa3.model.Car;
import pl.akademiaspring.ksb2pracadomowa3.service.CarService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    CarController(
            CarService carService
    ) {
        this.carService = carService;
    }


    @GetMapping
    public ResponseEntity<List<Car>> getCars(
            @RequestParam(required = false) String color) {
        if (color == null) {
            return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
        }
        return new ResponseEntity<>(carService.getCarsByColor(color), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Car car = carService.getCarById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        if (carService.addCar(car)){
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<Car> modCar(@RequestBody Car car) {
        if (carService.modCar(car)){
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> removeCar(@PathVariable long id) {
        if (carService.removeCar(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     }

     @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementExceptio(NoSuchElementException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
     }

}

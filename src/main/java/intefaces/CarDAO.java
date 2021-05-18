package intefaces;

import models.Car;

import java.util.List;

public interface CarDAO {

    public List<Car> findAll();
    public void save( Car car);
    public void delete(String name);
    public void update(String name);

}

package interfaces;

import models.City;

import java.util.List;

public interface iCity {
    public void save(City city);
    public City getCityByName(String name);
    public void delete(City city);
    public void update(City city);
    public List<City> getAllCities();
}

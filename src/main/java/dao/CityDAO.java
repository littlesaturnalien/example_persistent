package dao;

import interfaces.iCity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.City;
import java.util.List;

public class CityDAO implements iCity {
    @Override
    public void save(City city) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.persist(city);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public City getCityByName(String name) {
        EntityManager em = EntityManagerAdmin.getInstance();
        TypedQuery<City> query = em.createQuery("SELECT c FROM City c WHERE c.name = :name", City.class);
        query.setParameter("name", name);
        List<City> cities = query.getResultList();
        em.close();

        if (!cities.isEmpty()) {
            return cities.get(0);
        } else {
            return null;
        }
    }


    @Override
    public void delete(City city) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        City cityToDelete = em.find(City.class, city.getId());
        em.remove(cityToDelete);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(City city) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.merge(city);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<City> getAllCities() {
        EntityManager em = EntityManagerAdmin.getInstance();
        TypedQuery<City> query = em.createNamedQuery("City.findAll", City.class);
        List<City> cities = query.getResultList();
        em.close();
        return cities;
    }
}

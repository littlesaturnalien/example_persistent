package dao;

import interfaces.iCity;
import jakarta.persistence.EntityManager;
import models.City;

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
        return (City) em.createQuery("SELECT c FROM City c WHERE c.name = :name");
    }


    @Override
    public void delete(City city) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.remove(city);
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
}

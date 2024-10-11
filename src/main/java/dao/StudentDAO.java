package dao;

import interfaces.iStudent;
import jakarta.persistence.EntityManager;
import models.Student;

public class StudentDAO implements iStudent {
    @Override
    public void save(Student student) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Student getStudentByCif(String cif) {
        return null;
    }


    @Override
    public void delete(Student student) {

    }

    @Override
    public void update(Student student) {

    }
}

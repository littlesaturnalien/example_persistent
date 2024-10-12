package dao;

import interfaces.iStudent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.Student;

import java.util.List;

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
        EntityManager em = EntityManagerAdmin.getInstance();
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.cif = :cif", Student.class);
        query.setParameter("cif", cif);
        List<Student> students = query.getResultList();
        em.close();
        if (!students.isEmpty()) {
            return students.get(0);
        } else {
            return null;
        }
    }


    @Override
    public void delete(Student student) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        Student studentToDelete = em.find(Student.class, student.getId());
        em.remove(studentToDelete);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Student student) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Student> getAllStudents() {
        EntityManager em = EntityManagerAdmin.getInstance();
        TypedQuery<Student> query = em.createNamedQuery("Student.findAll", Student.class);
        List<Student> students = query.getResultList();
        em.close();
        return students;
    }
}

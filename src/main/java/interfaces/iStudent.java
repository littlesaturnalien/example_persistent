package interfaces;

import models.Student;

import java.util.List;

public interface iStudent {
    public void save(Student student);
    public Student getStudentByCif(String cif);
    public void delete(Student student);
    public void update(Student student);
    public List<Student> getAllStudents();
}

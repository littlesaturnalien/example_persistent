import dao.CityDAO;
import dao.StudentDAO;
import models.City;
import models.Student;

public class Main {
    public static void main(String[] args) {
        CityDAO cityDAO = new CityDAO();
        City managua = new City();
        managua.setName("Managua");
        cityDAO.save(managua);

        StudentDAO studentDAO = new StudentDAO();
        Student karen = new Student();
        karen.setCif("23010471");
        karen.setFirstName("Karen");
        karen.setLastName("Fonseca");
        karen.setEmail("kmryfonseca@uamv.edu.ni");
        karen.setCity();
    }
}

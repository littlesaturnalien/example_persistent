import dao.CityDAO;
import dao.StudentDAO;
import models.City;
import models.Student;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CityDAO cityDAO = new CityDAO();
        StudentDAO studentDAO = new StudentDAO();
        int opt = 0;
        String menu = "Gestión de Estudiantes y Ciudades\n"
                + "\n 1. Registrar\n" + " 2. Listar\n"
                + " 3. Actualizar\n"
                + " 4. Eliminar\n" + " 5. Salir\n" +
                " ---> ";

        while (opt != 5) {
            System.out.println(menu);
            opt = scanner.nextInt();

            switch (opt) {
                case 1:
                    String register = "\n¿Quieres registrar un estudiante o ciudad?\n"
                            + "1. Estudiante\n" + "2. Ciudad\n" + " ---> ";
                    System.out.println(register);
                    int xd = scanner.nextInt();

                    if (xd == 1) {
                        Student student = new Student();
                        System.out.println("Ingresa el cif del estudiante: ");
                        String cif = scanner.next();
                        student.setCif(cif);
                        System.out.println("Ingresa el nombre del estudiante: ");
                        String name = scanner.next();
                        student.setFirstName(name);
                        System.out.println("Ingresa el apellido del estudiante: ");
                        String surname = scanner.next();
                        student.setLastName(surname);
                        System.out.println("Ingresa el email del estudiante: ");
                        String email = scanner.next();
                        student.setEmail(email);
                        System.out.println("Ingresa el nombre de la ciudad del estudiante: ");
                        String cityName = scanner.next();
                        City city = cityDAO.getCityByName(cityName);

                        if (city != null) {
                            student.setCity(city);
                            studentDAO.save(student);
                            System.out.println("Estudiante registrado exitosamente.");
                        } else {
                            System.out.println("No se encontró la ciudad " + cityName + " en la base de datos.");
                        }

                    } else if (xd == 2) {
                        City city = new City();
                        System.out.println("Ingresa el nombre de la ciudad: ");
                        String newCity = scanner.next();
                        city.setName(newCity);
                        cityDAO.save(city);

                    } else {
                        System.out.println("Ingresa un número válido.");
                    }
                    break;

                case 2:
                    String consult = "\n¿Quieres ver la lista de todos los estudiantes o de las ciudades?\n"
                            + "1. Estudiantes\n" + "2. Ciudades\n" + " ---> ";
                    System.out.println(consult);
                    int xdd = scanner.nextInt();
                    if (xdd == 1) {
                        List<Student> students = studentDAO.getAllStudents();
                        System.out.println("\nLista de estudiantes\n");
                        if (students.isEmpty()) {
                            System.out.println("No hay estudiantes registrados.\n");
                        } else {
                            for (Student student : students) {
                                System.out.println(student.getFirstName() + " " + student.getLastName() + " (" +
                                       student.getCif() + ")\n" + student.getEmail() + "\nCiudad: " +
                                        (student.getCity() != null ? student.getCity().getName() : "Sin ciudad"));
                                System.out.println();
                            }
                        }
                    } else if (xdd == 2) {
                        List<City> cities = cityDAO.getAllCities();
                        System.out.println("\nLista de ciudades\n");
                        if (cities.isEmpty()) {
                            System.out.println("No hay ciudades registradas.\n");
                        } else {
                            for (City city : cities) {
                                System.out.println("Ciudad: " + city.getName());
                                System.out.println("Estado: " + (city.getState() ? "Activo" : "Inactivo"));
                                System.out.println();
                            }
                        }
                    } else {
                        System.out.println("Ingresa un número válido.\n");
                    }
                    break;

                case 3:
                    String update = "\n¿Quieres actualizar un estudiante o ciudad?\n"
                            + "1. Estudiante\n" + "2. Ciudad\n" + " ---> ";
                    System.out.println(update);
                    int a = scanner.nextInt();
                    if (a == 1) {
                        System.out.println("Ingresa el cif del estudiante: ");
                        String cif = scanner.next();
                        Student wantedStudent = studentDAO.getStudentByCif(cif);
                        if (wantedStudent != null) {
                            System.out.println("Estudiante: " + wantedStudent.getFirstName() + " " + wantedStudent.getLastName() + "\n");
                            System.out.println("Ingresa el nuevo nombre del estudiante: ");
                            String newName = scanner.next();
                            wantedStudent.setFirstName(newName);
                            System.out.println("Ingresa el nuevo apellido del estudiante: ");
                            String newSurname = scanner.next();
                            wantedStudent.setLastName(newSurname);
                            System.out.println("Ingresa el nuevo email del estudiante: ");
                            String newEmail = scanner.next();
                            wantedStudent.setEmail(newEmail);
                            System.out.println("Ingresa el nuevo nombre de la ciudad: ");
                            String newCity = scanner.next();
                            wantedStudent.setCity(cityDAO.getCityByName(newCity));
                            studentDAO.update(wantedStudent);
                        }
                        else {
                            System.out.println("No se encontró el estudiante.");
                        }

                    } else if (a == 2) {
                        System.out.println("Ingresa el nombre de la ciudad: ");
                        String nameCity = scanner.next();
                        City wantedCity = cityDAO.getCityByName(nameCity);
                        if (wantedCity != null) {
                            System.out.println(wantedCity.getName() + " actualmente está " + (wantedCity.getState() ? "activa" : "inactiva") + " en la base de datos.");
                            System.out.println("¿Deseas modificarlo? (S/N)");
                            String option = scanner.next();
                            if (option.equals("S") || option.equals("s")) {
                                wantedCity.setState(!wantedCity.getState());
                                cityDAO.update(wantedCity);
                            }
                        } else {
                            System.out.println("No se encontró la ciudad.");
                        }
                    } else {
                        System.out.println("Ingresa un número válido.");
                    }
                    System.out.println();
                    break;

                case 4:
                    String delete = "\n¿Quieres eliminar un estudiante o ciudad?\n"
                            + "1. Estudiante\n" + "2. Ciudad\n" + " ---> ";
                    System.out.println(delete);
                    int b = scanner.nextInt();
                    if (b == 1) {
                        System.out.println("Ingresa el cif del estudiante: ");
                        String cif = scanner.next();
                        Student wantedStudent = studentDAO.getStudentByCif(cif);
                        if (wantedStudent != null) {
                            System.out.println("Estudiante: " + wantedStudent.getFirstName() + " " + wantedStudent.getLastName() + "\n");
                            System.out.println("¿Estás seguro de eliminarlo? (S/N)");
                            String option = scanner.next();
                            if (option.equals("S") || option.equals("s")) {
                                studentDAO.delete(wantedStudent);
                            }
                        } else {
                            System.out.println("No se encontró el estudiante.");
                        }
                    } else if (b == 2) {
                        System.out.println("Ingresa el nombre de la ciudad: ");
                        String nameCity = scanner.next();
                        City wantedCity = cityDAO.getCityByName(nameCity);
                        if (wantedCity != null) {
                            System.out.println("¿Estás seguro de eliminar la ciudad " + wantedCity.getName() + "? (S/N)");
                            String option = scanner.next();
                            if (option.equals("S") || option.equals("s")) {
                                cityDAO.delete(wantedCity);
                            }
                        } else {
                            System.out.println("No se encontró la ciudad.");
                        }
                    } else {
                        System.out.println("Ingresa un número válido.");
                    }
                    System.out.println();
                    break;

                case 5:
                    System.out.println("\nSaliendo del programa...");
                    break;
                default:
                    System.out.println("\nIngrese un número válido.\n");
                    break;
            }
        }
    }
}

package Main;

import Entity.Department;
import Entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Main_second {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        Department department = new Department();
        department.setName("Finance");

        Employee employee1 = new Employee();
        employee1.setName("Ana");
        employee1.setDepartment(department);
        Employee employee2 = new Employee();
        employee2.setName("Radu");
        employee2.setDepartment(department);
        Employee employee3 = new Employee();
        employee3.setName("Maria");
        employee3.setDepartment(department);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        department.setEmployees(employees);

        entityTransaction.begin();

        entityManager.persist(department);
        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);

        entityTransaction.commit();
        entityManager.close();
    }
}

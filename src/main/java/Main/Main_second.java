package Main;

import Entity.Department;
import Entity.Employee;
import Entity.JobCategory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main_second {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try{
            //Department department = new Department();
            //department.setDepartmentName("IT");

            Department department = entityManager.find(Department.class, 1);

            //JobCategory jobCategory = new JobCategory();
            //jobCategory.setJobCategoryName("Software Developer");

            JobCategory jobCategory = entityManager.find(JobCategory.class, 1);

            Employee employee1 = new Employee();
            employee1.setFirstName("Iacob");
            employee1.setLastName("Dan");
            employee1.setIsManager(1);
            employee1.setStartDate(LocalDateTime.now());
            employee1.setEndDate(LocalDateTime.of(2020,12,15,13,25));
            employee1.setActive(1);
            employee1.setAddress("Republicii 46");
            employee1.setCp("1973647364736");
            employee1.setTelephone("078654782");
            employee1.setEmail("iacobdanstefan1992@yahoo.com");
            employee1.setBirthday(LocalDateTime.of(1992,9,15,23,30));
            employee1.setNoChildren(0);
            employee1.setSalary(3000.00);
            employee1.setStudies("Bachelor Degree");
            employee1.setSocialSecurityNumber("3874837492374");
            employee1.setHasDrivingLicence(1);
            employee1.setDepartment(department);
            employee1.setJobCategory(jobCategory);

            entityTransaction.begin();
            entityManager.persist(department);
            entityManager.persist(jobCategory);
            entityManager.persist(employee1);

            if(employee1.getFirstName().equals("Iacob") && employee1.getLastName().equals("Dan")){
                entityTransaction.rollback();
                entityManager.close();
                System.out.println("This user is in our database.");
                System.exit(0);

            }else if(employee1.getFirstName().isEmpty() && employee1.getLastName().isEmpty() && employee1.getAddress().isEmpty() &&
                     employee1.getCp().isEmpty() && employee1.getTelephone().isEmpty() && employee1.getEmail().isEmpty()){
                entityTransaction.rollback();
                entityManager.close();
                System.out.println("One of the fields is empty.");
                System.exit(0);

            }else{
                entityTransaction.commit();
                entityManager.close();
                System.out.println("A new employee was added.");
            }
        }catch (Exception e){
            entityTransaction.rollback();
            entityManager.close();
            System.out.println("Exception error : " + e);
            System.exit(0);
        }finally {
            System.out.println("No errors");
        }
    }
}

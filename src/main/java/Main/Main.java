package Main;

import Entity.Department;
import Entity.Employee;
import Entity.JobCategory;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    private static final EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static final EntityTransaction entityTransaction = entityManager.getTransaction();
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void createEmployee(){
        try{
            Department department = entityManager.find(Department.class, 1);
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

            Department department2 = new Department();
            department.setDepartmentName("IT");
            JobCategory jobCategory2 = new JobCategory();
            jobCategory.setJobCategoryName("Software Developer");

            Query query = entityManager.createQuery(
                    "SELECT employee2 FROM Employee employee2 WHERE employee2.firstName = :firstname").setParameter("firstname",employee1.getFirstName());
            List<Employee> employee2 = (List<Employee>)query.getResultList();

            if(!employee2.isEmpty()){
                logger.log(Level.WARNING,"This user is in our database.");
                System.exit(0);
            }else if(employee1.getFirstName().isEmpty() && employee1.getLastName().isEmpty() && employee1.getAddress().isEmpty() &&
                    employee1.getCp().isEmpty() && employee1.getTelephone().isEmpty() && employee1.getEmail().isEmpty()){
                logger.log(Level.WARNING,"One of the fields is empty.");
                System.exit(0);
            }else{
                entityTransaction.begin();
                entityManager.persist(department);
                entityManager.persist(jobCategory);
                entityManager.persist(employee1);
                try{
                    logger.log(Level.INFO,"A new employee was added.");
                    entityTransaction.commit();
                    entityManager.close();
                }catch (Exception e){
                    logger.log(Level.SEVERE,"It could not be created a new employee");
                    entityTransaction.rollback();
                    entityManager.close();
                }
            }
        }catch (Exception e){
            logger.log(Level.SEVERE,"Exception error : " + e);
            System.exit(0);
        }finally {
            System.out.println("No errors");
        }
    }

    public static void updateEmployee(){
        try{
            Department department = entityManager.find(Department.class, 1);
            JobCategory jobCategory = entityManager.find(JobCategory.class, 1);
            Employee employee1 = entityManager.find(Employee.class,5);

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

            Query query = entityManager.createQuery(
                    "SELECT employee2 FROM Employee employee2 WHERE employee2.id = :id").setParameter("id",employee1.getId());
            List<Employee> employee2 = (List<Employee>)query.getResultList();

            if(employee2.isEmpty()){
                logger.log(Level.WARNING,"This user is not in our database.");
                System.exit(0);
            }else{
                entityTransaction.begin();
                try{
                    logger.log(Level.INFO,"An employee was updated.");
                    entityTransaction.commit();
                    entityManager.close();
                }catch (Exception e){
                    logger.log(Level.SEVERE,"It could not be updated this employee");
                    entityTransaction.rollback();
                    entityManager.close();
                }
            }
        }catch (Exception e){
            logger.log(Level.SEVERE,"It could not be find this employee");
            System.exit(0);
        }finally {
            System.out.println("No errors");
        }
    }

    public static void deleteEmployee(){
        try{
            Employee employee1 = entityManager.find(Employee.class,9);
            entityManager.remove(employee1);

            Query query = entityManager.createQuery(
                    "SELECT employee2 FROM Employee employee2 WHERE employee2.id = :id").setParameter("id",employee1.getId());
            List<Employee> employee2 = (List<Employee>)query.getResultList();

            if(employee2.isEmpty()){
                logger.log(Level.WARNING,"This user is not in our database.");
                System.exit(0);
            }else{
                entityTransaction.begin();
                try{
                    logger.log(Level.INFO,"An employee was deleted.");
                    entityTransaction.commit();
                    entityManager.close();
                }catch (Exception e){
                    logger.log(Level.SEVERE,"It could not be deleted this employee");
                    entityTransaction.rollback();
                    entityManager.close();
                }
            }
        }catch (Exception e){
            logger.log(Level.SEVERE,"It could not be find this employee");
            System.exit(0);
        }finally {
            System.out.println("No errors");
        }
    }

    public static void selectEmployee(){
        try{
            Query query = entityManager.createQuery(
                    "SELECT employee2 FROM Employee employee2" + " WHERE employee2.id = :id")
                    .setParameter("id",1);
            List<Employee> employee2 = (List<Employee>)query.getResultList();
            if(employee2.isEmpty()){
                logger.log(Level.WARNING,"This id is not in our database.");
                System.exit(0);
            }else{
                Department department = entityManager.find(Department.class, 1);
                try{
                    Query query2 = entityManager.createQuery(
                            "SELECT employee2 FROM Employee employee2" +
                                    " WHERE employee2.id = :id and employee2.department = :department")
                            .setParameter("id",3).setParameter("department",department);
                    List<Employee> employee3 = (List<Employee>)query2.getResultList();
                    for(Employee e : employee3){
                        System.out.println(e.toString());
                    }
                    logger.log(Level.INFO,"An employee was selected.");
                }catch (Exception e){
                    logger.log(Level.SEVERE,"It could not be selected this employee");
                }
            }
        }catch (Exception e){
            logger.log(Level.SEVERE,"It could not be find this employee");
            System.exit(0);
        }finally {
            System.out.println("No errors");
        }
    }

    public static void selectAllEmployees(){
        try{
            Query query = entityManager.createQuery(
                    "SELECT employee2 FROM Employee employee2" + " WHERE employee2.id = :id")
                    .setParameter("id",1);
            List<Employee> employee2 = (List<Employee>)query.getResultList();
            if(employee2.isEmpty()){
                logger.log(Level.WARNING,"This database is empty.");
                System.exit(0);
            }else{
                try{
                    Query query2 = entityManager.createQuery(
                            "SELECT employee3 FROM Employee employee3" + " ORDER BY employee3.firstName ASC, employee3.salary ASC");
                    List<Employee> employee3 = (List<Employee>)query2.getResultList();
                    for(Employee e : employee3){
                        System.out.println(e.toString());
                    }
                    logger.log(Level.INFO,"All employees were selected.");
                }catch (Exception e){
                    logger.log(Level.SEVERE,"It could not be selected any employee");
                }
            }
        }catch (Exception e){
            logger.log(Level.SEVERE,"It could not be find any employee");
            System.exit(0);
        }finally {
            System.out.println("No errors");
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean somethingWrong;

        do{
            somethingWrong = false;

            System.out.println("Employees Management");
            System.out.println("What operation do you want to do?");
            System.out.println("Create Employee - 1, Update Employee - 2, Delete Employee - 3, Select Employee - 4, Select All Employees - 5, Exit - 0");
            System.out.println("Your choice : ");

            switch (sc.nextInt()){
                case 1:
                    createEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    selectEmployee();
                    break;
                case 5:
                    selectAllEmployees();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choose from 1 to 5");
                    somethingWrong = true;
                    break;
            }
        }while (somethingWrong);
    }
}

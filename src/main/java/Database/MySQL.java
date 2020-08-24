package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

    public static Connection getConnection() throws Exception {
        //Connection to MySQL
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/employees_management";
        String username = "root";
        String password = "toor";
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    //Create employee, department and jobCategory tables
    private static final String employee, department, jobCategory;
    static {
        employee = "create table employee ( " +
                "id int unsigned not null auto_increment primary key," +
                "firstName varchar(100) not null," +
                "lastName varchar(100) not null, " +
                "job_category_id smallint unsigned not null ,"+
                "department_id smallint unsigned not null,"+
                "isManager int default 0," +
                "startDate date not null," +
                "endDate date default null," +
                "active int default 1," +
                "address text not null," +
                "cp tinytext not null," +
                "telephone varchar(100) not null," +
                "email varchar(100)," +
                "birthday date not null," +
                "noChildren int default 0," +
                "salary double(10,2) not null," +
                "studies text," +
                "socialSecurityNumber tinytext," +
                "has_driving_license int default 0" +
                ")";
        department = "create table department("+
                "department_id smallint unsigned not null auto_increment primary key,"+
                "department_name varchar(100) not null" +
                ")";
        jobCategory = "create table jobCategory("+
                "job_category_id smallint unsigned not null auto_increment primary key,"+
                "job_category_name varchar(100) not null" +
                ")";
    }

    //Main
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(employee);
            stmt.executeUpdate(department);
            stmt.executeUpdate(jobCategory);
            System.out.println("CreateEmployeeTableMySQL: main(): table created.");
            System.out.println();
        } catch (ClassNotFoundException e) {
            System.out.println("error: failed to load MySQL driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("error: failed to create a connection object.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("other error:");
            e.printStackTrace();
        } finally {
            try {
                assert stmt != null;
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

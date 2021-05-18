import intefaces.UserDAO;
import models.User;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestFactory {

    private static TestFactory instance;
    private UserDAOImpl userDAO;
    private Connection connection;


    private TestFactory(){
        connectDatabase();
        this.userDAO = new UserDAOImpl(this.connection);
    }

    public static TestFactory getInstance(){
        if(instance == null){instance = new TestFactory(); }
        return instance;
    }

    public void connectDatabase() {
        try {
            // We register the MySQL (MariaDB) driver
            // Registramos el driver de MySQL (MariaDB)
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de MySQL: " + ex);
            }

            // Database connect
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test",
                    "root", "mysql");
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
    }


    public Object findById(String id,String obj){
        switch (obj){
            case "user":
                return userDAO.findById(id);
            case "car":
                return null;
        }
        return null;
    }


    public List<Object> findAll (String param ){
        switch (param){
            case "user":
                List<User> users = userDAO.findALL();
                List<Object> objects = new ArrayList<>();
                objects.addAll(users);
                return objects;
            case "car":
                return null;

        }
        return null;

    }


    public void save(Object obj){
        switch (obj.getClass().toString()){
            case "class models.User":
                User user = (User)obj;
                userDAO.save(user);
                break;
        }
    }

    public void delete(Object obj){
        switch (obj.getClass().toString()){
            case "class models.User":
                User user = (User)obj;
                userDAO.delete(user.getName());
                break;
        }
    }

    public void update(Object obj, String id ){
        switch (obj.getClass().toString()){
            case "class models.User":
                User user = (User)obj;
                userDAO.update(id, user);
                break;
        }
    }


}

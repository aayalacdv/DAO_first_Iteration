import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Connection connection = null;

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


    public static void main(String[] args) throws SQLException {

        TestFactory factory = TestFactory.getInstance();
        Object user = factory.findById("Axel","user");
        System.out.println(user);
        List<Object> users2 = factory.findAll("user");
        System.out.println(users2);
        User u = new User("Manuel");
        u.setCarID("Mazda");

        factory.save(u);
        users2 = factory.findAll("user");
        System.out.println(users2);

        u.setCarID("Ferrari");
        User p = new User("Pedro");
        p.setCarID("Ferrari");
        factory.update(p,p.getName());
        users2 = factory.findAll("user");
        System.out.println(users2);

    }


}

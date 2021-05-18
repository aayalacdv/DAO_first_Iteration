import intefaces.UserDAO;
import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet rs;

    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStm() {
        return stm;
    }

    public void setStm(Statement stm) {
        this.stm = stm;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    @Override
    public User findById(String id) {
        User user = null;
        try{
            this.stm = conn.createStatement();
            String query = "SELECT * FROM _user WHERE _name='"+id+"'";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("_name");
                String car = rs.getString("car_id");
                user = new User (name);
                user.setCarID(car);
            }

        }catch(SQLException sql ){

        }
        finally {
            try{
                if (this.rs != null) {
                    this.rs.close();
                }
                if (this.stm!= null) {
                    this.stm.close();
                }
            } catch (SQLException sql){

            }
        }

        return user;

    }

    @Override
    public List<User> findALL() {
        try{
            this.stm = this.conn.createStatement();
            String query = "select * from _user";
            this.rs = stm.executeQuery(query);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("_name");
                String car = rs.getString("car_id");
                User u = new User (name);
                u.setCarID(car);
                users.add(u);
            }
            return users;

        }catch (SQLException sql ){}
        finally{
            try{
                if (this.rs != null) {
                    this.rs.close();
                }
                if (this.stm!= null) {
                    this.stm.close();
                }
            } catch (SQLException sql){
            }

        }
        return null;
    }

    @Override
    public void save(User user) {
        try{
            this.stm = conn.createStatement();
            System.out.println(user);
            String query = "INSERT INTO _user VALUES ('"+user.getName()+"','"+user.getCarID()+"')";
            stm.executeUpdate(query);
        }catch(SQLException sql ){
            System.out.println(sql.getMessage());
        }
        finally {
            try{
                if (this.rs != null) {
                    this.rs.close();
                }
                if (this.stm!= null) {
                    this.stm.close();
                }
            } catch (SQLException sql){

            }
        }
    }

    @Override
    public void delete(String name) {
        try{
        this.stm = conn.createStatement();
        String query = "DELETE FROM _user WHERE _name='"+name+"'";
        stm.executeUpdate(query);
    }catch(SQLException sql ){

    }
        finally {
            try{
                if (this.rs != null) {
                    this.rs.close();
                }
                if (this.stm!= null) {
                    this.stm.close();
                }
            } catch (SQLException sql){
            }
        }

    }

    @Override
    public void update(String id, User user) {
        String name = user.getName();
        String car = user.getCarID();
        try{
            this.stm = conn.createStatement();
            String query = "UPDATE _user SET _name='"+name+"' , car_id='"+car+"' WHERE _name='"+id+"'";
            stm.executeUpdate(query);
        }catch(SQLException sql ){

        }
        finally {
            try{
                if (this.rs != null) {
                    this.rs.close();
                }
                if (this.stm!= null) {
                    this.stm.close();
                }
            } catch (SQLException sql){
            }
        }

    }
}

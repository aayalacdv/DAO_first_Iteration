package models;

import java.util.Objects;

public class User {
    private String name;
    private String carID;

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public User(String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", carID='" + carID + '\'' +
                '}';
    }
}

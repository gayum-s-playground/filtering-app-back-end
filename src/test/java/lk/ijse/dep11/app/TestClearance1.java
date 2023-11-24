package lk.ijse.dep11.app;

import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestClearance1 {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dep11_filtering_app", "root", "961021")) {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO customer (first_name, last_name, contact, country) VALUES (?,?,?,?)");
            Faker faker = new Faker();
            for (int i = 0; i < 100; i++) {
                stm.setString(1,faker.name().firstName());
                stm.setString(2,faker.name().lastName());
                stm.setString(3,faker.regexify("0\\d{2}-\\d{7}"));
                stm.setString(4,faker.country().name());
                stm.executeUpdate();
            }
            System.out.println("Records Added!");
        }
    }
}

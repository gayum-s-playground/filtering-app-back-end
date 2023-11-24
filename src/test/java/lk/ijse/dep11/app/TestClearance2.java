package lk.ijse.dep11.app;

import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class TestClearance2 {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dep11_filtering_app", "root", "961021")) {
            Statement stm = connection.createStatement();
            int deletedRows = stm.executeUpdate("DELETE FROM customer WHERE contact NOT REGEXP '\\\\d{3}-\\\\d{7}'");
            System.out.println(deletedRows);
        }
    }
}

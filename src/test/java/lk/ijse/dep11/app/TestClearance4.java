package lk.ijse.dep11.app;

import java.sql.*;

public class TestClearance4 {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dep11_filtering_app", "root", "961021")) {
            Statement stm = connection.createStatement();
            int deleteRowCount = stm.executeUpdate("DELETE FROM customer WHERE id IN (SELECT id FROM (SELECT DISTINCT c1.id FROM customer c1 INNER JOIN customer c2 ON c1.id <> c2.id AND c1.first_name = c2.first_name AND c1.last_name = c2.last_name WHERE c1.id > c2.id) AS c)");
            System.out.println(deleteRowCount);
        }
    }
}

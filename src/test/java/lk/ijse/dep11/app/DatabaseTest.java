package lk.ijse.dep11.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseTest {

    private Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dep11_filtering_app","root","961021");
    }

    @AfterEach
    void tearDown() throws Exception {
        connection.close();
    }

    @Order(1)
    @Test
    void testDBExceeds1000Records() throws Exception {
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT COUNT(*) FROM customer");
        rst.next();
        int numberOfRows = rst.getInt(1);
        System.out.println(numberOfRows);
        assertTrue(numberOfRows >= 1000);
    }

    @Order(2)
    @Test
    void testValidContactNumbers() throws Exception {
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM customer WHERE contact NOT REGEXP '\\\\d{3}-\\\\d{7}'");
        assertFalse(rst.next());
    }

    @Order(3)
    @Test
    void testUniqueContactNumbers() throws Exception {
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT contact,COUNT(contact) AS count FROM customer GROUP BY contact HAVING count > 1");
        assertFalse(rst.next());
    }

    @Order(4)
    @Test
    void testUniqueCustomerNames() throws Exception {
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT CONCAT(first_name,'  ',last_name) AS name, COUNT(*) AS count FROM customer GROUP BY first_name,last_name HAVING count > 1");
        assertFalse(rst.next());
    }
}

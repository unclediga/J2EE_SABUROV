package ru.unclediga.saburov.city.dao;

import ru.unclediga.saburov.city.domain.PersonRequest;
import ru.unclediga.saburov.city.domain.PersonResponse;
import ru.unclediga.saburov.city.exception.PersonCheckException;

import java.sql.*;

public class PersonCheckDao {
    private ConnectionBuilder connectionBuilder;
    private static final String SQL_REQUEST =
            "select temporal \n" +
                    " from cr_address_person c\n" +
                    "         inner join cr_person cp on c.person_id = cp.person_id\n" +
                    "         inner join cr_address ca on c.address_id = ca.address_id\n" +
                    " where UPPER(cp.sur_name) = UPPER(?)\n" +
                    "  and UPPER(cp.given_name) = UPPER(?)\n" +
                    "  and UPPER(cp.patronymic) = UPPER(?)\n" +
                    "  and cp.date_of_birth = ?\n" +
                    "  and ca.street_code = ?\n" +
                    "  and ca.building = ?\n" +
                    "  and ca.extension = ?\n" +
                    "  and ca.apartment = ? \n";

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        final PersonResponse personResponse = new PersonResponse();
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL_REQUEST)) {
            String sql = SQL_REQUEST;
            if (request.getExtension() != null) {
                sql += "\n and upper(a.extension) = upper(?)  ";
            } else {
                sql += "\n and extension is null ";
            }
            if (request.getApartment() != null) {
                sql += "\n and upper(a.apartment) = upper(?) ";
            } else {
                sql += "\n and a.apartment is null ";
            }
            stmt.setString(1, request.getSurName());
            stmt.setString(2, request.getGivenName());
            stmt.setString(3, request.getPatronymic());
            stmt.setDate(4, java.sql.Date.valueOf(request.getDateOfBirth()));
            stmt.setInt(5, request.getStreetCode());
            stmt.setString(6, request.getBuilding());
            int next = 7;
            if (request.getExtension() != null) {
                stmt.setString(7, request.getExtension());
                next++;
            }
            if (request.getApartment() != null) {
                stmt.setString(next, request.getApartment());
            }
            final ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                personResponse.setRegistered(true);
                personResponse.setTemporal(resultSet.getBoolean("temporal"));
            }
        } catch (SQLException se) {
            throw new PersonCheckException(se);
        }
        return personResponse;
    }

    public PersonCheckDao() {
        // Calling from Servlet result this error _         _
        //                                         \ (*.*)/
        // java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/db_example
        try {
            // TOMCAT 9.0:
            // Loading class `com.mysql.jdbc.Driver'. This is deprecated.
            // The new driver class is `com.mysql.cj.jdbc.Driver'.
            // The driver is automatically registered via the SPI and
            // manual loading of the driver class is generally unnecessary.
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }
}

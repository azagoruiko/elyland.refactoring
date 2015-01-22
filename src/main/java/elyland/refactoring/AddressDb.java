package elyland.refactoring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AddressDb {
    
    Connection connection;

    public AddressDb(Connection connection) {
        this.connection = connection;
    }

    public void addPerson(Person person) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("insert into AddressEntry values (?, ?, ?)");
            statement.setLong(1, System.currentTimeMillis());
            statement.setString(2, person.getName());
            statement.setString(3, person.getPhoneNumber().getNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException err) {
                    throw new RuntimeException(err);
                }
            }
        }
    }

    /**
     * Looks up the given person, null if not found.
     */
    public Person findPerson(String name) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement("select * from AddressEntry where name = '" + name + "'");
            rs = stmt.executeQuery();
            if (rs.next()) {
                String foundName = rs.getString("name");
                PhoneNumber phoneNumber = new PhoneNumber(rs.getString("phoneNumber"));
                Person person = new Person(foundName, phoneNumber);
                return person;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public List<Person> getAll() {
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = connection.prepareStatement("select * from AddressEntry");

            result = statement.executeQuery();
            List<Person> entries = new LinkedList<Person>();
            while (result.next()) {
                String name = result.getString("name");
                PhoneNumber phoneNumber = new PhoneNumber(result.getString("phoneNumber"));
                Person person = new Person(name, phoneNumber);
                entries.add(person);
            }
            return entries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException err) {
                    throw new RuntimeException(err);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException err) {
                    throw new RuntimeException(err);
                }
            }
        }
    }

}

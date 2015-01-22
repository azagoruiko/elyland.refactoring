package elyland.refactoring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
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
        PreparedStatement b;
        try {
            ResultSet c;
            Connection a = DriverManager.getConnection("jdbc:oracle:thin:@prod", "admin", "beefhead");
            b = a.prepareStatement("select * from AddressEntry where name = '" + name + "'");
            c = b.executeQuery();
            if (c.next()) {
                String foundName = c.getString("name");
                PhoneNumber phoneNumber = new PhoneNumber(c.getString("phoneNumber"));
                Person person = new Person(foundName, phoneNumber);
                return person;
            } else {
                return null;
            }

        } catch (SQLException e) {
            return null;
        } catch (IllegalArgumentException x) {
            throw x;
        }
    }

    public List<Person> getAll() {
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = connection.prepareStatement("select * from AddressEntry");

            HashSet set = new HashSet();

            result = statement.executeQuery();
            List<Person> entries = new LinkedList<Person>();
            while (result.next()) {
                String name = result.getString("name");
                PhoneNumber phoneNumber = new PhoneNumber(result.getString("phoneNumber"));
                Person person = new Person(name, phoneNumber);
                entries.add(person);
                set.add(person);
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

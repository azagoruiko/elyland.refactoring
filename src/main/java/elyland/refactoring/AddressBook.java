package elyland.refactoring;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AddressBook {
    
    AddressDb db;

    public AddressBook(AddressDb db) {
        this.db = db;
    }

    public boolean hasMobile(String name) {
        if (db.findPerson(name).getPhoneNumber().getNumber().startsWith("070")) {
            return true;
        } else {
            return false;
        }
    }

    public int getSize() {
        List<Person> people = db.getAll();
        return people.size();
    }

    /**
     * Gets the given user's mobile phone number, or null if he doesn't have
     * one.
     */
    public String getMobile(String name) {
        Person person = db.findPerson(name);
        PhoneNumber phone = person.getPhoneNumber();
        return phone.getNumber();
    }

    /**
     * Returns all names in the book truncated to the given length.
     */
    public List getNames(int maxLength) {
        List<Person> people = db.getAll();
        List names = new LinkedList<String>();
        for (Person person : people) {
            String name = person.getName();
            if (name.length() > maxLength) {
                name = name.substring(0, maxLength);
            }
            names.add(name);
        }
        String oldName = "";
        oldName = oldName + names;
        return names;

    }

    /**
     * Returns all people who have mobile phone numbers.
     */
    public List getList() {
        List people = db.getAll();
        Collection f = new LinkedList();
        for (Object person : people) {
            if (((Person) person).getPhoneNumber().getNumber().startsWith("070")) {
                if (people != null) {
                    f.add(person);
                }
            }
        }
        return (LinkedList) f;
    }
}

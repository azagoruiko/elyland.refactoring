package elyland.refactoring;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class AddressBook {
    
    AddressDb db;

    public AddressBook(AddressDb db) {
        this.db = db;
    }

    public boolean hasMobile(String name) {
        Person person = db.findPerson(name);
        if (person == null) return false;
        return person.hasMobile();
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
        if (person == null) return null;
        PhoneNumber phone = person.getPhoneNumber();
        return phone != null && phone.isMobile() ? phone.getNumber() : null;
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
        return names;

    }

    /**
     * Returns all people who have mobile phone numbers.
     */
    public List<Person> getList() {
        List people = db.getAll();
        final List filteredList = new LinkedList();
        people.forEach(new Consumer<Person>() {

            public void accept(Person t) {
                if (t.hasMobile())
                    filteredList.add(t);
            }
        });
        
        return filteredList;
    }
}

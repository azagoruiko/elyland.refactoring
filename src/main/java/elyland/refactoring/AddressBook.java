package elyland.refactoring;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class AddressBook {
    
    AddressDb db;

    public AddressBook(AddressDb db) {
        this.db = db;
    }

    /**
     * Searches a person and checks if its number is mobile
     * @param name a name to search
     * @return true if person's number is mobile 
     */
    public boolean hasMobile(String name) {
        Person person = db.findPerson(name);
        if (person == null) return false;
        return person.hasMobile();
    }

    /**
     * Retrieves person count
     * @return person count
     */
    public int getSize() {
        List<Person> people = db.getAll();
        return people.size();
    }

    /**
     * Gets the given user's mobile phone number, or null if he doesn't have
     * one.
     * 
     * @param name person name to search
     * @return mobile phone number, or null if he doesn't have one.
     */
    public String getMobile(String name) {
        Person person = db.findPerson(name);
        if (person == null) return null;
        PhoneNumber phone = person.getPhoneNumber();
        return phone != null && phone.isMobile() ? phone.getNumber() : null;
    }

    /**
     * Returns all names in the book truncated to the given length.
     * 
     * @param maxLength Maximum length for each returned name
     * @return a list of strings that are names cut if was necessary
     */
    public List<String> getNames(int maxLength) {
        List<Person> people = db.getAll();
        List<String> names = new LinkedList<>();
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
     * 
     * @return List of people who have mobile phone numbers.
     */
    public List<Person> getMobileList() {
        List<Person> people = db.getAll();
        final List<Person> filteredList = new LinkedList<>();
        people.forEach(new Consumer<Person>() {

            public void accept(Person t) {
                if (t.hasMobile())
                    filteredList.add(t);
            }
        });
        
        return filteredList;
    }
    
    /**
     * For testing purpose (adding a new person)
     * @param name person name
     * @param phoneNumber person phone number
     * @return saved Person
     */
    public Person addPerson(String name, String phoneNumber) {
        Person p = new Person(name, new PhoneNumber(phoneNumber));
        db.addPerson(p);
        return p;
    }
}

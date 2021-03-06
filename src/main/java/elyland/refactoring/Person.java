package elyland.refactoring;

/**
 * Person entity class
 */
public class Person {
    private String name;
    private PhoneNumber phoneNumber;

    public Person(String name, PhoneNumber phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean hasMobile() {
        if (phoneNumber == null) return false;
        return phoneNumber.isMobile();
    }
}

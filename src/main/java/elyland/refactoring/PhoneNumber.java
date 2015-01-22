package elyland.refactoring;

public class PhoneNumber {
    public final static String MOBILE_CODE = "070";
    private final String number;

    public PhoneNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
    
    public boolean isMobile() {
        return number.startsWith(MOBILE_CODE);
    }

}

package elyland.refactoring;

public class App 
{
    public static void main( String[] args )
    {
        ConnectionFactory cf = new ConnectionFactoryImpl("jdbc:mysql://localhost:3306/elyland?zeroDateTimeBehavior=convertToNull", 
                "test", "test");
        AddressBook book = new AddressBook(new AddressDb(cf.getConnection()));
        /*book.addPerson("p1", "070111");
        book.addPerson("p2", "060111");
        book.addPerson("p3", "070222");*/
        for(Person p : book.getMobileList()) {
            System.out.println(p.getName());
            System.out.println(p.getPhoneNumber().getNumber());
        }
        
        for(String n : book.getNames(1)) {
            System.out.println(n);
        }
    }
}

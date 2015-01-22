package elyland.refactoring;

import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author andrii
 */
public class AddressBookTest extends TestCase {
    
    ConnectionFactory cf = null;
    AddressBook book = null;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        cf = new ConnectionFactoryImpl("jdbc:mysql://localhost:3306/elyland?zeroDateTimeBehavior=convertToNull", 
                "test", "test");
        book = new AddressBook(new AddressDb(cf.getConnection()));
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        cf.getConnection().close();
    }

    /**
     * Test of hasMobile method, of class AddressBook.
     */
    public void testHasMobile() {
        System.out.println("hasMobile");
        assertTrue(book.hasMobile("p1"));
        assertFalse(book.hasMobile("p2"));
    }

    /**
     * Test of getSize method, of class AddressBook.
     */
    public void testGetSize() {
        System.out.println("getSize");
        int result = book.getSize();
        assertEquals(3, result);
    }

    /**
     * Test of getMobile method, of class AddressBook.
     */
    public void testGetMobile() {
        System.out.println("getMobile");
        String result = book.getMobile("p1");
        assertEquals("070111", result);
        result = book.getMobile("p2");
        assertNull(result);
    }

    /**
     * Test of getNames method, of class AddressBook.
     */
    public void testGetNames() {
        System.out.println("getNames");
        List<String> result = book.getNames(1);
        for (String s : result) {
            assertEquals("p", s);
        }
        assertEquals(3, result.size());
    }

    /**
     * Test of getList method, of class AddressBook.
     */
    public void testGetMobileList() {
        List<Person> result = book.getMobileList();
        assertEquals(2, result.size());
    }
    
}

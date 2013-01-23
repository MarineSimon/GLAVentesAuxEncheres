/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Date;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author poste5
 */
public class PeriodicityTest {
    
    public PeriodicityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setId method, of class Periodicity.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Random r = new Random();
        Long id = r.nextLong();
        Periodicity instance = new Periodicity();
        instance.setId(id);
        long expResult = id;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTypePeriod method, of class Periodicity, with the first constructor.
     */
    @Test
    public void testGetTypePeriod1() {
        System.out.println("getTypePeriod");
        Random r = new Random();
        int typePeriod = r.nextInt(2);
        Periodicity instance = new Periodicity(typePeriod);
        int expResult = typePeriod;
        int result = instance.getTypePeriod();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTypePeriod method, of class Periodicity, with the second constructor.
     */
    @Test
    public void testGetTypePeriod2() {
        System.out.println("getTypePeriod");
        Random r = new Random();
        int typePeriod = r.nextInt(2);
        Periodicity instance = new Periodicity(typePeriod,0);
        int expResult = typePeriod;
        int result = instance.getTypePeriod();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTypePeriod method, of class Periodicity, with the third constructor.
     */
    @Test
    public void testGetTypePeriod3() {
        System.out.println("getTypePeriod");
        Random r = new Random();
        int typePeriod = r.nextInt(2);
        Periodicity instance = new Periodicity(typePeriod,null);
        int expResult = typePeriod;
        int result = instance.getTypePeriod();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTypePeriod method, of class Periodicity, with the fourth constructor.
     */
    @Test
    public void testGetTypePeriod4() {
        System.out.println("getTypePeriod");
        Random r = new Random();
        int typePeriod = r.nextInt(2);
        Periodicity instance = new Periodicity(typePeriod,0,null);
        int expResult = typePeriod;
        int result = instance.getTypePeriod();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTypePeriod method, of class Periodicity.
     */
    @Test
    public void testSetTypePeriod() {
        System.out.println("setTypePeriod");
        Random r = new Random();
        int typePeriod = r.nextInt(2);
        Periodicity instance = new Periodicity();
        instance.setTypePeriod(typePeriod);
        int expResult = typePeriod;
        int result = instance.getTypePeriod();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfRepetition method, of class Periodicity, with the first constructor.
     */
    @Test
    public void testGetNumberOfRepetition1() {
        System.out.println("getNumberOfRepetition");
        Random r = new Random();
        int numberOfRepetition = r.nextInt();
        Periodicity instance = new Periodicity(0,numberOfRepetition);
        int expResult = numberOfRepetition;
        int result = instance.getNumberOfRepetition();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfRepetition method, of class Periodicity, with the second constructor.
     */
    @Test
    public void testGetNumberOfRepetition2() {
        System.out.println("getNumberOfRepetition");
        Random r = new Random();
        int numberOfRepetition = r.nextInt();
        Periodicity instance = new Periodicity(0,numberOfRepetition,null);
        int expResult = numberOfRepetition;
        int result = instance.getNumberOfRepetition();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNumberOfRepetition method, of class Periodicity.
     */
    @Test
    public void testSetNumberOfRepetition() {
        System.out.println("setNumberOfRepetition");
        Random r = new Random();
        int numberOfRepetition = r.nextInt();
        Periodicity instance = new Periodicity();
        instance.setNumberOfRepetition(numberOfRepetition);
        int expResult = numberOfRepetition;
        int result = instance.getNumberOfRepetition();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLimitDate method, of class Periodicity, with the first constructor.
     */
    @Test
    public void testGetLimitDate1() {
        System.out.println("getLimitDate");
        Random r = new Random();
        Date limitDate = new Date(r.nextLong());
        Periodicity instance = new Periodicity(1,limitDate);
        Date expResult = limitDate;
        Date result = instance.getLimitDate();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getLimitDate method, of class Periodicity, with the second constructor.
     */
    @Test
    public void testGetLimitDate2() {
        System.out.println("getLimitDate");
        Random r = new Random();
        Date limitDate = new Date(r.nextLong());
        Periodicity instance = new Periodicity(1,0,limitDate);
        Date expResult = limitDate;
        Date result = instance.getLimitDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLimitDate method, of class Periodicity.
     */
    @Test
    public void testSetLimitDate() {
        System.out.println("setLimitDate");
        Random r = new Random();
        Date limitDate = new Date(r.nextLong());
        Periodicity instance = new Periodicity();
        instance.setLimitDate(limitDate);
        Date expResult = limitDate;
        Date result = instance.getLimitDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Periodicity.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Random r = new Random();
        Long id = r.nextLong();
        Periodicity instance = new Periodicity();
        instance.setId(id);
        int expResult = (id != null ? id.hashCode() : 0);
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Periodicity.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Periodicity instance = new Periodicity();
        String expResult = "persistence.Periodicity[ id="+instance.getId()+" ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}

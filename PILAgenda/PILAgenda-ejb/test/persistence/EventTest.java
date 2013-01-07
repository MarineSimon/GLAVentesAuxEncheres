/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Date;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author poste5
 */
public class EventTest {
    
    public EventTest() {
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
     * Test of getId method, of class Event.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Event instance = new Event();
        Long expResult = null;              //WARNING: problem with id generation
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Event.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Random r = new Random();
        Long id = r.nextLong();
        Event instance = new Event();
        instance.setId(id);
        long expResult = id;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Event.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String name = "Gang Bang in the forest";
        Event instance = new Event(name,null,null,null,0,null,null,null,null);
        String expResult = name;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Event.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Gang Bang in the forest";
        Event instance = new Event();
        instance.setName(name);
        String expResult = name;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlace method, of class Event.
     */
    @Test
    public void testGetPlace() {
        System.out.println("getPlace");
        String place = "The dark forest";
        Event instance = new Event(null,place,null,null,0,null,null,null,null);
        String expResult = place;
        String result = instance.getPlace();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPlace method, of class Event.
     */
    @Test
    public void testSetPlace() {
        System.out.println("setPlace");
        String place = "The dark forest";
        Event instance = new Event();
        instance.setPlace(place);
        String expResult = place;
        String result = instance.getPlace();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBeginDate method, of class Event.
     */
    @Test
    public void testGetBeginDate() {
        System.out.println("getBeginDate");
        Random r = new Random();
        Date beginDate = new Date(r.nextLong());
        Event instance = new Event(null,null,beginDate,null,0,null,null,null,null);
        Date expResult = beginDate;
        Date result = instance.getBeginDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBeginDate method, of class Event.
     */
    @Test
    public void testSetBeginDate() {
        System.out.println("setBeginDate");
        Random r = new Random();
        Date beginDate = new Date(r.nextLong());
        Event instance = new Event();
        instance.setBeginDate(beginDate);
        Date expResult = beginDate;
        Date result = instance.getBeginDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndDate method, of class Event.
     */
    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
        Random r = new Random();
        Date endDate = new Date(r.nextLong());
        Event instance = new Event(null,null,null,endDate,0,null,null,null,null);
        Date expResult = endDate;
        Date result = instance.getEndDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndDate method, of class Event.
     */
    @Test
    public void testSetEndDate() {
        System.out.println("setEndDate");
        Random r = new Random();
        Date endDate = new Date(r.nextLong());
        Event instance = new Event();
        instance.setEndDate(endDate);
        Date expResult = endDate;
        Date result = instance.getEndDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVisibility method, of class Event.
     */
    @Test
    public void testGetVisibility() {
        System.out.println("getVisibility");
        Random r = new Random();
        int visibility = r.nextInt(2);
        Event instance = new Event(null,null,null,null,visibility,null,null,null,null);
        int expResult = visibility;
        int result = instance.getVisibility();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVisibility method, of class Event.
     */
    @Test
    public void testSetVisibility() {
        System.out.println("setVisibility");
        Random r = new Random();
        int visibility = r.nextInt(2);
        Event instance = new Event();
        instance.setVisibility(visibility);
        int expResult = visibility;
        int result = instance.getVisibility();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Event.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String description = "none shall pass!";
        Event instance = new Event(null,null,null,null,0,description,null,null,null);
        String expResult = description;
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Event.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "none shall pass!";
        Event instance = new Event();
        instance.setDescription(description);
        String expResult = description;
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Event.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Random r = new Random();
        Long id = r.nextLong();
        Event instance = new Event();
        instance.setId(id);
        int expResult = (id != null ? id.hashCode() : 0);
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Event.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Event instance = new Event();
        String expResult = "persistence.Event[ id="+instance.getId()+" ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Event.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Event instance = new Event();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

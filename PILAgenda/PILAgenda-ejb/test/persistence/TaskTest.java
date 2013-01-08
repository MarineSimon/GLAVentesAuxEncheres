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
public class TaskTest {
    
    public TaskTest() {
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
     * Test of setId method, of class Task.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Random r = new Random();
        Long id = r.nextLong();
        Task instance = new Task();
        instance.setId(id);
        long expResult = id;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Task.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String name = "end the fucking tests";
        Task instance = new Task(name,null,null,null);
        String expResult = name;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Task.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "end the fucking tests";
        Task instance = new Task();
        instance.setName(name);
        String expResult = name;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLimitDate method, of class Task.
     */
    @Test
    public void testGetLimitDate() {
        System.out.println("getLimitDate");
        Random r = new Random();
        Date limitDate = new Date(r.nextLong());
        Task instance = new Task(null,limitDate,null,null);
        Date expResult = limitDate;
        Date result = instance.getLimitDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLimitDate method, of class Task.
     */
    @Test
    public void testSetLimitDate() {
        System.out.println("setLimitDate");
        Random r = new Random();
        Date limitDate = new Date(r.nextLong());
        Task instance = new Task();
        instance.setLimitDate(limitDate);
        Date expResult = limitDate;
        Date result = instance.getLimitDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Task.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String description = "because it's boring, you know...";
        Task instance = new Task(null,null,description,null);
        String expResult = description;
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Task.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "because it's boring, you know...";
        Task instance = new Task();
        instance.setDescription(description);
        String expResult = description;
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Task.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Random r = new Random();
        Long id = r.nextLong();
        Task instance = new Task();
        instance.setId(id);
        int expResult = (id != null ? id.hashCode() : 0);
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Task.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Task instance = new Task();
        String expResult = "persistence.Task[ id="+instance.getId()+" ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}

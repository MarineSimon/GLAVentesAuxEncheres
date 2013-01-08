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
public class CustomizeEventTest {
    
    public CustomizeEventTest() {
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
     * Test of setId method, of class CustomizeEvent.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Random r = new Random();
        Long id = r.nextLong();
        CustomizeEvent instance = new CustomizeEvent();
        instance.setId(id);
        Long expResult = id;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAlarm method, of class CustomizeEvent.
     */
    @Test
    public void testGetAlarm() {
        System.out.println("getAlarm");
        Random r = new Random();
        Date alarm = new Date(r.nextLong());
        CustomizeEvent instance = new CustomizeEvent(1,alarm,null,null,null);
        Date expResult = alarm;
        Date result = instance.getAlarm();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAlarm method, of class CustomizeEvent.
     */
    @Test
    public void testSetAlarm() {
        System.out.println("setAlarm");
        Random r = new Random();
        Date alarm = new Date(r.nextLong());
        CustomizeEvent instance = new CustomizeEvent();
        instance.setAlarm(alarm);
        Date expResult = alarm;
        Date result = instance.getAlarm();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCommentaire method, of class CustomizeEvent.
     */
    @Test
    public void testGetCommentaire() {
        System.out.println("getCommentaire");
        String comment = "comment";
        CustomizeEvent instance = new CustomizeEvent(0,null,comment,null,null);
        String expResult = comment;
        String result = instance.getCommentaire();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCommentaire method, of class CustomizeEvent.
     */
    @Test
    public void testSetCommentaire() {
        System.out.println("setCommentaire");
        String comment = "comment";
        CustomizeEvent instance = new CustomizeEvent();
        instance.setCommentaire(comment);
        String expResult = comment;
        String result = instance.getCommentaire();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class CustomizeEvent.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CustomizeEvent instance = new CustomizeEvent();
        Random r = new Random();
        Long id = r.nextLong();
        instance.setId(id);
        int expResult = (id != null ? id.hashCode() : 0);
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CustomizeEvent.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CustomizeEvent instance = new CustomizeEvent();
        String expResult = "persistence.CustomizeEvent[ id="+instance.getId()+" ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}

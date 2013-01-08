/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.awt.Color;
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
public class AgendaTest {
    
    public AgendaTest() {
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
     * Test of setId method, of class Agenda.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Random r = new Random();
        Long id = r.nextLong();
        Agenda instance = new Agenda();
        instance.setId(id);
        Long expResult = id;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAccess method, of class Agenda.
     */
    @Test
    public void testGetAccess() {
        System.out.println("getAccess");
        Random r = new Random();
        int access = r.nextInt(2);
        Agenda instance = new Agenda(access,null,null,null,null);
        int expResult = access;
        int result = instance.getAccess();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAccess method, of class Agenda.
     */
    @Test
    public void testSetAccess() {
        System.out.println("setAccess");
        Random r = new Random();
        int access = r.nextInt(2);
        Agenda instance = new Agenda();
        instance.setAccess(access);
        int expResult = access;
        int result=instance.getAccess();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColor method, of class Agenda.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Random r = new Random();
        String color = "rainbow";
        Agenda instance = new Agenda(0,color,null,null,null);
        String expResult = color;
        String result = instance.getColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setColor method, of class Agenda.
     */
    @Test
    public void testSetColor() {
        System.out.println("setColor");
        Random r = new Random();
        String color = "pink";
        Agenda instance = new Agenda();
        instance.setColor(color);
        String expResult = color;
        String result = instance.getColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Agenda.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String name = "name";
        Agenda instance = new Agenda(0,null,name,null,null);
        String expResult = name;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Agenda.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "name";
        Agenda instance = new Agenda();
        instance.setName(name);
        String expResult = name;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Agenda.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String description="this is a fucking description ²&é\"'(-è_çà)=)'";
        Agenda instance = new Agenda(0,null,null,description,null);
        String expResult = description;
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Agenda.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description="this is a fucking description ²&é\"'(-è_çà)=)'";
        Agenda instance = new Agenda();
        instance.setDescription(description);
        String expResult = description;
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Agenda.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Agenda instance = new Agenda();
        Random r = new Random();
        Long id = r.nextLong();
        instance.setId(id);
        int expResult = (id != null ? id.hashCode() : 0);
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Agenda.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Agenda instance = new Agenda();
        String expResult = "persistence.Agenda[ id="+instance.getId()+" ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}

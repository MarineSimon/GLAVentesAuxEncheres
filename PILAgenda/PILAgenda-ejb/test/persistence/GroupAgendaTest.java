/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

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
public class GroupAgendaTest {
    
    public GroupAgendaTest() {
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
     * Test of getName method, of class GroupAgenda.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String name = "iugiefgaz-'\"-()";
        GroupAgenda instance = new GroupAgenda(name,null,null);
        String expResult = name;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class GroupAgenda.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "iugiefgaz-'\"-()";
        GroupAgenda instance = new GroupAgenda();
        instance.setName(name);
        String expResult = name;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class GroupAgenda.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String description = "xd lol rofl";
        GroupAgenda instance = new GroupAgenda(null,description,null);
        String expResult = description;
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class GroupAgenda.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "xd lol rofl";
        GroupAgenda instance = new GroupAgenda();
        instance.setDescription(description);
        String expResult = description;
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class GroupAgenda.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Random r = new Random();
        Long id = r.nextLong();
        GroupAgenda instance = new GroupAgenda();
        instance.setId(id);
        Long expResult = id;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class GroupAgenda.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Random r = new Random();
        Long id = r.nextLong();
        GroupAgenda instance = new GroupAgenda();
        instance.setId(id);
        int expResult = (id != null ? id.hashCode() : 0);
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class GroupAgenda.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        GroupAgenda instance = new GroupAgenda();
        String expResult = "persistence.Group[ id="+instance.getId()+" ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}

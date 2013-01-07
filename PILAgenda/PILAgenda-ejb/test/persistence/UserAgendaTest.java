/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.util.List;
import java.util.Random;
import java.util.Set;
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
public class UserAgendaTest {
    
    public UserAgendaTest() {
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
     * Test of getEmail method, of class UserAgenda.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String email = "lacarottedu54@leslegumes.com";
        UserAgenda instance = new UserAgenda(email, null, null, null, null, null, null, null, null, null, null);
        String expResult = email;
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class UserAgenda.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "lacarottedu54@leslegumes.com";
        UserAgenda instance = new UserAgenda();
        instance.setEmail(email);
        String expResult = email;
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class UserAgenda.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String password = "jesuisunchoufleur";
        UserAgenda instance = new UserAgenda(null, password, null, null, null, null, null, null, null, null, null);
        String expResult = password;
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class UserAgenda.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "jesuisunchoufleur";
        UserAgenda instance = new UserAgenda();
        instance.setPassword(password);
        String expResult = password;
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLastname method, of class UserAgenda.
     */
    @Test
    public void testGetLastname() {
        System.out.println("getLastname");
        String lastname = "Otte";
        UserAgenda instance = new UserAgenda(null, null, lastname, null, null, null, null, null, null, null, null);
        String expResult = lastname;
        String result = instance.getLastname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastname method, of class UserAgenda.
     */
    @Test
    public void testSetLastname() {
        System.out.println("setLastname");
        String lastname = "Otte";
        UserAgenda instance = new UserAgenda();
        instance.setLastname(lastname);
        String expResult = lastname;
        String result = instance.getLastname();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFirstname method, of class UserAgenda.
     */
    @Test
    public void testGetFirstname() {
        System.out.println("getFirstname");
        String firstname = "Car";
        UserAgenda instance = new UserAgenda(null, null, null, firstname, null, null, null, null, null, null, null);
        String expResult = firstname;
        String result = instance.getFirstname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstname method, of class UserAgenda.
     */
    @Test
    public void testSetFirstname() {
        System.out.println("setFirstname");
        String firstname = "Car";
        UserAgenda instance = new UserAgenda();
        instance.setFirstname(firstname);
        String expResult = firstname;
        String result = instance.getFirstname();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLang method, of class UserAgenda.
     */
    @Test
    public void testGetLang() {
        System.out.println("getLang");
        String lang = "Légume";
        UserAgenda instance = new UserAgenda(null, null, null, null, lang, null, null, null, null, null, null);
        String expResult = lang;
        String result = instance.getLang();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLang method, of class UserAgenda.
     */
    @Test
    public void testSetLang() {
        System.out.println("setLang");
        String lang = "Légume";
        UserAgenda instance = new UserAgenda();
        instance.setLang(lang);
        String expResult = lang;
        String result = instance.getLang();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class UserAgenda.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        String address = "69 rue du diable, 66 666 Hell, Enfer";
        UserAgenda instance = new UserAgenda(null, null, null, null, null, address, null, null, null, null, null);
        String expResult = address;
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class UserAgenda.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "69 rue du diable, 66 666 Hell, Enfer";
        UserAgenda instance = new UserAgenda();
        instance.setAddress(address);
        String expResult = address;
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTasks method, of class UserAgenda.
     */
    @Test
    public void testSetTasks() {
        System.out.println("setTasks");
        List<Task> tasks = null;
        UserAgenda instance = new UserAgenda();
        instance.setTasks(tasks);
        List<Task> expResult = tasks;
        List<Task> result = instance.getTasks();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCustomizedEvents method, of class UserAgenda.
     */
    @Test
    public void testSetCustomizedEvents() {
        System.out.println("setCustomizedEvents");
        List<CustomizeEvent> customizedEvents = null;
        UserAgenda instance = new UserAgenda();
        instance.setCustomizedEvents(customizedEvents);
        List expResult = customizedEvents;
        List result = instance.getCustomizedEvents();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEvents method, of class UserAgenda.
     */
    @Test
    public void testSetEvents() {
        System.out.println("setEvents");
        List<Event> events = null;
        UserAgenda instance = new UserAgenda();
        instance.setEvents(events);
        List expResult = events;
        List result = instance.getEvents();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAdministratedGroups method, of class UserAgenda.
     */
    @Test
    public void testSetAdministratedGroups() {
        System.out.println("setAdministratedGroups");
        List<GroupAgenda> administratedGroups = null;
        UserAgenda instance = new UserAgenda();
        instance.setAdministratedGroups(administratedGroups);
        List expResult = administratedGroups;
        List result = instance.getAdministratedGroups();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAgendas method, of class UserAgenda.
     */
    @Test
    public void testSetAgendas() {
        System.out.println("setAgendas");
        List<Agenda> agendas = null;
        UserAgenda instance = new UserAgenda();
        instance.setAgendas(agendas);
        List expResult = agendas;
        List result = instance.getAgendas();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBelongToGroups method, of class UserAgenda.
     */
    @Test
    public void testSetBelongToGroups() {
        System.out.println("setBelongToGroups");
        List<GroupAgenda> belongToGroups = null;
        UserAgenda instance = new UserAgenda();
        instance.setBelongToGroups(belongToGroups);
        List expResult = belongToGroups;
        List result = instance.getBelongToGroups();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGuestToGroups method, of class UserAgenda.
     */
    @Test
    public void testSetGuestToGroups() {
        System.out.println("setGuestToGroups");
        List<GroupAgenda> guestToGroups = null;
        UserAgenda instance = new UserAgenda();
        instance.setGuestToGroups(guestToGroups);
        List expResult = guestToGroups;
        List result = instance.getGuestToGroups();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFollowedAgendas method, of class UserAgenda.
     */
    @Test
    public void testSetFollowedAgendas() {
        System.out.println("setFollowedAgendas");
        List<Agenda> followedAgendas = null;
        UserAgenda instance = new UserAgenda();
        instance.setFollowedAgendas(followedAgendas);
        List expResult = followedAgendas;
        List result = instance.getFollowedAgendas();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDisplayedAgendas method, of class UserAgenda.
     */
    @Test
    public void testSetDisplayedAgendas() {
        System.out.println("setDisplayedAgendas");
        List<Agenda> displayedAgendas = null;
        UserAgenda instance = new UserAgenda();
        instance.setDisplayedAgendas(displayedAgendas);
        List expResult = displayedAgendas;
        List result = instance.getDisplayedAgendas();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPhone method, of class UserAgenda.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone");
        String phone = "06 66 66 66 66";
        UserAgenda instance = new UserAgenda();
        instance.setPhone(phone);
        String expResult = phone;
        String result = instance.getPhone();
        assertEquals(expResult, result);
    }

    /**
     * Test of isSeeWeekEnd method, of class UserAgenda.
     */
    @Test
    public void testIsSeeWeekEnd() {
        System.out.println("isSeeWeekEnd");
        UserAgenda instance = new UserAgenda();
        boolean expResult = false;
        boolean result = instance.isSeeWeekEnd();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSeeWeekEnd method, of class UserAgenda.
     */
    @Test
    public void testSetSeeWeekEnd() {
        System.out.println("setSeeWeekEnd");
        boolean seeWeekEnd = true;
        UserAgenda instance = new UserAgenda();
        instance.setSeeWeekEnd(seeWeekEnd);
        boolean expResult = seeWeekEnd;
        boolean result = instance.isSeeWeekEnd();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCountry method, of class UserAgenda.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        String country = "potatoeland";
        UserAgenda instance = new UserAgenda(null, null, null, null, null, null, country, null, null, null, null);
        String expResult = country;
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCountry method, of class UserAgenda.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry");
        String country = "potatoeland";
        UserAgenda instance = new UserAgenda();
        instance.setCountry(country);
        String expResult = country;
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCity method, of class UserAgenda.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        String city = "Raclette city";
        UserAgenda instance = new UserAgenda(null, null, null, null, null, null, null, city, null, null, null);
        String expResult = city;
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCity method, of class UserAgenda.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        String city = "Raclette city";
        UserAgenda instance = new UserAgenda();
        instance.setCity(city);
        String expResult = city;
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimeZone method, of class UserAgenda.
     */
    @Test
    public void testGetTimeZone() {
        System.out.println("getTimeZone");
        String timeZone = "future";
        UserAgenda instance = new UserAgenda(null, null, null, null, null, null, null, null, timeZone, null, null);
        String expResult = timeZone;
        String result = instance.getTimeZone();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTimeZone method, of class UserAgenda.
     */
    @Test
    public void testSetTimeZone() {
        System.out.println("setTimeZone");
        String timeZone = "future";
        UserAgenda instance = new UserAgenda();
        instance.setTimeZone(timeZone);
        String expResult = timeZone;
        String result = instance.getTimeZone();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHourFormat method, of class UserAgenda.
     */
    @Test
    public void testGetHourFormat() {
        System.out.println("getHourFormat");
        String hourFormat = "36";
        UserAgenda instance = new UserAgenda(null, null, null, null, null, null, null, null, null, hourFormat, null);
        String expResult = hourFormat;
        String result = instance.getHourFormat();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHourFormat method, of class UserAgenda.
     */
    @Test
    public void testSetHourFormat() {
        System.out.println("setHourFormat");
        String hourFormat = "564";
        UserAgenda instance = new UserAgenda();
        instance.setHourFormat(hourFormat);
        String expResult = hourFormat;
        String result = instance.getHourFormat();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDefaultEventDuration method, of class UserAgenda.
     */
    @Test
    public void testGetDefaultEventDuration() {
        System.out.println("getDefaultEventDuration");
        String defaultEventDuration = "1.37";
        UserAgenda instance = new UserAgenda(null, null, null, null, null, null, null, null, null, null, defaultEventDuration);
        String expResult = defaultEventDuration;
        String result = instance.getDefaultEventDuration();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDefaultEventDuration method, of class UserAgenda.
     */
    @Test
    public void testSetDefaultEventDuration() {
        System.out.println("setDefaultEventDuration");
        String defaultEventDuration = "1.554";
        UserAgenda instance = new UserAgenda();
        instance.setDefaultEventDuration(defaultEventDuration);
        String expResult = defaultEventDuration;
        String result = instance.getDefaultEventDuration();
        assertEquals(expResult, result);
    }

    /**
     * Test of isKeyboardShortcut method, of class UserAgenda.
     */
    @Test
    public void testIsKeyboardShortcut() {
        System.out.println("isKeyboardShortcut");
        UserAgenda instance = new UserAgenda();
        boolean expResult = false;
        boolean result = instance.isKeyboardShortcut();
        assertEquals(expResult, result);
    }

    /**
     * Test of setKeyboardShortcut method, of class UserAgenda.
     */
    @Test
    public void testSetKeyboardShortcut() {
        System.out.println("setKeyboardShortcut");
        boolean keyboardShortcut = true;
        UserAgenda instance = new UserAgenda();
        instance.setKeyboardShortcut(keyboardShortcut);
        boolean expResult = true;
        boolean result = instance.isKeyboardShortcut();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class UserAgenda.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Random r = new Random();
        Long id = r.nextLong();
        UserAgenda instance = new UserAgenda();
        instance.setId(id);
        long expResult = id;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class UserAgenda.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Random r = new Random();
        Long id = r.nextLong();
        UserAgenda instance = new UserAgenda();
        instance.setId(id);
        int expResult = (id != null ? id.hashCode() : 0);
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class UserAgenda.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        UserAgenda instance = new UserAgenda();
        String expResult = "persistence.UserAgenda[ id="+instance.getId()+" ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author poste5
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({persistence.PeriodicityTest.class, persistence.AgendaTest.class, persistence.GroupAgendaTest.class, persistence.TaskTest.class, persistence.UserAgendaTest.class, persistence.CustomizeEventTest.class, persistence.EventTest.class})
public class PersistenceSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}

package dbfacades;

import dbfacades.DemoFacade;
import entity.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * UNIT TEST example that mocks away the database with an in-memory database
 * See Persistence unit in persistence.xml in the test packages
 * 
 * Use this in your own project by:
 *   - Delete everything inside the setUp method, but first, observe how test data is created
 *   - Delete the single test method, and replace with your own tests
 * 
 */
public class FacadeTest {
  
EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-test", null);

DemoFacade facade = new DemoFacade(emf);
 
  /** Setup test data in the database to a known state BEFORE Each test */
  @Before
  public void setUp() {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      //Delete all, since some future test cases might add/change data
      em.createQuery("delete from Car").executeUpdate();
      //Add our test data
      Car e1 = new Car("Volve");
      Car e2 = new Car("BMW");
      em.persist(e1);
      em.persist(e2);
      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }
  
  @Test
  public void testGetAllCars()
  {
      List<Car> allCars = facade.getAllCars();
      Assert.assertEquals(2,allCars.size());
      Assert.assertEquals("BMW", allCars.get(1).getMake());
  }
  
  @Test
  public void testGetCarById()
  {
    Car carById = facade.getCarById(2);
    Assert.assertNotEquals(2, carById);
  }
  
//  @Test
//  public void testdeleteCarByID()
//  {
//      boolean value = facade.getRemoveById(2);
//      Assert.assertTrue(value);
//  }
//  
//  public void testgetCarsByMake()
//  {
//      List<Car> getMake = facade.getAllCars();
//  }

  // Test the single method in the Facade
  @Test
  public void countEntities() {
    EntityManager em = emf.createEntityManager();
    try {
      long count = facade.countCars();
      Assert.assertEquals(2,count);
    } finally {
      em.close();
    }
  }
  
}
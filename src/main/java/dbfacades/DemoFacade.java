package dbfacades;

import entity.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
Simple Facade demo for this start-up project
 Use this in your own project by:
  - Rename this class to reflect your "business logic"
  - Delete the class entity.Car, and add your own entity classes
  - Delete the three public methods below, and replace with your own Facade Logic 
  - Delete all content in the main method

*/
public class DemoFacade {

  EntityManagerFactory emf;

  public DemoFacade(EntityManagerFactory emf) {
    this.emf = emf;
  }

  public Car addCar(Car car){
    EntityManager em = emf.createEntityManager();
    try{
      em.getTransaction().begin();
      em.persist(car);
      em.getTransaction().commit();
      return car;
    }finally{
      em.close();
    }
  }
  
  
  
  public Car getCarById(int id)
  {
      EntityManager em = emf.createEntityManager();
      try
      {
          Car c = em.find(Car.class, id);
          if(c != null)
            return c;
          else
            return null;
      }
      finally
      {
          em.clear();
      }
  }
  
  public boolean getRemoveById(int id)
  {
      EntityManager em = emf.createEntityManager();
      try
      {
//          try
//          {
//                int c = em.find(Car.class, id).getId();
//                em.remove(c);
//                return true;
//          }
//          catch(NullPointerException ex)
//          {
//              return false;
//          }
            int c = 0;
            c = em.find(Car.class, id).getId();
            if(c > 0)
            {
                em.remove(c);
                return true;
            }
            else
            {
                return false;
            }
      }
      finally
      {
          em.clear();
      }
  }
  
  public List<Car> getAllCars() {
    EntityManager em = emf.createEntityManager();
    try {
      return (List<Car>) em.createQuery("select m from Car m").getResultList();
    } finally {
      em.close();
    }
    //throw new UnsupportedOperationException("Hov hov hov!!!");
  }
  public long countCars() {
    EntityManager em = emf.createEntityManager();
    try {
      return (Long) em.createQuery("select Count(m) from Car m").getSingleResult();
    } finally {
      em.close();
    }

  }

  /*
  This will only work when your have added a persistence.xml file in the folder: 
     src/main/resources/META-INF
  You can use the file: persistence_TEMPLATE.xml (in this folder) as your template
  */
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    DemoFacade df = new DemoFacade(emf);
    df.addCar(new Car("Volvo"));
    df.addCar(new Car("WW"));
    df.addCar(new Car("Jaguar"));
    long numbOfCars = df.countCars();
    System.out.println("Number of cars: "+numbOfCars);
  }

}

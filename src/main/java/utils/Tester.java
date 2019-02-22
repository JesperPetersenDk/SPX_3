package utils;

import dbfacades.DemoFacade;
import entity.Car;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {

    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu", null);
        DemoFacade df = new DemoFacade(emf);
        System.out.println("Building the Table(s)");
        df.addCar(new Car("BMW"));
        df.addCar(new Car("Romeo"));
        df.addCar(new Car("Mini"));
        df.addCar(new Car("Hyundai"));        
        System.out.println("Oprettet!!");
        Persistence.generateSchema("pu", null);
    }

}

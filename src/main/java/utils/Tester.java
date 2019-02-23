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
        df.addCar(new Car("Volve"));
        df.addCar(new Car("BWM"));        
        System.out.println("Oprettet!!");
        Persistence.generateSchema("pu", null);
    }

}

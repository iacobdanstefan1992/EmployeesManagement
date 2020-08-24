package Main;

//import Entity.Product;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class Main_first {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        //Create Product

        //Product product1 = new Product();
        //product1.setName("Ciocolata");
        //product1.setPrice(3.56);
        //product1.setDate(LocalDateTime.now());
        //entityTransaction.begin();
        //entityManager.persist(product1);

        //try{
        //    entityTransaction.commit();
        //    entityManager.close();
        //    System.out.println("S-a creat un nou produs cu numele : "+
        //            product1.getName()+", prețul : "+
        //            product1.getPrice()+" și data :  "+
        //    product1.getDate());

        //}catch (Exception e){
        //    entityTransaction.rollback();
        //    entityManager.close();
        //    System.out.println("Nu s-a putut creea un produs din cauza unei valori invalide.");
        //}

        //Update Product

        //Product product1 = entityManager.find(Product.class,2);
        //product1.setName("Cafea");
        //product1.setPrice(4.55);
        //product1.setDate(LocalDateTime.now());
        //System.out.println("Product Name : "+ product1.getName());
        //System.out.println("Product Price : "+ product1.getPrice());
        //System.out.println("Product Date : "+ product1.getDate());

        //entityTransaction.begin();
        //entityTransaction.commit();
        //entityManager.close();

        //Remove Product

        //Product product1 = entityManager.find(Product.class, 4);
        //entityManager.remove(product1);
        //entityTransaction.begin();
        //entityTransaction.commit();
        //entityManager.close();

        //Select All products

        //Query query = entityManager.createQuery("Select e " + "from Product e " + "ORDER BY e.name ASC");
        //List<Product> list = (List<Product>)query.getResultList();
        //for(Product e : list){
        //    System.out.println("Product ID : "+e.getId());
        //    System.out.println("\t Product Name : "+e.getName());
        //    System.out.println("\t Product Price : "+e.getPrice());
        //    System.out.println("\t Product Date : "+e.getDate());
        //}
    }
}

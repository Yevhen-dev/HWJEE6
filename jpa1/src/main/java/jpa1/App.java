package jpa1;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPAFlats");
            em = emf.createEntityManager();
            initDBFlats();
            try {
                while (true) {

                    System.out.println("1: add flat");
                    System.out.println("2: view all flats");
                    System.out.println("3: show one bedrooms flats");
                    System.out.println("4: show two bedrooms flats");
                    System.out.println("5: show flats under 25000$");
                    System.out.println("6: show flats for 25000$ - 30000$");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addFlat(sc);
                            break;
                        case "2":
                            viewFlats();
                            break;
                        case "3":
                            showOneBedroomFlats();
                            break;
                        case "4":
                            showTwoBedroomFlats();
                            break;
                        case "5":
                            showFlatsLessTWFV();
                            break;
                        case "6":
                            showFlatsBetweenTWFVAndTRT();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void addFlat(Scanner sc) {
        System.out.print("Enter flat district: ");
        String district = sc.nextLine();
        System.out.print("Enter flat address: ");
        String address = sc.nextLine();
        System.out.print("Enter flat area: ");
        String area = sc.nextLine();
        System.out.print("Enter flat bedroom: ");
        String bedroom = sc.nextLine();
        System.out.print("Enter flat price: ");
        String price = sc.nextLine();


        em.getTransaction().begin();
        try {
            Flat flat = new Flat(district, address, area, bedroom, price);
            em.persist(flat);
            em.getTransaction().commit();

//            System.out.println(c.getId()); // HQL == JPQL
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void viewFlats() {
        Query query = em.createQuery("SELECT flat FROM Flat flat", Flat.class);
        printResult(query);
    }

    private static void showOneBedroomFlats() {
        Query query = em.createQuery("SELECT flat FROM Flat flat where bedrooms='1' ", Flat.class);
        printResult(query);
    }

    private static void showTwoBedroomFlats() {
        Query query = em.createQuery("SELECT flat FROM Flat flat where bedrooms='2' ", Flat.class);
        printResult(query);
    }

    private static void printResult(Query query) {
        List<Flat> flats = (List<Flat>) query.getResultList();

        for (Flat flat : flats)
            System.out.println(flat);
    }

    private static void showFlatsLessTWFV() {
        Query query = em.createQuery("SELECT flat FROM Flat flat where price <= '25000' ", Flat.class);
        printResult(query);
    }

    private static void showFlatsBetweenTWFVAndTRT() {
        Query query = em.createQuery("SELECT flat FROM Flat flat where price >= '25000' and price <= '30000' ", Flat.class);
        printResult(query);
    }

    private static void initDBFlats() {
        List<Flat> initialFlatsArray = new ArrayList<Flat>();

        Flat salPraci = new Flat("Saltov", "Praci 52", "53", "2","23500");
        initialFlatsArray.add(salPraci);

        Flat solnUkr = new Flat("Soln", "Ukrain 14", "84", "3","26800");
        initialFlatsArray.add(solnUkr);

        Flat zalutKrop = new Flat("Zalut", "Kropiv 56", "35", "1","20100");
        initialFlatsArray.add(zalutKrop);

        Flat kominPerem = new Flat("Komin", "Peremogy 22", "57", "2","24600");
        initialFlatsArray.add(kominPerem);

        Flat indusNez = new Flat("Indust", "Nezlam 18", "61", "3","26800");
        initialFlatsArray.add(indusNez);

        Flat kominNauk = new Flat("Komin", "Naukov 69", "36", "1","24000");
        initialFlatsArray.add(kominNauk);

        Flat zalutStad = new Flat("Zalut", "Stadio 12", "49", "2","25450");
        initialFlatsArray.add(zalutStad);

        Flat salUzh = new Flat("Saltov", "Uzhviy 97", "53", "2","24800");
        initialFlatsArray.add(salUzh);

        Flat indusHer = new Flat("Indust", "Heroiv 51", "72", "3","31150");
        initialFlatsArray.add(indusHer);

        Flat solnYar = new Flat("Soln", "Yarosha 44", "33", "1","22650");
        initialFlatsArray.add(solnYar);

        Flat salTrol= new Flat("Saltov", "Troley 35", "29", "1","25100");
        initialFlatsArray.add(salTrol);

        em.getTransaction().begin();
        try {
            for( Flat flat : initialFlatsArray ) {
                Flat tempFlat = new Flat(flat.getDistrict(), flat.getAddress(), flat.getArea(), flat.getBedrooms(), flat.getPrice());
                em.persist(tempFlat);
                System.out.println(tempFlat.getId());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
}



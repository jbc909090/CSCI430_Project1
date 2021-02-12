
import java.util.*;
import java.text.*;
import java.io.*;

public class Tester {
    public static void main(String[] s) {
     Product P1 = new Product("Headphone", "p1",20, 2000);
     Product P2 = new Product("Phone","p2", 10, 4000);
     final ProductList P;
     P = ProductList.instance();
  

     // Testing Product Class
     System.out.println(P1.getName() + " should be Headphone.");
     System.out.println(P2.getName() + " should be Phone.");

     System.out.println(P1.getQuantity() + " should be 20.");
     System.out.println(P2.getQuantity() + " should be 10.");

 System.out.println(P1.getPrice() + " should be 2000.");
     System.out.println(P2.getPrice() + " should be 4000.");

// Testing ProductList Class
     P.insertProduct(P1);
     P.insertProduct(P2);
     System.out.println(P.toString());

  }
}

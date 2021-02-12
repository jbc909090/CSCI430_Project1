package main;

import java.util.*;
import java.io.*;
public class Client implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private int id;
//  private static final String MEMBER_STRING = "M";
  private cart userCart = new cart();
  public  Client (String name,int ID , String address) {
    this.name = name;
    this.address = address;
    this.id = ID;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }
  public int getId() {
    return id;
  }
  public void setName(String newName) {
    name = newName;
  }
  public void setAddress(String newAddress) {
    address = newAddress;
  }

 /* public boolean equals(String id) {
    return this.id.equals(id);
  }
 */
  public String toString() {
    String string = "Member name " + name + " address " + address + " id " + id;
    return string;
  }
  
  public void AddToCart(Product pr, int amount)
  {
	  userCart.adder(pr,amount);
  }
  
  public void DisplayCart()
  {
	  userCart.printer();
  }
  
}

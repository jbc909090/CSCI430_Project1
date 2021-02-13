package main;

import java.util.*;
import java.io.*;
public class Client implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private int id;
//  private static final String MEMBER_STRING = "M";
  private LinkedList<Product> userCart = new LinkedList<Product>();
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
		userCart.add(pr);
		amount += pr.getQuantity;
		pr.setQuantity(amount);
  }
  
  public void DisplayCart()
  {
		for(Product pr : userCart)
		{	
			System.out.println(pr.toString());
		}	
		System.out.println("Total cost:" + userCart.getTotal());
  }
  
	public int getTotal()
	{
		int total = 0;
		for(Product pr : userCart)
		{
			total += (pr.getQuantity() * pr.getPrice());
		}
		return total;
	}
  
}
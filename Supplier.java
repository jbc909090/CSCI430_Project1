//Contains a class, second class, and then test/main for testing functionality below that

//Code for a supplier currently in developement, SupplierList stores all suppliers
//it contains their ID, name, products, product prices, and set and get functions
//it keep products and product prices in a linked list

import java.util.*;
import java.io.*;
public class Supplier implements Serializable {
	private int ID;
	private String name;
	//linked list with 1 double and 3 strings (price, ID, name, Price _info) in that order per item
	LinkedList<Supply> products = new LinkedList<>();
	//constructor leaves linked list empty
	public Supplier(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}
	//get for ID, supplier IDs lack set as they should not be changed
	public int get_ID () {
		return ID;
	}
	// get & set for name, it can be both accessed and changed
	public void set_name (String name) {
		this.name = name;
	}
	public String get_name () {
		return name;
	}
	//linked list management functions below
	//main gives an int from 0 to the end of list and then it gets the instance from that point
	//sends the supply instance to use_item
	//currently no error checking for going over the list size
	public void grab_item (int position) {
		Supply item = products.get(position);
		use_item(item);
	}
	//lets main create a supply instance without needing supply class, by giving data for the 4 variables
	//needs a double and 3 string
	//sends you to use_item
	public void create_item(double price, String ID, String name, String price_info) {
		Supply item = new Supply(price, ID, name, price_info);
		use_item(item);
	}
	//the use_item takes a supply instance, uses a scanner for user input
	//it can add or delete. Modification requires deleting it and creating new instance currently
	//in future may include printing item
	public void use_item (Supply item) {
		Scanner myObj = new Scanner(System.in); //create scanner object
		System.out.println("Add[1] or Delete[2] the instance from the list?");
		int choice = myObj.nextInt();
		switch (choice) {
			case 1: add_product(item);
			break;
			case 2: remove_product(item);
			break;
			default: System.out.println("neither action chosen, no action taken");
		}
	}
	//currently public may become private and only useable via other methods, currently not for testing
	//takes a supply instance adds it to the linked list
	public void add_product(Supply item) {
		products.add(item);
	}
	//same situation as add_product may become private later but is public for testing purposes
	public void remove_product (Supply item) {
		products.remove(item);
	}
	//lastly this prints the whole linked list safely
	//no inputs or outputs
	public void print_list () {
		for (int i = 0; i < products.size(); i++) {
			Supply item = products.get(i);
			item.print();
		}
	}
	//in the future there will be more things that allow imporitng from product adn such for IDs but
	//i think we will still use everything here
}
//Code for a suppliers "inventory", bascially what information we want from a supplier
//it contains a product name (according to supplier), price, price description (is it price per item or price per pound and so on), and ID (according to us)
//in same file as Supplier requires it to work
class Supply {
	private double price;
	private String ID, name, price_info;
	//contructor takes the data gives and slots it in
	public Supply(double price, String ID, String name, String price_info) {
		this.price = price;
		this.ID = ID;
		this.name = name;
		this.price_info = price_info;
	}
	//class for printing the data
	public void print () {
		System.out.println(price + "$ " + price_info + "\n" + ID + " " + name);
	}
}
import java.util.*;
import java.text.*;
import java.io.*;

public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Client client = new Client("Billy", 12345, "123 Sample Ln");
	private ClientList clientList;
	private ProductList productList;
	private SupplierList supplierList;
	private static final int EXIT = 0;
	private static final int ADD_PRODUCT = 1;
	private static final int ADD_CLIENT = 2;
	private static final int ADD_SUPPLIER = 3;
	private static final int ADD_TO_CART = 4;
	private static final int EDIT_A_FIELD = 5;
	private static final int MENU = 6;
	
	
	private UserInterface() {
		supplierList = SupplierList.instance();
		productList = ProductList.instance();
		clientList = ClientList.instance();
	}

	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}
	
	public String getToken(String prompt) {
    		do {
      			try {
        			System.out.println(prompt);
        			String line = reader.readLine();
        			StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        			if (tokenizer.hasMoreTokens()) {
          			return tokenizer.nextToken();
        			}
      			} catch (IOException ioe) {
        			System.exit(0);
      			}
    		} while (true);
  	}
	
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter a command. [Use command " + MENU + " for a list of commands]"));
				if (value >= EXIT && value <= MENU) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Please enter a number");
			}
		} while (true);
	}
	
	public void menu() {
		System.out.println("Enter the number corresponding to the desired command:");
		System.out.println("Exit: " + EXIT);
		System.out.println("Add a Product: " + ADD_PRODUCT);
		System.out.println("Add a Client: " + ADD_CLIENT);
		System.out.println("Add a Supplier: " + ADD_SUPPLIER);
		System.out.println("Add to Cart: " + ADD_TO_CART);
		System.out.println("Edit a Field: " + EDIT_A_FIELD);
		System.out.println("Menu: " + MENU);
	}
	
	public void addProduct() {
		boolean successful;
		String productName = getToken("Enter the product name: ");
		int productQuantity = Integer.parseInt(getToken("Enter the product quantity: "));
		String productID = getToken("Enter the product ID: ");
		int productPrice = Integer.parseInt(getToken("Enter the product price: "));

		Product newProduct = new Product(productName, productID, productQuantity, productPrice);
		successful = productList.insertProduct(newProduct);
		
		if (successful) {
			System.out.println("Product with details (" + newProduct.toString() + ") added successfully");
		} else {
			System.out.println("Issue adding product!");
		}
	}
	
	public void addClient() {
		boolean successful;
		String clientName = getToken("Enter the client's name: ");
		int clientID = Integer.parseInt(getToken("Enter the client's ID: "));
		String clientAddress = getToken("Enter the client's address: ");

		Client newClient = new Client(clientName, clientID, clientAddress); //DOESNT WANT TO CREATE THE CLIENT OR ADD TO LIST
		successful = clientList.insertMember(newClient);

		if (successful) {
			System.out.println("Client with details (" + newClient.toString() + ") added successfully");
		} else {
			System.out.println("Issue adding client!");
		}
		
	}
	
	public void addSupplier() {
		boolean successful;
		int supplierID = Integer.parseInt(getToken("Enter the supplier ID: "));
		String supplierName = getToken("Enter the supplier name:");

		Supplier newSupplier = new Supplier(supplierID, supplierName);

		successful = supplierList.insertSupplier(newSupplier);
		if (successful) {
			System.out.println("Supplier with name " + newSupplier.getName() + " and ID " + newSupplier.get_ID() + " added successfully");
		} else {
			System.out.println("Issue adding supplier!");
		}
	}
	
	public void addToCart() { //PLAN TO ADD SUPPORT FOR SPECIFYING CLIENT BY ID TO ADD TO CART
		String productToAddID = getToken("Please enter the product ID string you wish to add: ");
		Product productToAdd = productList.search(productToAddID);
		int quant = Integer.parseInt(getToken("Enter the quantity to be added to the cart: "));
		client.AddToCart(productToAdd, quant);
	}

	public void editAField() {
		System.out.println("Would you like to edit");
		System.out.println("1. Product");
		System.out.println("2. Client");
		System.out.println("3. Supplier");
		int choice = Integer.parseInt(getToken("Enter your choice: "));
		String id;
		int ID, position;
		switch (choice) {
			case 1: id = getToken("Enter ID of product to edit");
				position = productList.IDcheck(id);
				Product itemP = productList.get_listed_obj(position);
				itemP = edit_product(itemP);
				productList.set_listed_obj(position, itemP);
				break;
			case 2: ID = Integer.parseInt(getToken("Enter ID of client to edit"));
				position = clientList.IDcheck(ID);
				Client itemC = clientList.get_listed_obj(position);
				itemC = edit_client(itemC);
				clientList.set_listed_obj(position, itemC);
				break;
			case 3: ID = Integer.parseInt(getToken("Enter ID of supplier to edit"));
				position = supplierList.IDcheck(ID);
				Supplier itemS = supplierList.get_listed_obj(position);
				itemS = edit_supplier(itemS);
				supplierList.set_listed_obj(position, itemS);
				break;
			default:
				System.out.println("Incorrect choice");
		}	
	}
	public Supplier edit_supplier (Supplier item) {
		int userEdit = Integer.parseInt(getToken("Edit NAME[1]"));
		switch (userEdit) {
			case 1: String name = getToken("To what name?");
				item.setName(name);
				break;
			default: System.out.println("no action chosen, none taken");
		}
		return item;
	}
	public Client edit_client (Client item) {
		int userEdit = Integer.parseInt(getToken("Edit NAME[1] or ADDRESS[2]"));
		switch (userEdit) {
			case 1: String name = getToken("To what name?");
				item.setName(name);
				break;
			case 2: String address = getToken("To what address?");
				item.setAddress(address);
				break;
			default: System.out.println("no action chosen, none taken");
		}
		return item;
	}
	public Product edit_product (Product item) {
		int userEdit = Integer.parseInt(getToken("Edit NAME[1], PRICE[2], or QUANTITY[3]"));
		switch (userEdit) {
			case 1: String name = getToken("To what name?");
				item.setName(name);
				break;
			case 2: int price = Integer.parseInt(getToken("To what price"));
				item.setPrice(price);
				break;
			case 3: int quantity = Integer.parseInt(getToken("To what quantity"));
				item.setQuantity(quantity);
				break;
			default: System.out.println("no action chosen, none taken");
		}
		return item;
	}
	public void process() {
		int command;
		menu();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
				case ADD_PRODUCT:
					addProduct();
					break;
				case ADD_CLIENT:
					addClient();
					break;
				case ADD_SUPPLIER:
					addSupplier();
					break;
				case ADD_TO_CART:
					addToCart();
					break;
				case EDIT_A_FIELD:
					editAField();
					break;
				case MENU:
					menu();
					break;
				default:
					System.out.println("Not a valid command");
					command = EXIT;
			}
		}
	}
					
	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}

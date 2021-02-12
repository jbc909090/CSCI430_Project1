public class Tester {
    public static void main(String[] s) {
		Supplier S1 = new Supplier(1, "Cereal");
		Supplier S2 = new Supplier(21, "Woodcutters");
		final SupplierList S;
		S = SupplierList.instance();
		System.out.println(S1.get_ID());
		System.out.println(S1.get_name());
		System.out.println(S2.get_ID());
		System.out.println(S2.get_name());
		S1.create_item(1.20, "CHX", "Chex's mix", "price per box"); //i will hit 1 to add
		S1.create_item(1.45, "SWD", "Saw Dust", "price per pound"); //i will hit 1 to add
		S1.grab_item(0);//i will hit one to add
		S1.print_list();
		S1.grab_item(2);//i will hit 2 to remove_product
		S1.print_list();
		S2.create_item(2.23, "SPR_P", "Spruce Planks", "Price per cubic meter");//hit 1
		S2.create_item(2.05, "BIR_L", "Birch Logs", "Price per cubic meter");//hit 1
		S2.create_item(4.62, "OAK_S", "Stained Oak Planks", "Price per cubic meter");//hit 1
		S2.print_list();
		S2.grab_item(0);//delete it
		S2.print_list();
		S2.set_name("Minnesote Logs");
		System.out.println(S2.get_name());
		
		//testing SupplierList
		S.insertSupplier(S1);
		S.insertSupplier(S2);
		System.out.println(S.toString());
	}
}
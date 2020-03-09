package alpha;

public class View {
	public void menuOptions() {
		
		System.out.println("Menu Options:");
		System.out.println("1. View products");
		System.out.println("2. View orders");
		System.out.println("3. View customers");
		System.out.println("4. View product details");
		System.out.println("5. View order details");
		System.out.println("6. View products by category");
		System.out.println("7. View orders by status");
	}
	
	public void viewStringBuilder(StringBuilder sb) {
		System.out.println(sb);
	}
	
	public void close() {
		System.out.println("Quitting ...");
	}
}

package alpha;

public class Model {

	String selectAllFromProduct() {
		return "SELECT * FROM product";
	}
	
	String selectAllFromCustomers() {
		return "SELECT * FROM customers";
	}
	
	String selectAllFromOrders() {
		return "SELECT * FROM orders";
	}

	String getProductDetails(String productName) {
		return "select * from product_details where name ='" + productName+"'";
	}
}
	


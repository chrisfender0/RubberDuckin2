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
	
	String selectAllFromOrderDetails(String ui) {
		return "SELECT * from orderdetails_view where order_number ='" + ui + "'";
	}
	
	String selectAllActiveOrders () {
		return "SELECT * from active_view";
	}
	
	String selectProductsFromCategory(String category) {
		return "select name, unit_price from category_view where category_name ='" + category + "'";
	}
}

	


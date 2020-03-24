// ProductFactory.java
public class ProductFactory {
    public Product createProduct() {
	return new Product();
    }
    
    public void configureProduct(Product product) {
	product.configure();
    }
}

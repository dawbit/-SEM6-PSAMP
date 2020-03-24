// Product.java
public class Product {
    public Product() {
	// constructor implementation
    }

    public void configure() {
	// configuration implementation
    }

    static aspect FlagAccessViolation {
	pointcut factoryAccessViolation() 
	    : call(Product.new(..)) && !within(ProductFactory+);
	
	pointcut configuratorAccessViolation() 
	    : call(* Product.configure(..)) && !within(ProductConfigurator+);

	declare error 
	    :  factoryAccessViolation() || configuratorAccessViolation() 
	    : "Access control violation";
    }
}



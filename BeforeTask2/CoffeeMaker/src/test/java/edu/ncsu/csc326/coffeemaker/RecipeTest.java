package edu.ncsu.csc326.coffeemaker;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Test;


public class RecipeTest {
	Recipe recipe;
	
   
    @Before
    public void setUp() 
    {   recipe = new Recipe();
    	
    }
    
   
    @Test
	public void testSetAmtCoffeeException() {
		Throwable exception = assertThrows(
				RecipeException.class, () -> {
					String s = "-14";
					recipe.setAmtCoffee(s); // Should throw a RecipeExceptionException
				}
				);
		assertEquals("Units of coffee must be a positive integer", exception.getMessage());
	}
   
    @Test
	public void testSetAmtMilkException() {
		Throwable exception = assertThrows(
				RecipeException.class, () -> {
					recipe.setAmtMilk("-2"); // Should throw a RecipeExceptionException
				}
				);
		assertEquals("Units of milk must be a positive integer", exception.getMessage());
	}
    
    // This one should fail because the setName method is not checking this and the exception is not thrown.
    @Test
    public void testInputNameValidity() {
		Throwable exception = assertThrows(
				IllegalArgumentException.class, () -> {
					recipe.setName("))&&"); 
				}
				);
    }
    
    
    // This one tests if the name is null and  because the setName method does not handle a null input name.
    // We expect an exception to be thrown but no exception is thrown.
   
    @Test
    public void testAsserNametNotNull() {
    	String n= null;
		recipe.setName(n); 
    assertNotNull(null, recipe.getName());
    }
    
    // This test should fail because invalid price inputs are not checked in the setPrice method
    @Test
    public void testInvalidPriceInput() {
		Throwable exception = assertThrows(
				IllegalArgumentException.class, () -> {
					String price = "///";
					recipe.setPrice(price); 
				}
				);
    }
    
    
    @Test
	public void testSetPriceException() {
		Throwable exception = assertThrows(
				RecipeException.class, () -> {
					recipe.setPrice("-23"); // Should throw a RecipeExceptionException
				}
				);
		assertEquals("Price must be a positive integer", exception.getMessage());
	}
    
    @Test
	public void testSetAmtChocolateException() {
		Throwable exception = assertThrows(
				RecipeException.class, () -> {
					recipe.setAmtChocolate("-356"); // Should throw a RecipeExceptionException
				}
				);
		assertEquals("Units of chocolate must be a positive integer", exception.getMessage());
	}
    

}

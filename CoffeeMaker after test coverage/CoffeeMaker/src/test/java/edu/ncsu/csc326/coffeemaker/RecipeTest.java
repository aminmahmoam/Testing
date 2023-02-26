package edu.ncsu.csc326.coffeemaker;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
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
    
    //???
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
	public void testInvalidPriceInput1() {
		Throwable exception = assertThrows(
				RecipeException.class, () -> {
					String price = "abc";
					recipe.setPrice(price);
				}
		);
	}

	@Test
	public void testInvalidPriceInput2() {
		Throwable exception = assertThrows(
				RecipeException.class, () -> {
					String price = "-12";
					recipe.setPrice(price);
				}
		);
	}

	@Test
	public void testInvalidPriceInput3() throws RecipeException{
		try{
			recipe.setPrice("12");
		} catch (RecipeException e){
			e.getMessage();
		}
		int expected = 12;
		assertEquals(expected, recipe.getPrice());
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

	@Test
	public void testFDataChocolateSet() {
		String result = "";
		try {
			recipe.setAmtChocolate("abc");
		} catch (RecipeException e) {
			result = e.getMessage();
		}
		assertEquals("Units of chocolate must be a positive integer", result);
	}
	@Test
	public void testFDataCoffeeSet() {
		String result = "";
		try {
			recipe.setAmtCoffee("abc");
		} catch (RecipeException e) {
			result = e.getMessage();
		}
		assertEquals("Units of coffee must be a positive integer", result);
	}
	@Test
	public void testFDataMilkSet() {
		String result = "";
		try {
			recipe.setAmtMilk("abc");
		} catch (RecipeException e) {
			result = e.getMessage();
		}
		assertEquals("Units of milk must be a positive integer", result);
	}
	@Test
	public void testFDataSugarSet() {
		String result = "";
		try {
			recipe.setAmtSugar("abc");
		} catch (RecipeException e) {
			result = e.getMessage();
		}
		assertEquals("Units of sugar must be a positive integer", result);
	}
	@Test
	public void testNDataSugarSet() {
		String result = "";
		try {
			recipe.setAmtSugar("-12");
		} catch (RecipeException e) {
			result = e.getMessage();
		}
		assertEquals("Units of sugar must be a positive integer", result);
	}
	@Test
	public void testToString() {
		recipe.setName("hello");
		String result = recipe.toString();
		String expected = "hello";
		assertEquals(result, expected);
	}

	@Test
	public void testHash() {
		recipe.setName(null);
		int result = recipe.hashCode();
		int expected = 31;
		assertEquals(result, expected);
	}

	@Test
	public void testEquals() {
		recipe.setName(null);
		Recipe newRecipe = new Recipe();
		newRecipe.setName("abc");
		Boolean result = recipe.equals(newRecipe);
		assertEquals(result, false);

		newRecipe.setName(null);
		result = recipe.equals(newRecipe);
		assertEquals(result, false);

		String test = "abc";
		result = recipe.equals(test);
		assertEquals(result, false);

		recipe.setName("abc");
		newRecipe.setName("abc");
		result = recipe.equals(newRecipe);
		assertEquals(result, true);
	}
}

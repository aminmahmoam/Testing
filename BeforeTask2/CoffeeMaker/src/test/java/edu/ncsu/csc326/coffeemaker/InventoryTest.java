package edu.ncsu.csc326.coffeemaker;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;


public class InventoryTest {
	Inventory inventory;
	Recipe recipe;
	
	@Before
	public void setup() throws Exception 
	{
		inventory = new Inventory();
		recipe = new Recipe();
		inventory.setChocolate(2);
		inventory.setCoffee(10);
		inventory.setMilk(50);
		inventory.setSugar(1);
		
		recipe.setName("R1");
	    recipe.setPrice("12");
		recipe.setAmtChocolate("5");
		recipe.setAmtCoffee("10");
		recipe.setAmtMilk("6");
		recipe.setAmtSugar("2");
	}
	
	
	// The addChocolate function throws exception for negative integers so we test that here
	@Test
	public void testAddChocolateException() {
		Throwable exception = assertThrows(
				InventoryException.class, () -> {
					String s = "-2";
					inventory.addChocolate(s);  // Should throw an InventoryException
				}
				);
		assertEquals("Units of chocolate must be a positive integer", exception.getMessage());
	}
	
	
	@Test
	public void testAddCoffeeException() {
		Throwable exception = assertThrows(
				InventoryException.class, () -> {
					String s = "-12";
					inventory.addCoffee(s);  // Should throw an InventoryException
				}
				);
		assertEquals("Units of coffee must be a positive integer", exception.getMessage());
	}
	
	@Test
	public void testAddMilkException() {
		Throwable exception = assertThrows(
				InventoryException.class, () -> {
					String s = "-12";
					inventory.addMilk(s);  // Should throw an InventoryException
				}
				);
		assertEquals("Units of milk must be a positive integer", exception.getMessage());
	}
	
	// The test fails because line 182 in Inventory class is wrong
	@Test
	public void testAddSugarException() {
		Throwable exception = assertThrows(
				InventoryException.class, () -> {
					String s = Integer.toString(-12);
					inventory.addSugar(s);  // Should throw an InventoryException
				}
				);
		assertEquals("Units of sugar must be a positive integer", exception.getMessage());
	}
	
	@Test
	public void testEnoughIngredients()
	   
	{   // There are not enough ingredients for this recipe so we expect to get a false and the 
		// recipe is not added
		boolean added = inventory.enoughIngredients(recipe);
		// Since the name of the recipe that was deleted is "R1" we expect to get R1
		boolean expected = false;
		assertEquals(expected,added);
	}
	
	@Test
	public void testUseIngredients()
	   
	{   // The recipe could not be added because there was not enough ingredients
		boolean result = inventory.useIngredients(recipe);
		// We expect to get a false since no ingredient is used
		boolean expected = false;
		assertEquals(expected,result);
	}
	

}

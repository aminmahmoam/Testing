package edu.ncsu.csc326.coffeemaker;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;


public class CoffeeMakerTest {
	 CoffeeMaker coffeeMaker;
	 Recipe recipe;
	 Inventory inventory;
	 RecipeBook recipeBook;
	 
	 @Before
	 public void setUp() throws Exception 
	 {
		 coffeeMaker = new CoffeeMaker();
		 recipe = new Recipe();
		 inventory = new Inventory();
		 recipeBook = new RecipeBook();
		 recipe.setName("R1");
		 recipe.setPrice("12");
	     recipe.setAmtChocolate("5");
		 recipe.setAmtCoffee("10");
		 recipe.setAmtMilk("6");
		 recipe.setAmtSugar("2");
		 
	 }
	 
	 // This test fails because the add recipe method does not check if the recipe is null
	// If the recipe is null, it should not be added to the array of recipes
	 @Test
	 public void testAddrecipe()
	 {
		 Recipe recipe1= null;
		 boolean added = coffeeMaker.addRecipe(recipe1);
		 boolean expected = false;
		 assertEquals(expected, added);
	 }
	 
	 // It passes because the deletRecipe in the recipeBook class checks if any recipe exist in that index
	 @Test
	 public void testDeleteRecipe1()
	 {
		 String deleted= coffeeMaker.deleteRecipe(0);
		 String expected = null;
		 assertEquals(expected,deleted);
	 }
	 
	 // The test passes
	 @Test
	 public void testDeleteRecipe2()
	 {   coffeeMaker.addRecipe(recipe);
		 String deleted= coffeeMaker.deleteRecipe(0);
		 String expected = "R1";
		 assertEquals(expected,deleted);
	 }
	 
	 // I don't know why I get error here
	 // addSugar method does not work properly because of line 182 in the Inventory class
	 // But what is the problem with chocolate?
	 @Test
	 public void testAddInvnetory() 
	 {
		 try 
		 {  
			 coffeeMaker.addInventory("2", "12", "3", "6");
		 }
		 catch(InventoryException e) 
		 {
			// fail("InventoryException should not be thrown");
		 }
		 
		 String inventoryCheck = coffeeMaker.checkInventory();
		 String expected = "Coffee: 17\nMilk: 27\nSugar: 18\nChocolate: 21\n";
		 assertEquals(expected,inventoryCheck);
		 
	 }
	 
	 // Test if we get the subtraction of paid money and the price of the recipe
	 // Here the recipe can be made because all the ingredients are enough
	@Test
	public void testMakeCoffee1()
	
	{   coffeeMaker.addRecipe(recipe);
		int paidAmount = 24;
		try {
			coffeeMaker.addInventory("15", "15", "15", "15");
		} catch (InventoryException e) {
			e.getMessage();
		}
		int expected = 24-12;
		int result = coffeeMaker.makeCoffee(0, paidAmount);
		assertEquals(expected, result);
	}
	 
	// Here the expected return value is 6 since the paid money is not enough and the transaction does not take place
	@Test
	public void testMakeCoffee2()
	
	{   coffeeMaker.addRecipe(recipe);
		int paidAmount = 6;
		try {
			coffeeMaker.addInventory("15", "15", "15", "15");
		} catch (InventoryException e) {
			e.getMessage();
		}
		int expected = 6;
		int result = coffeeMaker.makeCoffee(0, paidAmount);
		assertEquals(expected, result);
	}
	
	// Here the ingredients are not enough so the recipe can not be made
	@Test
    public void testMakeCoffee3()
	
	{   coffeeMaker.addRecipe(recipe);
		int paidAmount = 6;
		try {
			coffeeMaker.addInventory("2", "15", "4", "1");
		} catch (InventoryException e) {
			e.getMessage();
		}
		int expected = 6;
		int result = coffeeMaker.makeCoffee(0, paidAmount);
		assertEquals(expected, result);
	}
	
	// This test fails because the method does not check for negative arguments
	 @Test
	public void testNegativeArgumentsForMakeCoffee1() {
		Throwable exception = assertThrows(
				IllegalArgumentException.class, () -> {
					coffeeMaker.addRecipe(recipe);
					int paidAmount = -12;
					try {
						coffeeMaker.addInventory("2", "15", "4", "1");
					} catch (InventoryException e) {
						e.getMessage();
					}
					coffeeMaker.makeCoffee(0, paidAmount);
				    }
				);
		assertEquals("The passed argument as the paid price should be positive integer", exception.getMessage());
    }
	 
    // This test passes because negative index in the array is checked.
	 @Test
	 public void testNegativeArgumentsForMakeCoffee2() {
			Throwable exception = assertThrows(
					IndexOutOfBoundsException.class, () -> {
						coffeeMaker.addRecipe(recipe);
						int paidAmount = 12;
						try {
							coffeeMaker.addInventory("2", "15", "4", "1");
						} catch (InventoryException e) {
							e.getMessage();
						}
						coffeeMaker.makeCoffee(-4, paidAmount);
					    }
					);
			assertEquals("Index -4 out of bounds for length 4", exception.getMessage());
	    }

	 
	 

}

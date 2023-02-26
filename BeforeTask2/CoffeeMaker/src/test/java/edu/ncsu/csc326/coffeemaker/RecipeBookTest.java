package edu.ncsu.csc326.coffeemaker;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;



public class RecipeBookTest {
	RecipeBook recipeBook;
	Recipe newRecipe;
	Recipe oldRecipe;
	int newRecipeIndex;
	
	
	@Before
	public void setup() throws Exception
	{
		recipeBook= new RecipeBook();
		newRecipe = new Recipe();
		newRecipe.setName("R1");
		newRecipe.setPrice("12");
		newRecipe.setAmtChocolate("5");
		newRecipe.setAmtCoffee("10");
		newRecipe.setAmtMilk("6");
		newRecipe.setAmtSugar("2");
		
		oldRecipe = new Recipe();
		oldRecipe.setName("R0");
		oldRecipe.setPrice("12");
		oldRecipe.setAmtChocolate("10");
		oldRecipe.setAmtCoffee("8");
		oldRecipe.setAmtMilk("2");
		oldRecipe.setAmtSugar("5");
		
	}
    // The test passes.
	@Test
	public void testEditRecipeException() 
	{
		Throwable exception =
		assertThrows(
		IndexOutOfBoundsException.class,
		() -> { recipeBook.editRecipe(-4, oldRecipe);
				}
		);
	}
	
	@Test
	public void testAddRecipe()
	{   // Here we try to add a recipe that has already been added to the recipeBook
		// The addRecipe function should return false which means the repeated recipe can not be added
	    recipeBook.addRecipe(newRecipe);
	    Boolean canBeAdded = recipeBook.addRecipe(newRecipe);
        Boolean expected = false;
		assertEquals(expected,canBeAdded);
		
	}
	// Try to edit oldRecipe with the newRcecipe
	@Test
	public void testEditRecipe1()
	 
	{   
		recipeBook.addRecipe(oldRecipe);
		// The name of edited recipe (OldRecipe) should be returned so we expect R0
		String recipeName =recipeBook.editRecipe(0, newRecipe);
		String expected = "R0";
		assertEquals(expected,recipeName);
		
	}
	
	@Test
	public void testEditRecipe2()
	 
	{   // No recipe has been added to the recipe book so no recipe can be found at index  0
		String recipeName =recipeBook.editRecipe(0, newRecipe);
		// Since the recipe can not be found we expect the editRecipe function to return null
		String expected = null;
		assertEquals(expected,recipeName);
		
	}
	
	@Test
	public void testDeletRecipe1()
	 
	{   // No recipe has been added to the recipe book so no recipe can be found at index  0 to be deleted
		String recipeName =recipeBook.deleteRecipe(0);
		// Since the recipe can not be found  to delete we expect the deleteRecipe function to return null
		String expected = null;
		assertEquals(expected,recipeName);
		
	}
	
	@Test
	public void testDeletRecipe2()
	   
	{   // We add a recipe that will be stored in index 0 in the array of recipes
		recipeBook.addRecipe(newRecipe);
		// We delete the added recipe by specifying its index
		String recipeName =recipeBook.deleteRecipe(0);
		// Since the name of the recipe that was deleted is "R1" we expect to get R1
		String expected = "R1";
		assertEquals(expected,recipeName);
		
	}
	
	@Test
	public void testGetRecipes()

	{   // No recipes have been added to the recipe book 
		// so we expect to get an empty array
		Recipe expected []= {};
		Recipe recipes [] = recipeBook.getRecipes();
		assertArrayEquals(expected, recipes);
		
	}
	
	
}

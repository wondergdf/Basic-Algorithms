
class Meal {

	private String staple_food;
	private String assisted_food;
	private String drink;
	

	public String getAssisted_food() {
		return assisted_food;
	}

	public void setAssisted_food(String assisted_food) {
		this.assisted_food = assisted_food;
	}

	public String getStaple_food() {
		return staple_food;
	}

	public void setStaple_food(String staple_food) {
		this.staple_food = staple_food;
	}

	public String getDrink() {
		return drink;
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}

}

abstract class MealBuilder{
	//abstract factory
	protected Meal meal;
	
	
	//factory method list
	public abstract void  makeStapleFood();
	public abstract void  makeAssistedFood();
	public abstract void  makedrink();
	
	public MealBuilder(){
		meal = new Meal();
		
	}

	public Meal getMeal() {
		return meal;
	}
	
	
}


class Meal_One_Builder extends MealBuilder{
	//concrete factory

	@Override
	public void makeStapleFood() {
         this.meal.setStaple_food("steak");		
	}

	@Override
	public void makeAssistedFood() {
       this.meal.setAssisted_food("salad");		
	}

	@Override
	public void makedrink() {
      this.meal.setDrink("wine");		
	}
	
}

class Meal_Two_Builder extends MealBuilder{
	//concrete factory
	
	@Override
	public void makeStapleFood() {
         this.meal.setStaple_food("Hamberger");		
	}

	@Override
	public void makeAssistedFood() {
       this.meal.setAssisted_food("New Orleans Chicken Wing");		
	}

	@Override
	public void makedrink() {
      this.meal.setDrink("pepsi");		
	}
	
}

class Service {
	private MealBuilder mealBuilder ;

	public MealBuilder getMealBuilder() {
		return mealBuilder;
	}

	public void setMealBuilder(MealBuilder mealBuilder) {
		this.mealBuilder = mealBuilder;
	}
	
	public Meal makeMeal(){
		mealBuilder.makeStapleFood();
		mealBuilder.makeAssistedFood();
		mealBuilder.makedrink();
		
		return mealBuilder.getMeal();
	}
}


public class Abstract_Factory_Demo {
	
	public static void main(String[] args) {
		
		Meal_One_Builder meal_One = new Meal_One_Builder();
		Meal_Two_Builder meal_Two = new Meal_Two_Builder();
		
		Service waitress = new Service();
		
		waitress.setMealBuilder(meal_One);		
		Meal mealA = waitress.makeMeal();	
	    System.out.println(mealA.getStaple_food()+"_"+mealA.getAssisted_food()+"_"+mealA.getDrink());
	   
	   
	   waitress.setMealBuilder(meal_Two);
	   Meal mealB = waitress.makeMeal();
	   System.out.println(mealB.getStaple_food()+"_"+mealB.getAssisted_food()+"_"+mealB.getDrink());
	   
		
	}
	

	
}


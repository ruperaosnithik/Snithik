import java.util.*;
import java.io.*;

class Meal {
    private String name;
    private String[] ingredients;

    Meal(String name, String[] ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() { return name; }
    public String[] getIngredients() { return ingredients; }

    @Override
    public String toString() {
        return name + " -> " + Arrays.toString(ingredients);
    }
}

public class WeeklyMealPlanner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Recipe book with dietary options
        Map<String, String[]> recipeBook = new HashMap<>();
        recipeBook.put("Pasta", new String[]{"pasta", "tomato", "cheese"});
        recipeBook.put("Salad", new String[]{"lettuce", "tomato", "cucumber"});
        recipeBook.put("Omelette", new String[]{"eggs", "onion", "tomato", "spinach"});
        recipeBook.put("Smoothie", new String[]{"banana", "milk", "berries", "honey"});
        recipeBook.put("Soup", new String[]{"chicken", "carrot", "celery", "onion"});
        recipeBook.put("Sandwich", new String[]{"bread", "cheese", "ham", "lettuce"});
        recipeBook.put("Stir Fry", new String[]{"chicken", "broccoli", "soy sauce", "garlic"});

        String[] days = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
        Map<String, Meal> weeklyPlan = new LinkedHashMap<>();

        // Input dish for each day
        for (String day : days) {
            System.out.print(day + " dish: ");
            String dish = sc.nextLine();
            if (recipeBook.containsKey(dish)) {
                weeklyPlan.put(day, new Meal(dish, recipeBook.get(dish)));
            } else {
                System.out.println("❌ " + dish + " not found in recipe book!");
                weeklyPlan.put(day, new Meal(dish, new String[]{"No ingredients"}));
            }
        }

        // Print weekly plan with ingredients
        System.out.println("\n📅 Weekly Meal Plan with Ingredients:");
        for (Map.Entry<String, Meal> entry : weeklyPlan.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Build grocery list (unique items)
        Set<String> grocerySet = new HashSet<>();
        for (Meal m : weeklyPlan.values()) {
            for (String item : m.getIngredients()) {
                grocerySet.add(item.trim());
            }
        }

        // Categorise grocery items
        Map<String, List<String>> categories = new LinkedHashMap<>();
        categories.put("Produce", new ArrayList<>());
        categories.put("Dairy", new ArrayList<>());
        categories.put("Protein", new ArrayList<>());
        categories.put("Grains", new ArrayList<>());
        categories.put("Others", new ArrayList<>());

        for (String item : grocerySet) {
            switch (item.toLowerCase()) {
                case "tomato": case "cucumber": case "lettuce": case "spinach":
                case "carrot": case "celery": case "onion": case "broccoli":
                case "banana": case "berries": case "garlic":
                    categories.get("Produce").add(item); break;
                case "milk": case "cheese":
                    categories.get("Dairy").add(item); break;
                case "eggs": case "chicken": case "ham":
                    categories.get("Protein").add(item); break;
                case "pasta": case "bread":
                    categories.get("Grains").add(item); break;
                default:
                    categories.get("Others").add(item); break;
            }
        }

        // Print grocery list by category
        System.out.println("\n🛒 Weekly Grocery List (Categorised):");
        for (Map.Entry<String, List<String>> entry : categories.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                System.out.println(entry.getKey() + ":");
                for (String item : entry.getValue()) {
                    System.out.println("- " + item);
                }
            }
        }

        // Summary
        System.out.println("\nTotal meals planned: " + weeklyPlan.size());
        System.out.println("Total unique grocery items: " + grocerySet.size());

        // Save grocery list to file (File I/O)
        try (PrintWriter writer = new PrintWriter(new File("grocery_list.txt"))) {
            writer.println("Weekly Grocery List:");
            for (String item : grocerySet) {
                writer.println("- " + item);
            }
            System.out.println("\n📂 Grocery list saved to grocery_list.txt");
        } catch (IOException e) {
            System.out.println("Error saving grocery list: " + e.getMessage());
        }

        sc.close();
    }
}




public final class WeeklyMealPlanner {

    // Method to display weekly plan
    static void displayPlan(String[] days, String[] meals) {
        System.out.println("\n Weekly Meal Plan:");
        for (int i = 0; i < days.length; i++) {
            System.out.println(days[i] + ": " + meals[i]);
        }
    }

    // Recursive method to count total meals planned
    static int countMeals(int n) {
        if (n <= 0) return 0;   // base case
        return 1 + countMeals(n - 1); // recursive case
    }

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        // Module 1: Primitive data types, arrays, console I/O
        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        String[] meals = new String[7];

        // Input meals using Scanner
        System.out.println("Enter your meal plan for the week:");
        for (int i = 0; i < days.length; i++) {
            System.out.print(days[i] + ": ");
            meals[i] = sc.nextLine();
        }

        // Module 2: Control flow (if-else, switch, loops)
        System.out.println("\nDo you want to view your plan or grocery list?");
        System.out.println("1. Weekly Plan\n2. Grocery List");
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        switch (choice) {
            case 1:
                displayPlan(days, meals);
                break;
            case 2:
                System.out.println("\nGrocery List (ingredients separated by commas):");
                for (String meal : meals) {
                    // simple loop to split ingredients
                    String[] items = meal.split(",");
                    for (String item : items) {
                        System.out.println("- " + item.trim());
                    }
                }
                break;
            default:
                System.out.println("Invalid choice!");
        }

        // Module 3: Recursion & arrays
        int totalMeals = countMeals(meals.length);
        System.out.println("\nTotal meals planned: " + totalMeals);

        sc.close();
    }
}

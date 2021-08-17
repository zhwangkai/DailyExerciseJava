package KFCSystem;

import java.util.ArrayList;
import java.util.List;

public class KFC {
    String[] names = {"Chips", "Chicken", "Beef", "Cook"};

    static final int Max = 20;
    List<Food> foods = new ArrayList<Food>();
    public void prod(int index) {
        synchronized (this) {
            while (foods.size() > Max) {
                System.out.println("Materials enough!");
                this.notifyAll();
                try {
                    String name = Thread.currentThread().getName();
                    this.wait();
                    System.out.println("Waiter: " + name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Start cooking...");
            for (int i = 0; i < index; i++) {
                Food food = new Food(names[(int)(Math.random() * 4)]);
                foods.add(food);
                System.out.println("Cooked" + food.getName() + foods.size());
            }
        }
    }

    public void consu(int index) {
        synchronized (this) {
            while(foods.size() < index) {
                System.out.println("Food is not enough!");
                this.notifyAll();
                try {
                    String name = Thread.currentThread().getName();
                    this.wait();
                    System.out.println("Customer: " + name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Consume start...");
            for (int i = 0; i < index; i++) {
                Food food = foods.remove(foods.size() - 1);
                System.out.println("Booked one" + food.getName() + foods.size());
            }
        }
    }
}

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Management {

    private  ArrayList<Food> foods;

    private ArrayList<Food> order_list;

    private ArrayList<String> discount_code_list;
    private ArrayList<String> discounts;

    public ArrayList<String> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(ArrayList<String> discounts) {
        this.discounts = discounts;
    }

    public ArrayList<String> getDiscount_code_list() {
        return discount_code_list;
    }

    public void setDiscount_code_list(ArrayList<String> discount_code_list) {
        this.discount_code_list = discount_code_list;
    }

    private double total_price;

    private double total_time;

    public double getTotal_time() {
        return total_time;
    }

    public void setTotal_time(double total_time) {
        this.total_time = total_time;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public ArrayList<Food> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(ArrayList<Food> order_list) {
        this.order_list = order_list;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public Management() {
        order_list = new ArrayList<>();
        foods = new ArrayList<>();
        discount_code_list = new ArrayList<>();
        discounts = new ArrayList<>();
    }

    public void menu() throws IOException {
        Scanner input = new Scanner(System.in);
        read_from_file();
        while (true) {
            print_menu();
            System.out.println("--------------------------------------------------------");
            System.out.println("1-order food");
            System.out.println("2-sort by price");
            System.out.println("3-exit");
            System.out.println("pleas enter your command");
            int command = input.nextInt();
            switch (command) {
                case 1:
                    order_food();
                    break;
                case 2:
                    sorting_foods();
                    break;
                case 3:
                    return;
            }
        }
    }

    public void order_food() throws IOException {
        Scanner input = new Scanner(System.in);
        while (true) {
            print_menu();
            System.out.println("--------------------------------------------------------");
            System.out.println("1-order");
            System.out.println("2-discount code");
            System.out.println("3-delivery");
            System.out.println("4-exit");
            System.out.println("please enter your command:");
            int command = input.nextInt();
            switch (command) {
                case 1:
                    order();
                    break;
                case 2:
                    discount();
                    break;
                case 3:
                    print_order_info();
                    delivery();
                    return;
                case 4:
                    return;
            }
        }
    }


    public void print_menu() throws IOException {
        for (int i = 0; i < foods.size(); i++) {
            System.out.println(i + "-" + foods.get(i));
        }
    }


    public void read_from_file() throws IOException {
        RandomAccessFile file = new RandomAccessFile("Menu.txt", "r");
        while (file.getFilePointer() != file.length()) {
            String[] meals = file.readLine().split(" - ");
            Food food = new Food();
            food.setName(meals[0]);
            food.setPrice(meals[1]);
            food.setTime(meals[2]);
            foods.add(food);
        }
    }

    public void sorting_foods() {
        ArrayList<Food> sorted_foods = new ArrayList<>();
        MinHeap<Food> minHeap = new MinHeap<>(foods);
        while (!minHeap.isEmpty())
            sorted_foods.add(minHeap.extractMin());
        foods = sorted_foods;
    }

    public void total_price(int price) {
        total_price += price;
    }

    public void total_time(int time) {
        total_time += time;
    }

    public void order() throws IOException {
        Scanner input = new Scanner(System.in);
        print_menu();
        System.out.println("--------------------------------------------------------");
        System.out.println("please choose your food:");
        int number = 0;
        while (true) {
            number = input.nextInt();
            if (number == -1)
                break;
            order_list.add(foods.get(number));
            total_price(foods.get(number).get_price());
            total_time(foods.get(number).get_time(foods.get(number).getTime()));

        }
        print_order_info();

    }

    public void print_order_info(){
        System.out.println("list of foods: " + order_list);
        System.out.println("--------------------------------------------------------");
        System.out.println("total price:" + total_price);
        System.out.println("total time:" + total_time);
        System.out.println("--------------------------------------------------------");

    }

    public void discount() throws IOException {
        Scanner input = new Scanner(System.in);
        RandomAccessFile file = new RandomAccessFile("Discount.txt" , "r");
        ArrayList <String> code = new ArrayList<>();
        while (file.getFilePointer() != file.length()){
            discounts.add(file.readLine());
        }

        HuffmanCoding huffmanCoding = new HuffmanCoding();
        huffmanCoding.HuffmanCodes(56);
        code = huffmanCoding.Code(discounts);
        for (int i = 0 ; i < code.size(); i++){
            System.out.println(code.get(i));
        }

        System.out.println("please enter your discount code :");
        String str = input.nextLine();
        if (code.contains(str)){
            total_price = total_price * 0.9;
            System.out.println("code accepted");
           print_order_info();
            System.out.println("please enter a number to continue");
            int num = input.nextInt();
        }else {
            System.out.println("invalid code");
        }

    }




    public void delivery(){
        Scanner input = new Scanner(System.in);
        Gragh gragh = new Gragh(8);
        gragh.addVertexData(0 , "A");
        gragh.addVertexData(1 , "G");
        gragh.addVertexData(2 , "B");
        gragh.addVertexData(3 , "F");
        gragh.addVertexData(4 , "H");
        gragh.addVertexData(5 , "C");
        gragh.addVertexData(6 , "D");
        gragh.addVertexData(7 , "E");

        gragh.addEdge(0 , 1 , 1);
        gragh.addEdge(1 , 2 , 1);
        gragh.addEdge(0 , 6 , 1);
        gragh.addEdge(2 , 7 , 1);
        gragh.addEdge(3 , 4 , 1);
        gragh.addEdge(4 , 5 , 1);
        gragh.addEdge(4 , 6 , 1);
        gragh.addEdge(6 , 7 , 1);
        gragh.addEdge(2 , 7 , 1);
        gragh.addEdge(2 , 5 , 1);

        System.out.println("please choose your destination :");
        System.out.println("G B F H C D E");
        String destination = input.nextLine();
        String result = gragh.dijkstra("A" , destination);
        System.out.println(result);

    }


}

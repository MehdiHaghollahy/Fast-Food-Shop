public class Food implements Comparable<Food> {

    private String name;

    private String price;

    private String time;

    public Food(String name, String price, String time) {
        this.name = name;
        this.price = price;
        this.time = time;
    }

    public Food(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return
                 name + '\'' +
                 price + '\'' +
                 time + '\'';
    }


    @Override
    public int compareTo(Food o) {
        return get_price() - o.get_price();
    }

    public Integer get_price(){
        return Integer.parseInt(price.substring(1));
    }

    public int get_time(String n){
         int time = Integer.parseInt(n.substring(0 , n.length() - 1));
         return time;
    }


}

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Account {

    Map<String , Integer> users = new HashMap<>();

    public Map<String, Integer> getUsers() {
        return users;
    }

    public void setUsers(Map<String, Integer> users) {
        this.users = users;
    }

    public Account(){

    }

    public void login() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("please enter your username:");
        String username = input.nextLine();
        System.out.println("please enter your password");
        String password = input.nextLine();
        if (users.containsValue(hash_code(password)) && users.containsKey(username)){
            Management management = new Management();
            management.menu();
        }else {
            System.out.println("wrong input");
        }

    }

    public void sign_up() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("please enter your username:");
        String username = input.nextLine();
        System.out.println("please enter your password:");
        String password = input.nextLine();
        if (users.containsKey(username) || users.containsValue(hash_code(password))){
            System.out.println("there is another one");
        }else {
            users.put(username , hash_code(password));
            System.out.println("done");
            System.out.println("please enter  0 to  back to main menu");
            int back = input.nextInt();
        }

    }

    public int hash_code(String password){
        int hash = 0;
        for (int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            hash += ((int)c * i) - i;
        }
        return hash;

    }
}

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
       Scanner input = new Scanner(System.in);
       Account account = new Account();


       while (true){
           System.out.println("welcome to fast food shop");
           System.out.println("1-Login");
           System.out.println("2-Sign up");
           System.out.println("3-Exit");
           int command = input.nextInt();
           switch (command){
               case 1:
                   account.login();
                   break;
               case 2:
                   account.sign_up();
                   break;
               case 3:
                   return;
           }
       }


    }
}
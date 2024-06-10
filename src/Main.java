import java.util.Scanner;

public class Main{
    public static void main(String[] args)
    {
        Scanner inp = new Scanner(System.in);
        System.out.print("Name : ");
        String name = inp.nextLine();
        System.out.print("Age : ");
        int age = inp.nextInt();
        System.out.println("Hello, " + name + "\nAge : " + age);
    }
}
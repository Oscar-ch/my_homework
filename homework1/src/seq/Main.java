package seq;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        System.out.println("请输入一串数字：");
        Scanner sc_one = new Scanner(System.in);
        ArrayList<Integer> a = new ArrayList<>();
        while (sc_one.hasNext()) {
            int temp;
            temp = sc_one.nextInt();
            a.add(temp);
        }
        new function().showAll(a);
        new function().insertSort(a);
        new function().showAll(a);

        System.out.println("请输入另一串数字：");
        Scanner sc_two = new Scanner(System.in);
        ArrayList<Integer> b = new ArrayList<>();
        while (sc_two.hasNext()) {
            int temp;
            temp = sc_two.nextInt();
            b.add(temp);
        }
        new function().add(a,b);
        new function().showAll(a);
        new function().insertSort(a);
        new function().showAll(a);
    }
}


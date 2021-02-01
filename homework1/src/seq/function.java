package seq;

import java.util.ArrayList;
import java.util.Iterator;

public class function {
    void insertSort(ArrayList<Integer> a) {
        for (int i = 1; i < a.size(); i++) {
            int j = i - 1;
            int temp = a.get(i);
            while (j >= 0 && a.get(j) > a.get(i)) {
                a.set(j + 1, a.get(j));
                j--;
            }
            a.set(j + 1, temp);
            showAll(a);

        }
    }
    void showAll(ArrayList<Integer> a) {
        System.out.println("排序后结果：");
        Iterator<Integer> it = a.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
        }
        System.out.println();
    }
    void add(ArrayList<Integer> aa,ArrayList<Integer> bb) {
        for (int i = 1; i < aa.size()+bb.size(); i++) {
           if(i >= aa.size())
                bb.set(i + 1, bb.get(aa.size()+bb.size()-i));
            }

        }
}
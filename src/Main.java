import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("请输入一串数字：");

        //不定长输入数
        int[] first = Functions.print();
        int[] result_1 = Functions.add(first,first);//合并
        first = Sort.sort(result_1);//排序
        //第一次输出
        System.out.println("排序后的结果：");
        for (int x : result_1)
            System.out.print(x+" ");

        System.out.println("\n请输入另一串数字：");

        //第二次输入
        int[] second = Functions.print();
        int[] result_2 = Functions.add(first,second);//合并
        result_2 = Sort.sort(result_2);//排序
        //第二次输出
        System.out.println("合并后的结果：");
        for (int x : result_2)
            System.out.print(x+" ");

    }
}

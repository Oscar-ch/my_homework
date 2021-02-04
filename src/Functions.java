import java.util.*;

public class Functions {
    //实现不定长数组输入
    public static int[] print() {
        Scanner reader = new Scanner(System.in);
        String str = reader.nextLine();
        int a[] = new int[str.length()];
        //将字符串以空格分隔，存储为字符串数组
        String s[] = str.split(" ");
        //遍历字符串数组，然后将其转化为整型
        for (int i = 0; i < s.length; i++)
            a[i] = Integer.parseInt(s[i]);
        return a;
    }

    public static int[] add(int[] first,int[] second) {
        //合并数组
        int[] three = new int[first.length + second.length];
        System.arraycopy(first, 0, three, 0, first.length);
        System.arraycopy(second, 0, three, first.length, second.length);
        //删除重复项
        Set<Integer> set = new TreeSet<Integer>();//新建一个set集合
        for (int i : three) {
            set.add(i);
        }
        Integer[] arr = set.toArray(new Integer[0]);
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }
}

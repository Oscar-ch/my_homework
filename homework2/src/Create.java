import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Create {
    String name;
    int live;
    int attack;
    int defence;
    int rate;


    public String name(){
        Scanner sn =new Scanner(System.in);
        AtomicReference<String> n = new AtomicReference<>(sn.nextLine());
        return n.get();
    }

    public int set(){
        Scanner sc =new Scanner(System.in);
        AtomicInteger number= new AtomicInteger(sc.nextInt());
        return number.get();
    }

    void inter(String name1){
        System.out.println("请输入"+name1+"名称：");
        name = name();
        System.out.println("请输入"+name1+"生命值：");
        live = set();
        System.out.println("请输入"+name1+"攻击力：");
        attack = set();
        System.out.println("请输入"+name1+"防御力：");
        defence = set();
        System.out.println("请输入"+name1+"速度：");
        rate = set();
    }
}

class Gamer extends Create{
    void inter(){
        super.inter("人物");
    }
}
class Monster extends Create{
    void inter(){
        super.inter("怪物");
    }
}
import java.util.*;

public class Fight {
    private int hp = 0;
    Random random1 = new Random();//gamer攻击成功概率
    Random random2 = new Random();//monster攻击成功概率

    public void fight() {
        Gamer gamer = new Gamer();
        gamer.inter();
        Monster monster = new Monster();
        monster.inter();

        System.out.println("战斗开始");
        int round;
        for (round = 1; ; round++) {
            if (random1.nextInt(monster.rate) <= gamer.rate) {
                int loselive1 = 0;
                if (gamer.attack - monster.defence > 0)
                    loselive1 = gamer.attack - monster.defence;
                else loselive1 = 0;

                if (loselive1 > 10)
                    System.out.println("嗷嗷嗷");

                System.out.println(monster.name + "受到" + loselive1 + "点伤害");

                hp = monster.live - loselive1;
                monster.live = hp;

                if (monster.live <= 0) {
                    System.out.println(monster.name + "： 可恶，到此为此了吗");
                    System.out.println(monster.name + "被杀死");

                    if (gamer.live >= 20)
                        System.out.println(gamer.name + ": 轻松胜利了呢");
                    else System.out.println(gamer.name + ": 呼，真是一场艰苦的战斗");

                    break;
                }
            } else System.out.println(gamer.name + "的攻击未命中");

            if (random2.nextInt(gamer.rate) <= monster.rate) {
                int loselive2 = 0;
                if (monster.attack - gamer.defence > 0)
                    loselive2 = monster.attack - gamer.defence;
                else loselive2 = 0;

                if (loselive2 > 10)
                    System.out.println("嗯唔");

                System.out.println(gamer.name + "受到" + loselive2 + "点伤害");

                hp = gamer.live - loselive2;
                gamer.live = hp;

                if (gamer.live <= 0) {
                    System.out.println(gamer.name + "： 技...止于...此...");
                    System.out.println(gamer.name + "战败了");

                    if (monster.live >= 20)
                        System.out.println(monster.name + ": 真是不自量力的小鬼");
                    else System.out.println(monster.name + ": 嚯嚯嚯，燃起来了");

                    break;
                }
            } else System.out.println(monster.name + "的攻击未命中");
            System.out.println(round + "回合结束");
            System.out.println();

            if(round == 100) {
                System.out.println("平局");
                break;
            }
        }
    }
}
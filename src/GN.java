import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GN {

    public static void main(String arg[]) {
        int a = 0;// 数字标记
        int b = 0;// 位置标记
        int time = 0;// 次数
        int rdn[] = new int[4];//随机数
        Random rd = new Random();

        do {
            rdn[0] = rd.nextInt(9);
            rdn[1] = rd.nextInt(9);
            rdn[2] = rd.nextInt(9);
            rdn[3] = rd.nextInt(9);// 生成四个随机数字。
            if (rdn[0] == 0 || rdn[0] == rdn[1] || rdn[0] == rdn[2] || rdn[0] == rdn[3] || rdn[1] == rdn[2] || rdn[1] == rdn[3] || rdn[2] == rdn[3]) {
                continue;
            } else {
                break;
            }
        } while (true);

        do {
            System.out.print("请输入您所猜的数字：");
            Scanner s = new Scanner(System.in);
            int n = -1;
            try {
                n = s.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("输入的不是数字,请重新输入.");
                continue;
            }
            String x = String.valueOf(n);

            if (n == 1) {//当输入1的时候查看答案
                // System.out.println(rdn[0] + "" + rdn[1] + "" + rdn[2] + "" + rdn[3]);
            }

            int num[] = new int[4];
            num[0] = n / 1000;
            num[1] = n / 100 % 10;
            num[2] = n % 100 / 10;
            num[3] = n % 10;// 处理键入的数字
            if (x.length() > 4 || x.length() <= 0) {
                System.out.println("输入的数字不符合要求，请重新输入。");
            } else if (num[0] == 0) {
                System.out.println("输入的数字不能以零开头，请重新输入。");
            } else if (x.length() == 4) {
                time++;
                a = 0;
                b = 0;
                for (int i = 0; i <= 3; i++) {
                    for (int j = 0; j <= 3; j++) {
                        if (num[i] == rdn[j])
                            if (i == j) {
                                b++;
                            } else {
                                a++;
                            }
                    }
                }
                if (b != 4) {
                    String log = "数字相同而位置不同有 " + a + " 个，位置和数字都相同有 " + b + " 个";
                    if (time > 5) {
                        log += " , 你个笨蛋，都猜" + time + "次了，还猜不对,继续加油哟!";
                    }
                    System.out.println(log);
                }
            }
            if (b == 4) {
                for (int m = 1; m <= 21; m++) {
                    for (int f = 1; f <= 22 - m; f++) {
                        System.out.print(" ");
                    }
                    for (int c = 1; c <= m; c++) {
                        System.out.print(" *");
                    }
                    System.out.println();
                }
                System.out.println("恭喜你，猜对了！一共猜了" + time + "次,答案是:。" + rdn[0] + "" + rdn[1] + "" + rdn[2] + "" + rdn[3]);
                break;
            }
        }
        while (true);
    }

}

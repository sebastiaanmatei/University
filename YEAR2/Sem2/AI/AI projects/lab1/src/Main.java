import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Main {
    public static void main(String[] args) {


        System.out.println("test start");
        //problema 1
        String textalf = "Ana are mere rosii si galbene";
        System.out.println("problema 1:");
        System.out.println("date:");
        System.out.print("1.Ana are mere rosii si galbene - ");
        //test
        System.out.println(Problems.getAlphab(textalf));
        System.out.print("2.este o zi cu soare - ");
        //test2
        System.out.println(Problems.getAlphab("este o zi cu soare"));
        System.out.println(" ");


        //problema 2 //rez

        System.out.println("problema 2:");
        System.out.println("date:");
        System.out.print("1. (1,5) si (4,1) - ");
        //test1
        System.out.println(Problems.getEuclidianDist(1, 5, 4, 1));
        System.out.print("2. (2,4) si (4,8) - ");
        //test2
        System.out.println(Problems.getEuclidianDist(2, 4, 4, 8));
        System.out.println(" ");


        //problema 3 //??

        System.out.println("problema 3:");
        System.out.println("date:");
        System.out.print("1. v1=[1,0,2,0,3], v2=[1,2,0,3,1] - ");
        List<Integer> v1 = new ArrayList<>();
        List<Integer> v2 = new ArrayList<>();
        v1.add(1);
        v1.add(0);
        v1.add(2);
        v1.add(0);
        v1.add(3);
        v2.add(1);
        v2.add(2);
        v2.add(0);
        v2.add(3);
        v2.add(1);
        //test1
        System.out.println(Problems.getProdVectors(v1, v2));
        System.out.println(" ");


        //problema 4 //rez

        System.out.println("problema 4:");
        String text = "ana are ana are mere rosii";
        System.out.println("date:");
        System.out.print("1. ana are ana are mere rosii - ");
        //test 1
        System.out.println(Problems.getSgProp(text));
        System.out.print("2. ana are ana are ana rosii - ");
        //test2
        System.out.println(Problems.getSgProp("ana are ana are ana rosii"));
        System.out.print("3. hello whats up - ");
        //test 3
        System.out.println(Problems.getSgProp("hello whats up"));
        System.out.println(" ");


        //problema 5 //rez

        List<Integer> test5 = new ArrayList<>();
        test5.add(1);
        test5.add(4);
        test5.add(3);
        test5.add(4);
        test5.add(5);
        test5.add(6);
        List<Integer> test52 = new ArrayList<>();
        test52.add(2);
        test52.add(1);
        test52.add(3);
        test52.add(4);
        test52.add(2);
        test52.add(6);
        System.out.println("problema 5: ");
        System.out.println("date:");
        System.out.print("1. v=[1,4,3,4,5,6] - ");
        //test1
        System.out.println(Problems.getRep(test5));
        System.out.print("2. v=[2,1,3,4,2,6] - ");
        //test2
        System.out.println(Problems.getRep(test52));
        System.out.println(" ");


        //problema 6 //rez

        List<Integer> test6 = new ArrayList<>();
        test6.add(2);
        test6.add(8);
        test6.add(7);
        test6.add(2);
        test6.add(2);
        test6.add(5);
        test6.add(2);
        test6.add(3);
        test6.add(1);
        test6.add(2);
        test6.add(2);
        List<Integer> test62 = new ArrayList<>();
        test62.add(8);
        test62.add(8);
        test62.add(7);
        test62.add(8);
        test62.add(2);
        test62.add(5);
        test62.add(2);
        test62.add(8);
        test62.add(1);
        test62.add(8);
        test62.add(8);
        System.out.println("problema 6:");
        System.out.println("date:");
        System.out.print("1. v=[2,8,7,2,2,5,2,3,1,2,2] - ");
        //test1
        System.out.println(Problems.getMajority(test6));
        System.out.print("2. v=[8,8,7,8,2,5,2,8,1,8,8] - ");
        //test2
        System.out.println(Problems.getMajority(test62));
        System.out.println(" ");


        //problema 7 //rez

        List<Integer> test7 = new ArrayList<>();
        test7.add(7);
        test7.add(4);
        test7.add(6);
        test7.add(3);
        test7.add(9);
        test7.add(1);
        List<Integer> test72 = new ArrayList<>();
        test72.add(1);
        test72.add(60);
        test72.add(59);
        test72.add(58);
        test72.add(23);
        System.out.println("problema 7:");
        System.out.println("date:");
        System.out.print("1. v=[7,4,6,3,9,1], k=2 - ");
        //test1
        System.out.println(Problems.getKGreatest(test7, 2));
        System.out.print("2. v=[1,60,59,58,23], k=3 - ");
        //test2
        System.out.println(Problems.getKGreatest(test72, 3));
        System.out.println(" ");


        //problema 8 //rez
        System.out.println("problema 8:");
        System.out.println("date:");
        System.out.println("nr 30 :");
        //test1
        System.out.println(Problems.genBase2(30));
        System.out.println("nr 16 :");
        //test2
        System.out.println(Problems.genBase2(16));
        System.out.println("nr 256 :");
        //test3
        System.out.println(Problems.genBase2(256));
        System.out.println(" ");


        //problema 9 //rez
        Map<Integer, List<Integer>> indexes = new HashMap<>();
        List<Integer> list = Arrays.asList(1, 1, 2, 2);
        indexes.put(1, list);
        List<Integer> list2 = Arrays.asList(1, 3, 2, 4);
        indexes.put(2, list2);
        List<Integer> list3 = Arrays.asList(3, 4, 4, 5);
        indexes.put(3, list3);
        List<Integer> list4 = Arrays.asList(1, 2, 2, 5);
        indexes.put(4, list4);
        System.out.println("problema 9: ");
        System.out.println("date:");
        int[][] mat = new int[1000][1000];
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 5; j++) {
                mat[i][j] = i + j;
                System.out.print(mat[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("seturi de indici: ((1,1)(2,2)),((1,3)(2,4)),((3,4)(4,5)),((1,2)(2,5))");
        System.out.println("raspuns:");
        //test1
        System.out.println(Problems.getSums(mat, 4, 5, indexes));
        System.out.println(" ");


        //problema 10
        int[][] multi = {
                {0, 0, 1, 1, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };
        System.out.println("problema 10:");
        System.out.println("ex:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(multi[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("raspuns:");
        //test1
        System.out.println(Problems.max1(multi, 5, 5));
        System.out.println("test end");







    }

}
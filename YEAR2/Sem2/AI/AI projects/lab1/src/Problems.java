import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class Problems {

    static int[] frequency = new int[100000];
    static int[] frequency2 = new int[100000];


    //problema 1 - ultimul cuv dpdv alfabetic
    public static String getAlphab(String tx){
        String[] text = tx.split("\\s+");
        String last=text[0];
        for (int i = 1; i < text.length; i++) {
            if (last.compareTo(text[i]) < 0) {
                last=text[i];
            }
        }
        return last;
    }



    //problema 2 - distanta euclidiana
    public static Double getEuclidianDist(int a1, int b1, int a2, int b2) {
        return sqrt(pow(a1 - a2, 2) + pow(b1 - b2, 2));
    }

    //problema 3 - produs scalar vectori
    public static Float getProdVectors(List<Integer> l1, List<Integer> l2) {
        float sum = 0;
        for (int i = 0; i < l1.size(); i++) {
            sum += l1.get(i) * l2.get(i);
        }
        return sum;
    }


    //problema 4 - cuv care apar o sg data
    public static List<String> getSgProp(String sentence) {

        List<String> finalList = new ArrayList<>();
        String[] words = sentence.split("\\s+");

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].compareTo(words[j]) > 0) {
                    String temp = words[i];
                    words[i] = words[j];
                    words[j] = temp;
                }
            }
        }
        //Arrays.sort(words);
        for (int i = 0; i < words.length; i++) {
            if (i == 0 && !words[1].equals(words[0])) {
                finalList.add(words[i]);

            } else if (i == words.length - 1 && !words[words.length - 1].equals(words[words.length - 2])) {
                finalList.add(words[words.length - 1]);


            } else if (i >= 1 && i < words.length - 1) {
                if (!words[i - 1].equals(words[i]) && !words[i].equals(words[i + 1])) {
                    finalList.add(words[i]);
                }
            }
        }
        return finalList;
    }


    //problema 5 - numarul care apare de 2 ori
    public static Integer getRep(List<Integer> lst) {
        for(int i=0;i<lst.size();i++){
            frequency[lst.get(i)]=0;
        }
        for (int i = 0; i < lst.size(); i++) {
            if (frequency[lst.get(i)] == 1) {
                return lst.get(i);
            }
            frequency[lst.get(i)]++;

        }
        return lst.get(lst.size() - 1);
    }

    //problema 6 - numarul care apare de n/2 ori
    public static Integer getMajority(List<Integer> lst) {
        for(int i=0;i<lst.size();i++){
            frequency2[lst.get(i)]=0;
        }
        for (int i = 0; i < lst.size(); i++) {
            if (frequency2[lst.get(i)] >= lst.size() / 2) {
                return lst.get(i);
            }
            frequency2[lst.get(i)]++;

        }
        return -1;
    }

    //problema 7 - al k lea cel mai mare
    public static Integer getKGreatest(List<Integer> lst, int k) {
        for (int i = 0; i < lst.size(); i++) {
            for (int j = i + 1; j < lst.size(); j++) {
                if (lst.get(i).compareTo(lst.get(j)) < 0) {
                    Integer temp = lst.get(i);
                    lst.set(i, lst.get(j));
                    lst.set(j, temp);
                }
            }
        }
        return lst.get(k - 1);

    }

    //problema 8 - transformare in binar 1-n
    public static List<Integer> genBase2(int n) {
        List<Integer> ls = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int num = i;
            int number = 0;
            int pow=1;
            while (num>0){
                number+=pow*(num%2);
                num/=2;
                pow*=10;
            }
            ls.add(number);
        }
        return ls;

    }

    //problema 9 - sume submatrici
    public static List<Integer> getSums(int[][] v, int n, int m, Map<Integer, List<Integer>> indexes) {
        List<Integer> sums = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                v[i][j] += v[i - 1][j] + v[i][j - 1] - v[i - 1][j - 1];
            }
        }
        var ind = indexes.values().stream().toArray();

        for (int i = 0; i < indexes.size(); i++) {
            List<Integer> values = (List<Integer>) ind[i];
            int x1 = (int) values.toArray()[0];
            int y1 = (int) values.toArray()[1];
            int x2 = (int) values.toArray()[2];
            int y2 = (int) values.toArray()[3];

            int sum = v[x2][y2] - v[x1 - 1][y2] - v[x2][y1 - 1] + v[x1 - 1][y1 - 1];
            sums.add(sum);

        }
        return sums;

    }

    //problema 10 - linia cu cei mai multi 1
    public static Integer max1(int[][] v, int n, int m) {
        int index = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;

            for (int j = 0; j < m; j++) {
                sum += v[i][j];
            }
            if (max < sum) {
                index = i;
                max = sum;
            }
        }
        return index + 1;
    }


}

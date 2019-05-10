package lambad;


import java.util.Arrays;

import java.util.List;

public class LambdaLearn {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("cdadsfs","dsafaa","gdasffs");
        Cheaker cheaker = (a)->a.substring(0,1).equals("d");
        cheak(list,cheaker);
    }
    public static void cheak(List<String> list,Cheaker cheaker){
        for(String str:list){
            cheaker.cheaker(str);
            if(cheaker.cheaker(str))
                System.out.println(str);
        }
    }
}

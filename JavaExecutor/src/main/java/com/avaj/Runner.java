package com.avaj;

import java.util.ArrayList;
import java.util.Arrays;

public class Runner {


    private static int jiecheng(int i){
        if(i==1){
            return i;
        }else {
            return i*jiecheng(i-1);
        }
    }

    public static void main(String[] args) {

        Object mO=new Object();

        mO.hashCode();

        System.out.println("the 5! ="+jiecheng(5));

        String a = "abc";
        String b = "abc";
        String c=a;
        String d=b;

        System.out.println("a==b " + a == b);
        System.out.println("a.equals(b) " + a.equals(b));
        System.out.println("c==d"+c==d);
        System.out.println("c.equals(d)="+c.equals(d));
        int count = 0;
        ArrayList<Integer> datas = new ArrayList<>();
        while (count < 100) {
            int i = (int) (Math.random() * 100);
            if (!datas.contains(i)) {
                datas.add(i);
                count++;
            }

        }
        Object[] array = datas.toArray();
        System.out.println("now the datas = " + Arrays.toString(array));

        for (int i = 1; i < array.length; i++) {
            int temp = (int) array[i];
            int j = i - 1;
            for (; j >= 0 && temp < (int) array[j]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }

        for(int i=0;i<datas.size();i++){
            if(datas.get(i)==10){
                datas.remove(i);
            }
        }





        System.out.println("now the datas = " + Arrays.toString(array));



        for(Color mColor:Color.values()){
            System.out.println(mColor);
        }
    }


}

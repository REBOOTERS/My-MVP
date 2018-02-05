package com.avaj.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * author : engineer
 * e-mail : yingkongshi11@gmail.com
 * time   : 2018/2/5
 * desc   :
 * version: 1.0
 */
public class PlayWithList {
    public static void main(String[] args) {
        List<Integer> mList = new ArrayList<>();
        genRandom(mList);


        System.out.println("origin data=");
        System.out.print("[");
        for (Integer data : mList) {
            System.out.print(" " + data);
        }
        System.out.print(" ]");

        selectSort(mList);

        System.out.println("sorted data=");
        System.out.print("[");
        for (Integer data : mList) {
            System.out.print(" " + data);
        }
        System.out.print(" ]");

        test();

    }


    private static void selectSort(List<Integer> list){
        for(int i=0;i<list.size()-1;i++){
            int k=i;
            for(int j=i+1;j<list.size();j++){
                if(list.get(k)>list.get(j)){
                    k=j;
                }
            }
            if(k!=i){
                int temp=list.get(k);
                list.set(k,list.get(i));
                list.set(i, temp);
            }
        }
    }

    private static void genRandom(List<Integer> list) {
        int count=0;
        int flag=-1;
        while (count < 100) {
            int num= (int) (Math.random()*100);
            if (!list.contains(num)) {
                list.add(num * flag);
                flag=flag*-1;
                count++;
            }
        }
    }

    private static void test(){

        List<Integer> data=new ArrayList<Integer>();
        List<List<Integer>> result=new ArrayList<>();

        int[] numbers={1,0,-1,-1,-1,-1,0,1,1,1};
        for(int i=0;i<numbers.length-2;i++){
            for(int j=i+1;j<numbers.length-1;j++){
                for(int k=j+1;k<numbers.length;k++){
                    if(numbers[i]+numbers[j]+numbers[k]==0){
                        data.add(numbers[i]);
                        data.add(numbers[j]);
                        data.add(numbers[k]);
                        result.add(data);
                        break;
                    }
                }
            }
        }


        System.out.println("\nsize=="+result.size());
    }
}

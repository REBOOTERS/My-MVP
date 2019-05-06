package com.avaj.generic;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: zhuyongging
 * @since: 2019-05-03
 */
public class Client {
    public static void main(String[] args) {
        A a = new A();
        a = new B();
        C c = new C();
        c = new D();

        List<A> alist = new ArrayList<>();
        List<B> blist = new ArrayList<>();

        boolean result = alist.getClass() == blist.getClass();

        alist = blist;

        List<? extends A> a_sub_list = new ArrayList<>();
        a_sub_list = alist;
        alist = (List<A>) a_sub_list;

        List<? super A> a_super_list = new ArrayList<>();
        a_super_list = alist;
        alist = a_super_list;


        List<? extends A> list = new ArrayList<>();

        A object = list.get(0);

        list.add(new A());
        list.add(new B());
        list.add(new C());
        list.add(new D());


        List<?> anys = new ArrayList<A>();
        Object o = anys.get(0);
        anys.add(new A());

        List<Object> objects = new ArrayList<>();
        objects.add(new A());

        List<A> listA = new ArrayList<>();

        A a = listA.get(0);
        listA.add(new A());
        listA.add(new B());
        listA.add(new C());
        listA.add(new D());

        List<? super A> listAA = new ArrayList<>();

        A aa = listAA.get(0);

        listAA.add(new A());
        listAA.add(new B());
        listAA.add(new C());
        listAA.add(new D());

    }
}

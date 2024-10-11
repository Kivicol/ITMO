package GUI;

import data.Route;

import java.util.LinkedList;

public class SetComparator {
    public static boolean compare(LinkedList<Route> a, LinkedList<Route> b) {
        if (a.size() != b.size()) return false;
        boolean flag = false;
        for (Route m1 : b) {
            boolean check = false;
            for (Route m2 : a) {
                if (m1.equals(m2)){
                    check = true;
                    flag = true;
                    break;
                }
            }
            if (!check) flag = false;
        }
        for (Route m1 : a){
            boolean check = false;
            for (Route m2 : b){
                if (m2.equals(m1)){
                    check = true;
                    flag = true;
                    break;
                }
            }
            if (!check) flag = false;
        }
        return flag;
    }
}

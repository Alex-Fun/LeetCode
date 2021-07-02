package demo;

import java.util.*;
import java.util.stream.Collectors;

public class MyArrayList<Q> {

//    private String get(){
////        System.arraycopy();
//        return Q.class;
//    }
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList();
        words.add("Hello");
        words.add("World");
        List<String> collect = words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);

        LinkedList a;
    }
}

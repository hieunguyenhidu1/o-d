import java.util.*;

public class Main {
    public static void main(String[] args) {
        int N = 10_000_000;

        // ArrayList
        List<Integer> arrayList = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) arrayList.add(i);
        System.out.println("ArrayList add: " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        arrayList.contains(N - 1);
        System.out.println("ArrayList access (contains): " + (System.currentTimeMillis() - start) + " ms");

        // HashSet
        Set<Integer> hashSet = new HashSet<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) hashSet.add(i);
        System.out.println("HashSet add: " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        hashSet.contains(N - 1);
        System.out.println("HashSet access (contains): " + (System.currentTimeMillis() - start) + " ms");

        // HashMap
        Map<Integer, Integer> hashMap = new HashMap<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) hashMap.put(i, i);
        System.out.println("HashMap put: " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        hashMap.get(N - 1);
        System.out.println("HashMap access (get): " + (System.currentTimeMillis() - start) + " ms");
    }
}
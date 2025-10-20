package com.myorm;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {3,1,5,8};
        Arrays.sort(arr, (Integer a, Integer b) -> b - a);

        for (int val : arr) {
            System.out.print(val + " ");
        }
    }
}
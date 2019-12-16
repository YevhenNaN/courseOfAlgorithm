package com.company;

public class Main {
    public static void main(String[] args) {
        int[] dataArray = {8, 5, 0, 3, 1, 4, 4, 2, 5, 0, 5, 4, 5, 1, 1, 2, 3, 1};
        WidthAlgorithm algorithm = new WidthAlgorithm();
        algorithm.search(dataArray);
        System.out.println(algorithm.result());
    }
}
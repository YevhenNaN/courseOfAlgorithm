package com.company;
public class Main {

    public static void main(String[] args) {
        char[] arrayOfCharsOfMaze = {'a', 'a', 'a', 'c', 'a', 'b', 'd', 'e', 'f'};
        int widthOfMaze = 3;
        int heightOfMaze = 3;
        AlgorithmForIndianaJones algorithm = new AlgorithmForIndianaJones();
        algorithm.searchForAllPossibleExitsFromTheMaze(arrayOfCharsOfMaze, widthOfMaze, heightOfMaze);
        System.out.println(algorithm.getNumberOfWaysToExit());
    }
}
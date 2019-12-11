package com.company;

import java.util.*;

class AlgorithmForIndianaJones {
    private int numberOfWaysToExit = 0;

    void searchForAllPossibleExitsFromTheMaze(char[] arrayOfCharsOfMaze, int widthOfMaze, int heightOfMaze) {
        if (widthOfMaze == 1)  {
            numberOfWaysToExit = 1;
            return;
        }

        ArrayList<Character> listOfCharsOfMazeBoxes = new ArrayList<>();
        for (char rowOfIndexes : arrayOfCharsOfMaze) {
            listOfCharsOfMazeBoxes.add(rowOfIndexes);
        }

        for (int rowOfIndexes = 0; rowOfIndexes < listOfCharsOfMazeBoxes.size(); rowOfIndexes += heightOfMaze) {
            checkOfRightUpperFinish(listOfCharsOfMazeBoxes, rowOfIndexes, heightOfMaze);
            checkOfRightDownFinish(listOfCharsOfMazeBoxes, rowOfIndexes, heightOfMaze);
        }
    }

    private void checkOfRightUpperFinish(ArrayList<Character> listOfCharsOfMazeBoxes, int startPosition, int heightOfMaze) {
        for (int rowOfIndexes = heightOfMaze - 1 + heightOfMaze; rowOfIndexes <= listOfCharsOfMazeBoxes.size() - 1; rowOfIndexes += heightOfMaze) {
            if (startPosition == rowOfIndexes) { // Yellow ~~
                return;
            }
        }
        if (startPosition < heightOfMaze) {
            numberOfWaysToExit++;
        } else {
            if (listOfCharsOfMazeBoxes.get(startPosition) == listOfCharsOfMazeBoxes.get(startPosition - heightOfMaze + 1)) {
                checkOfRightUpperFinish(listOfCharsOfMazeBoxes, startPosition - heightOfMaze + 1, heightOfMaze);
            } else {
                checkOfRightUpperFinish(listOfCharsOfMazeBoxes, startPosition + 1, heightOfMaze);
            }
        }
    }

    private void checkOfRightDownFinish(ArrayList<Character> listOfCharsOfMazeBoxes, int startPosition, int heightOfMaze) {
        for (int rowOfIndexes = heightOfMaze - 1; rowOfIndexes <= listOfCharsOfMazeBoxes.size() - heightOfMaze - 1; rowOfIndexes += heightOfMaze)
            if (startPosition == rowOfIndexes) { // Yellow ~~
                return;
            }
        if (startPosition >= listOfCharsOfMazeBoxes.size() - heightOfMaze) {
            numberOfWaysToExit++;
        } else {
            if (listOfCharsOfMazeBoxes.get(startPosition) == listOfCharsOfMazeBoxes.get(startPosition + heightOfMaze + 1)) {
                checkOfRightDownFinish(listOfCharsOfMazeBoxes, startPosition + heightOfMaze + 1, heightOfMaze);
            } else {
                    checkOfRightDownFinish(listOfCharsOfMazeBoxes, startPosition + 1, heightOfMaze);
            }
        }
    }

    public int getNumberOfWaysToExit() {
        return numberOfWaysToExit;
    }
}

package com.company;

import java.util.ArrayList;
import java.util.List;
class WidthAlgorithm {
    private static int score = 0;
    void search(int[] dataArray) {
        List<Integer> dataList = new ArrayList<>();
        for (int i : dataArray) {
            dataList.add(i);
        }
        System.out.println(dataList);
        int numberOfEdges = dataList.get(0);
        dataList.remove(0);
        int startOfTheReference = dataList.get(0);
        dataList.remove(0);
        List<Integer> startList = new ArrayList<>();
        List<Integer> finishList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            if (i % 2 == 0) {
                startList.add(dataList.get(i));
            } else {
                finishList.add(dataList.get(i));
            }
        }
        System.out.println(startList);
        System.out.println(finishList);
        check(startList, finishList, startOfTheReference);

    }

    private void check(List<Integer> startList, List<Integer> finishList, int startOfTheReference) {
        List<Integer> turnList = new ArrayList<>();
        List<Integer> checkList = new ArrayList<>();
        for (int i = 0; i < startList.size(); i++) {
            if (startList.get(i) == startOfTheReference) {
                turnList.add(finishList.get(i));
            }
        }
        for(int i = 0; i < startList.size(); i++) {
            if(startList.get(i) == startOfTheReference){
                startList.set(i, -1);
            }
        }
        System.out.println(turnList);
        System.out.println("---------");
        while (true) {
            for (int i = 0; i < finishList.size(); i++) {
                for (int j = 0; j < turnList.size(); j++) {
                    if (finishList.get(i) == turnList.get(j)) {
                        checkList.add(i);
                    }
                }
            }

            for (int i : checkList) {
                finishList.set(i, -1);
            }
            for (int i : checkList) {
                startList.set(i, -1);
            }
            checkList.removeAll(checkList);

            score++;
            for (int i = 0; i < startList.size(); i++) {
                for (int j = 0; j < turnList.size(); j++) {
                    if (startList.get(i) == turnList.get(j)) {
                        checkList.add(i);
                    }
                }
            }

            System.out.println(startList);
            System.out.println(finishList);
            turnList.removeAll(turnList);
            for (int i = 0; i < checkList.size(); i++) {
                for (int j = 0; j < finishList.size(); j++) {
                    if (checkList.get(i) == j) {
                        turnList.add(finishList.get(j));
                    }
                }
            }

            System.out.println(turnList);
            if(turnList.size() == 0){
                System.out.println("------------");
                break;
            }
            System.out.println("-----------");
        }
    }

    int result(){
        return score;
    }
}
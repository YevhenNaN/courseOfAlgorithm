package com.company;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


class Connection {
    public int Node1;
    public int Node2;
    public int ConnectionTime;

    public Connection(int a, int b, int c) {
        Node1 = a;
        Node2 = b;
        ConnectionTime = c;
    }
}


public class Main {

    public static int[] getData(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int[] dataArray = new int[150];
        int i = 0;
        while (scanner.hasNextInt()) {
            dataArray[i] = scanner.nextInt();
            i++;
        }
        return dataArray;
    }

    public static int sumOfMaxPing = 0;

    //*************************************************************************************
    public static int findConnectionSum(int clientNode, ArrayList<Connection> connections, int amountOfNodes, int amountOfconnections, int serverPlace) {
        if (clientNode != serverPlace) {
            boolean isFirstToServer = false;

            int nextnode = 0;
            int minimaltime = 100000;

            for (int i = 0; i < amountOfconnections; ++i) {
                if (connections.get(i).Node1 == clientNode || connections.get(i).Node2 == clientNode) {
                    if (connections.get(i).Node1 == serverPlace || connections.get(i).Node2 == serverPlace) {
                        isFirstToServer = true;

                        sumOfMaxPing = sumOfMaxPing + connections.get(i).ConnectionTime;
                        return sumOfMaxPing;

                    } else {

                        boolean help = false;
                        if (connections.get(i).Node1 == clientNode) {

                            for (int k = 0; k < amountOfconnections; ++k) {

                                if (k != i) {
                                    if (connections.get(k).Node1 == connections.get(i).Node2 || connections.get(k).Node2 == connections.get(i).Node2) {
                                        if (minimaltime > connections.get(k).ConnectionTime) {
                                            help = true;
                                            minimaltime = connections.get(k).ConnectionTime;
                                        }
                                    }
                                }
                            }
                            if (connections.get(i).Node1 == clientNode)
                                nextnode = connections.get(i).Node2;

                        } else {
                            if (help = true)
                                if (connections.get(i).Node2 == clientNode)
                                    nextnode = connections.get(i).Node1;


                        }


                    }

                }
            }
            for (int i = 0; i < amountOfconnections; ++i) {
                if (connections.get(i).Node1 == nextnode && connections.get(i).Node2 == clientNode) {
                    sumOfMaxPing += connections.get(i).ConnectionTime;
                } else if (connections.get(i).Node2 == nextnode && connections.get(i).Node1 == clientNode) {
                    sumOfMaxPing += connections.get(i).ConnectionTime;
                }
            }

            if (nextnode != serverPlace)
                findConnectionSum(nextnode, connections, amountOfNodes, amountOfconnections, serverPlace);
        }

        return sumOfMaxPing;
    }


    public static void main(String[] args) throws FileNotFoundException {
        int amountOfNodes;
        int amountOfconnections;

        File file = new File("/home/lenovo/Завантаження/gamsrv.txt");
        int[] Array = getData(file);

        int AmountOfInts = 0;

        for (int i = 0; i < Array.length; ++i) {
            if (Array[i] == 0) {
                AmountOfInts = i;
                break;
            }
            //System.out.println(" "+ Array[i]);
        }
        amountOfNodes = Array[0];
        amountOfconnections = Array[1];
        System.out.println("Amount of nodes:" + amountOfNodes);
        System.out.println("Amount of Connections:" + amountOfconnections);

        int[] clientNodes = new int[3];
        clientNodes[0] = Array[2];
        clientNodes[1] = Array[3];
        clientNodes[2] = Array[4];


        ArrayList<Connection> Connections = new ArrayList<Connection>();

        for (int i = 5; i < AmountOfInts; i += 3) {
            Connection a = new Connection(Array[i], Array[i + 1], Array[i + 2]);
            Connections.add(a);
        }

        for (int i = 0; i < 3; ++i) {
            System.out.println("clientNode:" + clientNodes[i]);
        }
        for (int i = 0; i < Connections.size(); ++i) {
            System.out.println(Connections.get(i).Node1 + " " + Connections.get(i).Node2 + " " + Connections.get(i).ConnectionTime);
        }


        int usermax = 0;
        int max = 0;

        for (int j = 1; j <= amountOfNodes; ++j) {
            for (int i = 0; i < 3; ++i) {
                if (j != clientNodes[i]) {
                    FindConnectionSum(clientNodes[i], Connections, amountOfNodes, amountOfconnections, j);

                    if (sumOfMaxPing > usermax) usermax = sumOfMaxPing;

                    sumOfMaxPing = 0;
                }

            }
            if (max < usermax) {
                max = usermax;
            }
        }

        System.out.println("max:" + max);

    }

}

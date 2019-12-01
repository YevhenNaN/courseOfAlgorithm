package com.company;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


class Connection{
    public int Node1;
    public int Node2;
    public int ConnectionTime;

    public Connection(int a, int b, int c){
        Node1=a;
        Node2=b;
        ConnectionTime=c;
    }
}



public class Main {

    public static int[] getData(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        int[] dataArray = new int[150];
        int i = 0;
        while(scan.hasNextInt()) {
            dataArray[i] = scan.nextInt();
            i++;
        }
        return dataArray;
    }
    public static int sum=0;
    //*************************************************************************************
    public static int FindConnectionSum(int ClientNode, ArrayList<Connection> Connections, int AmountOfNodes, int AmountOfConnections, int serverplace) {
        if (ClientNode != serverplace) {
            boolean isFirstToServer = false;

            int nextnode = 0;
            int minimaltime = 100000;

            for (int i = 0; i < AmountOfConnections; ++i) {
                if (Connections.get(i).Node1 == ClientNode || Connections.get(i).Node2 == ClientNode) {
                    if (Connections.get(i).Node1 == serverplace || Connections.get(i).Node2 == serverplace) {
                        isFirstToServer = true;

                        sum =sum+ Connections.get(i).ConnectionTime;
                        return sum;

                    } else {

                        boolean help = false;
                        if (Connections.get(i).Node1 == ClientNode) {

                            for (int k = 0; k < AmountOfConnections; ++k) {

                                if (k != i) {
                                    if (Connections.get(k).Node1 == Connections.get(i).Node2 || Connections.get(k).Node2 == Connections.get(i).Node2) {
                                        if (minimaltime > Connections.get(k).ConnectionTime) {
                                            help = true;
                                            minimaltime = Connections.get(k).ConnectionTime;
                                        }
                                    }
                                }
                            }
                            if (help = true)
                                if (Connections.get(i).Node1 == ClientNode)
                                    nextnode = Connections.get(i).Node2;

                        } else {
                            if (help = true)
                                if (Connections.get(i).Node2 == ClientNode)
                                    nextnode = Connections.get(i).Node1;


                        }


                    }

                }
            }
            for (int i = 0; i < AmountOfConnections; ++i) {
                if (Connections.get(i).Node1 == nextnode && Connections.get(i).Node2 == ClientNode) {
                    sum += Connections.get(i).ConnectionTime;}
                else if(Connections.get(i).Node2 == nextnode && Connections.get(i).Node1 == ClientNode){
                    sum += Connections.get(i).ConnectionTime;}
            }

            if (nextnode != serverplace)
                FindConnectionSum(nextnode, Connections, AmountOfNodes, AmountOfConnections, serverplace);
        }

        return sum;
    }


    public static void main(String[] args) throws FileNotFoundException {
        int AmountOfNodes;
        int AmountOfConnections;

        File file = new File("/home/lenovo/Завантаження/gamsrv.txt");
        int[] Array = getData(file);

        int AmountOfInts=0;

        for(int i=0; i<Array.length;++i){
            if(Array[i]==0){
                AmountOfInts=i;
                break;
            }
            //System.out.println(" "+ Array[i]);
        }
        AmountOfNodes=Array[0];
        AmountOfConnections=Array[1];
        System.out.println("Amount of nodes:"+AmountOfNodes);
        System.out.println("Amount of Connections:"+AmountOfConnections);

        int[] ClientNodes= new int[3];
        ClientNodes[0]=Array[2];
        ClientNodes[1]=Array[3];
        ClientNodes[2]=Array[4];


        ArrayList<Connection> Connections = new ArrayList<Connection>();

        for(int i=5;i<AmountOfInts;i+=3){
            Connection a=new Connection(Array[i],Array[i+1],Array[i+2]);
            Connections.add(a);
        }

        for(int i=0; i<3;++i) {
            System.out.println("ClientNode:"+ClientNodes[i]);
        }
        for(int i=0; i<Connections.size();++i) {
            System.out.println(Connections.get(i).Node1 + " " + Connections.get(i).Node2 + " " + Connections.get(i).ConnectionTime);
        }



        int usermax=0; int max=0;

        for (int j = 1; j <= AmountOfNodes; ++j) {
            for(int i=0; i<3;++i) {
                if(j!=ClientNodes[i]) {
                    FindConnectionSum(ClientNodes[i], Connections, AmountOfNodes, AmountOfConnections, j);

                    if(sum>usermax)usermax=sum;

                    sum = 0;
                }

            }
            if(max<usermax){max=usermax;}
        }

        System.out.println("max:"+max);

    }

}

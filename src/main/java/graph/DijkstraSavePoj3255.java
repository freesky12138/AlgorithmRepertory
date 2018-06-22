package graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:graph
 * @description
 * @date 2018/6/22 15:50
 */
public class Main {


    /*
    4 4
     1 2 100
     2 4 200
     2 3 250
     3 4 100
     */


    public static void main(String[] args) {
        int noteSize;
        int vSize;
        Scanner scanner = new Scanner(System.in);
        noteSize = scanner.nextInt();
        vSize = scanner.nextInt();
        Edge[] edges = new Edge[vSize];
        int[] sortest = new int[noteSize];
        int[] sortest2 = new int[noteSize];
        Arrays.fill(sortest, Integer.MAX_VALUE );
        Arrays.fill(sortest2, Integer.MAX_VALUE );
        Comparator<Note> cmp;
        cmp = new Comparator<Note>() {
            public int compare(Note o1, Note o2) {
                return o1.v > o2.v ? 1 : -1;
            }
        };
        PriorityQueue<Note> priorityQueue = new PriorityQueue<Note>(noteSize,cmp);

        for (int i = 0; i < vSize; i++) {
            edges[i] = new Edge(scanner.nextInt() - 1, scanner.nextInt() - 1, scanner.nextInt());
        }
        priorityQueue.add(new Note(0, 0));
        sortest[0] = 0;
        sortest2[0] = 0;
        while (true) {
            Note t = priorityQueue.poll();
            if (t == null)
                break;

            for (int i = 0; i < vSize; i++) {
                int d2 = sortest[edges[i].from] + edges[i].cost;
                if (sortest[edges[i].to] > d2) {
                    int temp = sortest[edges[i].to];
                    sortest[edges[i].to] = d2;
                    d2 = temp;
                    priorityQueue.add(new Note(edges[i].to, sortest[edges[i].to]));
                }

                if (sortest2[edges[i].to] > d2 && d2 > sortest[edges[i].to]) {
                    sortest2[edges[i].to] = d2;
                    priorityQueue.add(new Note(edges[i].to, sortest2[edges[i].to]));
                }
            }
        }
        System.out.printf(sortest2[noteSize - 1] + "");
    }

    public static class Note {
        private int n;
        private int v;

        public Note(int n, int v) {
            this.n = n;
            this.v = v;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }
    }

    static class Edge {
        private int from;
        private int to;
        private int cost;


        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;

        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

    }
}

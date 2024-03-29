package algo0724;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ_10282_해킹 {
    private static int T,N,D,C,cnt,time; 
    private static int[] distances;
    private static List<Edge>[] edgeList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            N = sc.nextInt();
            D = sc.nextInt();
            C = sc.nextInt();
            distances = new int[N + 1];
            edgeList = new ArrayList[N + 1];
            Arrays.fill(distances, Integer.MAX_VALUE);
            for (int i = 0; i <= N; i++) {
                edgeList[i] = new ArrayList<>();
            }
            for (int i = 0; i < D; i++) {
                int a = sc.nextInt(); 
                int b = sc.nextInt(); 
                int s = sc.nextInt(); 
                edgeList[b].add(new Edge(a, s)); 
            }
            distances[C] = 0;
            dijkstra();
            cnt = 0;
            time = 0;
            for (int i = 1; i <= N; i++) {
                if (distances[i] != Integer.MAX_VALUE) {
                    cnt++;
                    time = Math.max(time, distances[i]);
                }
            }
            System.out.println(cnt + " " + time);
        }
    }

    private static void dijkstra() {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(C, 0));
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int vertex = edge.vertex;
            int cost = edge.cost;
            if (distances[vertex] < cost) {
                continue;
            }
            for (int i = 0; i < edgeList[vertex].size(); i++) {
                int vertex2 = edgeList[vertex].get(i).vertex;
                int cost2 = edgeList[vertex].get(i).cost + cost;
                if (distances[vertex2] > cost2) {
                    distances[vertex2] = cost2;
                    queue.add(new Edge(vertex2, cost2));
                }
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        int vertex;
        int cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
}

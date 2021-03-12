package algo0312;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_다익스트라1753 {
    static final int INF = Integer.MAX_VALUE;
    static int v, e, start;
    static List<ArrayList<Node>> list = new ArrayList<>();
    static int[] dist;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();
        start = sc.nextInt();

        // 인접리스트 구성
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList());
        }

        // 인접리스트 초기화
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            list.get(u).add(new Node(v, w));
        }

        dist = new int[v + 1];
        Arrays.fill(dist, INF);

        dijkstra(start);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            if (dist[i] == INF)
                sb.append("INF\n");
            else
                sb.append(dist[i] + "\n");
        }

        System.out.println(sb);
        sc.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[] visited = new boolean[v + 1];

        q.offer(new Node(start, 0));
        dist[start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll().end;

            if (visited[cur])
                continue;
            visited[cur] = true;

            for (Node next : list.get(cur)) {
                if (dist[next.end] > dist[cur] + next.weight) {
                    dist[next.end] = dist[cur] + next.weight;
                    q.offer(new Node(next.end, dist[next.end]));
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int end, weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}
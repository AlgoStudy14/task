import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_G4_별자리만들기 {
	/*
	 * <문제>
	 * 입력받은 N 개의 별들을 이어 하나의 별자리를 만듬.
	 * 	1. 별자리를 이루는 선은 다른 두 별을 이은 것.
	 *  2. 모든 별들은 별자리 위의 선을 통해 서로 직/간접적으로 이어져 있어야 한다.
	 * 별을 이을 때 두 별 사이의 거리만큼 비용이 들 때, 별자리를 만드는 최소 비용.
	 * 
	 * <문제풀이>
	 * 크루스칼과 Union-Find.
	 * 
	 * 오랜만에 하는 내용이라 까먹어서 보고 해결함... 나중에 다시 풀어야 할 문제.
	 */
	static int[] parent;
	static int N;
    static ArrayList<Star> tmpList = new ArrayList<>();
    static ArrayList<Star> starList = new ArrayList<>();
    static float answer = 0;
	//묶는다
	static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            if (x < y) parent[y] = x;
            else parent[x] = y;
        }
    }
	//이어져있는지 찾아서
    static int find(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }
    //같은 부모인지 확인
    static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);
        return x == y;
    }
    //좌표를 가지는 별자리
    static class Star implements Comparable<Star> {
        double x; //처음엔 x좌표로 사용하고 이후 시작 지점으로 사용
        double y;
        double weight;

        public Star(double x, double y, double weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Star o) {
            return Double.compare(weight, o.weight);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        //별들 좌표 입력받기.
        for (int i = 0; i < N; i++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            tmpList.add(new Star(x, y, 0f));
        }
        parent = new int[N];
        //make-set
        for (int i = 0; i < N; i++)
            parent[i] = i;
        //거리 값 계산하며 별 LIST에 넣기.
        for (int i = 0; i < tmpList.size() - 1; i++) {
            Star star1 = tmpList.get(i);
            double x1 = star1.x;
            double y1 = star1.y;
            for (int j = i + 1; j < tmpList.size(); j++) {
                Star star2 = tmpList.get(j);
                double x2 = star2.x;
                double y2 = star2.y;
                //(X1 - X2)^2 + (Y1 - Y2)^2 가 거리값.
                double weight = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                starList.add(new Star(i, j, weight));
            }
        }
        
        Collections.sort(starList);
        
        for (int i = 0; i < starList.size(); i++) {
            Star star = starList.get(i);
            //부모가 같지 않으면, 답에 거리값을 추가하고, 연결 실행.
            if (!isSameParent((int) star.x, (int) star.y)) {
                answer += star.weight;
                union((int) star.x, (int) star.y); //연결
            }
        }
        System.out.println(Math.round(answer * 100) / 100.0);
    }
}

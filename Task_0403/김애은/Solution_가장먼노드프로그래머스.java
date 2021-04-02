package algo0402;

import java.util.LinkedList;
import java.util.Queue;
//최소거리 보장 bfs
//가장 멀리 떨어져 잇는 노드는 마지막 큐 사이즈
public class Solution_가장먼노드프로그래머스 {
	
	public static void main(String[] args) {
		int n=6;
		int edge[][]= {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		int answer=solution(n,edge);
		System.out.println(answer);
	}
	private static int solution(int n, int[][] edge) {
		Queue<Integer> ed = new LinkedList<Integer>();
		int answer=0;
		boolean[][] check = new boolean[n+1][n+1];//연결 확인
		boolean[] V= new boolean [n+1];//방문했는지
		for(int i=0;i<edge.length;i++) {//연결
			check[edge[i][0]][edge[i][1]] = true;
			check[edge[i][1]][edge[i][0]] = true;
		}
		V[0]=V[1]=true;//시작
		ed.add(1);//1부터 시작
		while(!ed.isEmpty()) {
			int size=ed.size();//length가 필요하기 때문에
			for(int i=0;i<size;i++) {
				int cnt = ed.poll();
				for(int j=1;j<=n;j++) {
					if(check[j][cnt]&&!V[j]) {//연결이 되어있고 방문을 안했다면?
						V[j]=true;
						ed.add(j);
					}
				}
			}answer=size;
			//System.out.println(answer);
		}
		return answer;
	}
}

package floyd.warshall;
/*
 * 도시i->j로 가는 최소 비용 출력. 2차원 배열 형태
 */
import java.util.Scanner;

public class BOJ_11404_플로이드 {

	static int N, M, a,b,c;
	static int[][] distance;
	//무한대로 초기화해야된다해서 max value했다가 망..
	static final int INF = 1000000000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//도시의 개수
		N = sc.nextInt();
		//버스의 개수
		M = sc.nextInt();
		distance = new int[N+1][N+1];
		
		//초기화 해주는 부분
		for (int i = 1; i <=N; i++) {
			for (int j = 1; j <=N; j++) {
				//A->A로의 비용은 0이기 때문에 continue
				if(i==j) continue;
				//이후 입력받는 버스 정보에서 A->B로 갈 수 있는 버스 정보를 받지 못할수도있기때문에 이렇게 초기화
				distance[i][j] = INF;
			}
		}
		while(M-- >0) {
			//버스의 시작 도시
			a = sc.nextInt();
			//버스의 도착 도시
			b = sc.nextInt();
			//한번 타는데 필요한 비용
			c = sc.nextInt();
			
			distance[a][b] = Math.min(distance[a][b], c);
		}
		floydWarshall();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <=N; i++) {
			for (int j = 1; j <=N; j++) {
				//접근 못하면 0 출력
				if(distance[i][j] >= INF) sb.append("0 ");
				else sb.append(distance[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}
	private static void floydWarshall() {
		//거쳐가는 노드
		for (int k = 1; k <=N; k++) {
			//출발
			for (int i = 1; i <=N; i++) {
				//도착
				for (int j = 1; j <=N; j++) {
					//i->k->j vs i->j 비교해서 작은 값 넣음
					distance[i][j] = Math.min(distance[i][k]+distance[k][j], distance[i][j]);
				}
			}
		}
		
	}

}
 

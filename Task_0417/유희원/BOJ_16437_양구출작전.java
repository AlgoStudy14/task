package algo0417;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * G2
입력 예
4 //섬의 개수
S 100 3 //양이 2번 섬에 100마리 살고있고 2번째 섬에서 3번째 섬으로 갈 수 있는 다리 있음
W 50 1  //늑대가 3번 섬에 50마리 살고있고 3번째 섬에서 1번째 섬으로 갈 수 있는 다리 있음
S 10 1  //양이 4번 섬에 10마리 살고있고 4번째 섬에서 1번째 섬으로 갈 수 있는 다리 있음
양들은 1번 섬으로 가는 경로로 이동, 이때 늑대 있는 섬 지나치면 1늑1양 잡아먹음
늑대에게 잡혀먹지 않고 갈 수 있는 양의 최대 마릿수
 */

public class BOJ_16437_양구출작전 {

	static int N;
	static ArrayList<Integer> list[];
	static char[] sw;
	static int[] count;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //섬의 개수
		list = new ArrayList[N+1]; //연결 정보
		sw = new char[N+1]; //양,늑대
		count = new int[N+1]; //마리수
		for (int i = 0; i < N+1; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 2; i < N+1; i++) {
			sw[i] = sc.next().charAt(0);
			count[i] = sc.nextInt();
			//연결되어있는 애들 list에 담음
			list[sc.nextInt()].add(i);
		}
		System.out.println(postOrder(1));
	}
	//입력값들로 트리 만들어 후위 순회?
	private static long postOrder(int node) {
		long sum = 0;
		for(int next: list[node]) sum+=postOrder(next);
		//양이면 일단 더하고
		if(sw[node]=='S') return sum+count[node];
		//늑대 거쳐가면 음수일 경우 주의
		else return (sum-count[node]>=0)?sum-count[node]:0;
	}
}

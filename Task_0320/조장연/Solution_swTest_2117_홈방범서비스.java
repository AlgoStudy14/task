import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * <문제 요약>
 * 특정 마름모 모양으로 서비스가 진행된다.
 * 마름모의 크기에 따라 운영비용이 달라진다.
 * 집의 위치가 표시되어있는 map이 주어졌을 때 손해를 보지 않고 최대한 많은 집에게 서비스를 줄 경우 최대 집수?
 * 
 * <풀이법 요약>
 * 생각1 : 마름모를 만드는 공식을 구해서 완탐을 돌리려 했음 -> 마름모 만드는 공식만들기 귀찮고 시간초과 날꺼같았음.
 * 생각2 : 모든 좌표에서 BFS를 돌리면서 한바퀴 돌릴때마다(마름모의 크기 기준) answer를 뽑아내려 했음 -> 시간초과 날꺼 같았음.
 * 
 * 생각3 : map값을 받으면서 집좌표만 저장해 놓고 모든 좌표를 돌며 거리에 따른 집 수를 계산(position 배열)
 *        조건에 맞게 answer을 뽑아냄
 *        
 * 마름모를 직접 만들어서 완탐돌렸으면 어지러웠을것 같음.
 */

public class Solution_swTest_2117_홈방범서비스 {
	static int T, N, M;
	static ArrayList<int[]> houseList;
	static int[] cost;
	static int answer;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			houseList = new ArrayList<>();
			cost = new int[2 * (N - 1)];
			answer = 0;

			for (int i = 0; i < 2 * (N - 1); i++) {
				cost[i] = (i + 1) * (i + 1) + i * i;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int num = sc.nextInt();
					if (num == 1) {
						int[] arr = { i, j };
						houseList.add(arr);
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int[] position = new int[2 * (N - 1)];
					for (int k = 0; k < houseList.size(); k++) {
						int num = Math.abs(houseList.get(k)[0] - i) + Math.abs(houseList.get(k)[1] - j);
						for (int l = num; l < position.length; l++) {
							position[l]++;
						}
					}

					 //System.out.println(Arrays.toString(position));
					for (int k = 0; k < position.length; k++) {
						if (position[k] * M >= cost[k]) {
							if (answer < position[k])
								answer = position[k];
						}
					}
				}
			}
			System.out.println("#" + t + " " + answer);
		}
	}

}

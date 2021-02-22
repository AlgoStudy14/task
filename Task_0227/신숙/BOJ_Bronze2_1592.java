package BaekJoon;

import java.util.Scanner;

public class BOJ_Bronze2_1592 {
	/*
	 * 영식이와 친구들.
	 * 시계방향으로 1부터 N까지 적혀있는 자리에 앉음. 시작은 첫번째 친구가.
	 * 한사람이 M번 공을 받으면 종료. 
	 * 현재 공을 받은 횟수가 홀수면 시계방향으로 L번째에게. 짝수째면 반시계로 L번째에.
	 */
	static int N, M, L, num, count;
	static int[] countN;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		L = sc.nextInt();
		countN = new int[N];
		//먼저 공을 가지고 있기만 하면 +1이라고 하니, 처음 0번째에 있는 애 1로 초기화.
		countN[0] = 1;
		while(true) {
			if(countN[num] % 2 == 1)
				num += L;
			else
				num -= L;
			//N = 5, L= 2, num = 3, 시계방향으로 가면 0으로 가야함.
			if(num >= N)
				num %= N;
			//N = 6, L = 2, num = 1 반시계로 갈려면 N-1로 가야함.  N = 6, L = 3, num = 1
			else if(num < 0)
				num += N;
			countN[num]++;
			count++;
			if(countN[num] == M)
				break;
		}
		System.out.println(count);
	}
}

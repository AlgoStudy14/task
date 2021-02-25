import java.util.Scanner;

/*
 * <문제 요약>
 * 가지고있는 정사각형을 이용해서 만들수 있는 사각형의 갯수는?
 * 
 * <풀이법 요약>
 * 사각형은 m*n의 형태로 나타낼 수 있음
 * 예를들어 2*3 과 3*2는 같은것으로 간주하기 때문에 사각형의 가로세로는 root(전체 사각형갯수)를 넘지 않음
 * root(전체 사각형갯수)까지 각각 for문을 돌리면서 나눠지면 answer++;
 */

public class Main_BOJ_8320_직사각형을만드는방법 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int answer = 0;

		for (int i = 1; i <= n; i++) {
			int num = (int) Math.sqrt(i);
			for (int j = 1; j <= num; j++) {
				if (i % j == 0)
					answer++;
			}
		}

		System.out.print(answer);
	}

}

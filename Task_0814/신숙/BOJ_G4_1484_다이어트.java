import java.util.Scanner;

public class BOJ_G4_1484_다이어트 {
	/*
	 * <문제>
	 * G가 주어짐
	 * G는 현재 몸무게 x, 이전 몸무게 y일 때, x^2 - y^2 = G이다.
	 * 현재 몸무게로 가능한 X를 모두 출력하기. 가능한 몸무게가 없을땐 -1 출력.
	 * 
	 * <문제풀이>
	 * 0 < G <= 100,000
	 * 투 포인터로 start^2 - end^2 > g  start++
	 * start^2 - end^2 < g end++
	 */
	
	static int G, start, end;
	static boolean flag;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		G = sc.nextInt();
		//몸무게는 양의 정수, g는 자연수
		start = 1;
		end = 2;
		while(true) {
			long sub = (long)(Math.pow(end, 2) - Math.pow(start, 2));
			//기저조건
			if((sub > G) && (end - start == 1))
				break;
			if(sub == G) {
				System.out.println(end);
				flag = true;
			}
			if(sub > G)
				start++;
			else
				end++;
		}
		if(!flag)
			System.out.println(-1);
	}
}

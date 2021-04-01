package DynamicProgramming;

/*
 * 12 = 5 + 5 + (5 / 5) + (5 / 5)
 * 12 = 55 / 5 + 5 / 5
 * 12 = (55 + 5) / 5
 * 처럼 5와 사칙연산으로 12를 만들 수 있음
 * 여기서 5가 N, 12가 number
 * N을 사용한 횟수 최솟값 출력
 * 
 * N : 1이상 9이하
 */
public class PM_L3_N으로표현 {

	static int N = 5, number = 12;
	public static void main(String[] args) {
		System.out.println(solution(N,number));

	}
	static int n, target, answer;
	public static int solution(int N, int number) {
        answer = Integer.MAX_VALUE;
        n = N;
        target = number;
        dfs(0,0);
        return answer==Integer.MAX_VALUE?-1:answer;
    }
	//연산횟수, 연산결과값
	private static void dfs(int cnt, int num) {
		//최솟값이 8보다 크면 -1 return
		if(cnt>8) {
			answer = -1;
			return;
		}
		//연산결과가 목표값일경우
		if(num==target) {
			answer = Math.min(answer, cnt);
		}
		int tempN = n;
		//최대 8번이니까 8-cnt까지만, nCnt에서 다시 더해줘야함
		for (int i = 0; i < 8-cnt; i++) {
			int nCnt =  cnt+i+1;
			dfs(nCnt,num+tempN);
			dfs(nCnt,num-tempN);
			dfs(nCnt,num/tempN);
			dfs(nCnt,num*tempN);
			
			tempN = tempN*10+n; //자릿수 늘려주기
		}
	}
}

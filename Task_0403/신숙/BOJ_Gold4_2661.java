import java.util.Scanner;

public class BOJ_Gold4_2661 {
	/*
	 * 좋은 수열
	 * N이라는 수 길이를 입력받아서 이 수를 1,2,3으로만 구성해 인접한 두 부분집합이 다르도록.
	 * 
	 * 현재 입력할 수(1자리)와 이 전의 수(1자리)가 같은지 비교
	 * 현재 입력할 수(2자리)와 이 전의 수(2자리)가 같은지 비교............... 무한
	 * 한번이라도 반복되면 return.
	 * 
	 * => 수를 1,2,3 넣으면서 확인을 할 것인지.   수를 만들고 확인할 것인지
	 * 
	 * 몇자리까지 확인이 가능한가   =>	N/2	(4자리면, 1212  => 부분집합 2자리까지 비교가능. 6자리면, 123123 = > 부분집합 3자리까지 비교가능)
	 * 그중에 가장 작은 수를 구하라 => 시작을 1부터
	 */
	static int N;
	static boolean flag;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		dfs(1, "1");
	}
	
	static void dfs(int cnt, String s) {
		//dfs가 1부터 즉, 최저값부터 돌아갈 것이기 때문에 flag가 true이면 더 보지 않아도 됨.
		if(flag)
			return;
		//중간에 종료되지 않고 cnt==N이 되었다면 답이기 때문에 출력하고 종료되도록. 여기에서 return을 하려면 좀 복잡해져서.(출력을 하는 것이 아니면 최저값연산)
		if(cnt == N) {
			flag = true;
			System.out.println(s);
		}else {
			for(int i = 1; i <= 3; i++) {
				if(sub(s + i))
					dfs(cnt+1, s + i);
			}
		}
	}
	//부분집합 비교. N = 8 이면 4번 비교하게될것.
	static boolean sub(String s) {
		int l = s.length();
		int h = l/2;
		int start = l - 1;
		int end = l;
		
		for(int i = 1; i <= h; i++) {
			if(s.substring(start - i, end - i).equals(s.substring(start, end)))
				return false;
			start -= 1;
		}
		return true;
	}
}

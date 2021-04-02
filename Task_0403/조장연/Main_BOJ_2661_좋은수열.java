import java.util.ArrayList;
import java.util.Scanner;

/*
 * <문제 요약>
 * 연달아 중복되는 부분수열이 있지 않은 최소 N자리 수 구하기(숫자는 1,2,3만)
 * 
 * <풀이법 요약>
 * DFS를 사용하여 N자리 까지 최소수열를 구한다.
 * 이때 중복 부분수열이 있는지는 subString을 사용하여 비교
 * 
 * 처음에는 1자리부터 가능한 경우의 수를 각 ArrayList에 저장. 
 * N자리 수는 N/2끼리 합쳐서 되는지 확인
 * 이런식으로 하니까 메모리 터지더라...
 */

public class Main_BOJ_2661_좋은수열 {
	static int N;
	static String res="";
	static boolean isTrue;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		dfs("1",1); //1번째 위치에 1 넣기
		
		//System.out.println(result);
	}

	static void dfs(String res,int cnt) {//좋은 순열 인 것들만 넘어옴
		if(isTrue) return;
		if(cnt == N) {
			System.out.println(res);
			isTrue = true;
			return;
		}
		
		for(int i=1;i<=3;i++) {
			if(isPossible(res+i)) dfs(res+i,cnt+1);
		}
	}
	

	static boolean isPossible(String res) {
		//전체 길이의 반틈만 비교
		int div = res.length() /2;
		int beginIndex = res.length() -1; //전체index 바로 앞에꺼
		int endIndex = res.length(); //마지막 index
		for(int i=1;i<=div;i++) { //1부터 div길이까지 비교해주기
			if(res.substring(beginIndex-i, endIndex-i).equals(res.substring(beginIndex, endIndex))) return false;
			beginIndex -=1;
		}
		return true;
	}
}

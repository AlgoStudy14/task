package algo0619;
/*
 * <문제 요약>
 * 구해야 하는 것:퀸을 놓는 방법의 수 구하기
 * 제약 사항: 1<=N<15
 * 문제 유형: 백트래킹
 * 요구 개념: 백트래킹
 * 
 * <풀이법>
 * 모든 경우를 다 확인한다.
 * 그 중(Math.abs(col-i)==Math.abs(arr[col]-arr[i]))return false; 이부분을 알면 좋을거 같다.
 * 
 * 처음 제일 윗줄부터 놓기 때문에 행은 상관 안하고 열만 신경써도 된다.
 * 그래서 열 값을arr[] 배열안에 넣는다. 
 * 대각선의 경우 시작접에서 도착점까지 x,y의 차이가 같으면 대각선이 같다는것으로 나온다. (센스)

 */
import java.util.Scanner;

public class Main_nqueen9663 {
	static int T;
	static int arr[];
	static int ans=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T=sc.nextInt();
		arr=new int[T];
		go(0);
		System.out.println(ans);
	}
	private static void go(int cnt) {
		if(cnt==T) {
			ans++;
			return;
		}
		for(int i=0;i<T;i++) {
			arr[cnt]=i;
			if(possible(cnt)) {
				go(cnt+1);
			}
		}
	}
	private static boolean possible(int col) {
		for (int i = 0; i < col; i++) {
			if(arr[col]==arr[i])return false;
			else if(Math.abs(col-i)==Math.abs(arr[col]-arr[i]))return false;
		}
		return true;
	}

}

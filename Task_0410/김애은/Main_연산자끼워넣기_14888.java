package algo0405;
/*
 * 처음에는 bfs+dfs인줄 알았다.
 * dfs로만 풀 수 있다.
 * 처음에 개수가 나오니까
 * 여기서 중요한건 처음 dfs 넘겨줄때 값을 하나 들고 해야된다
 * 안들고 했다가 0과 연산할뻔 했다.
 */
import java.util.Scanner;

public class Main_연산자끼워넣기_14888 {
	static int N, min= Integer.MAX_VALUE, max= Integer.MIN_VALUE;
	static int[]num;
	static int[] yun=new int[4];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		num=new int[N];
		for(int i=0;i<N;i++) {
			num[i]=sc.nextInt();
		}
		for(int i=0;i<4;i++) {
			yun[i]=sc.nextInt();
		}
		dfs(num[0],1);
		System.out.println(max);
		System.out.println(min);
	}
	private static void dfs(int n, int index) {
		if(index==N) {
			max=Math.max(n, max);
			min=Math.min(n, min);
			return;
		}
		for(int i=0;i<4;i++) {
			if(yun[i]>0) {
				yun[i]--;
				if(i==0) {
					dfs(n+num[index],index+1);
				}else if(i==1) {
					dfs(n-num[index],index+1);
				}
				else if(i==2) {
					dfs(n*num[index],index+1);
				}
				else if(i==3) {
					dfs(n/num[index],index+1);
				}
				yun[i]++;
			}
		}
		
	}
}

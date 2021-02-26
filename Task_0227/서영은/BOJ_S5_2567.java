import java.util.Scanner;
/**
 * <문제 요약>
 * 구해야 하는 것: 색종이의 두레의 길이 
 * 제약 사항: 색종이가 도화지 밖으로 나가는 경우는 없다. 색종이의 수는 100 이하. 색종이는 서로 겹칠 수 있다.  
 * 문제 유형: 구현?
 * 요구 개념: 구현? 
 * 
 * <풀이법 요약>
 * 1. 입력받은 색종이를 map에서 1로 표시한다.
 * 2. 1로 표시된 자리의 상하좌우의 map 자리의 합이 3일 때는 둘레가 1 추가, 2일 때는 둘레가 2 추가 해서 구한다. 
 */

public class BOJ_2567_색종이2 {
	static int N;
	static int map[][] = new int[100][100];
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,1,-1};
	static int answer=0;
	
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		N=scann.nextInt();
		
		for (int i = 0; i < N; i++) {
			int c = scann.nextInt();
			int r = scann.nextInt();
			
			for (int j = r; j < r+10; j++) {
				for (int k = c; k < c+10; k++) {
					map[j][k]=1;
				}
			}
		}
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(map[i][j] == 1) {
					int cnt=0;
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						
						if(nr<0 || nr>=100 || nc<0 || nc>=100) continue;
						
						cnt+=map[nr][nc];
					}
					
					if(cnt==3) answer+=1;
					else if(cnt==2) answer+=2;
				}
			}
		}
		System.out.println(answer);
	}
}

import java.util.Scanner;

//모두 다 겹쳐있다는 가정 하에 겉에 테두리 길이만 구하려했는데 그런 문제가 아님
public class Main {

	static int N;
	static int x, y,nr,nc;
	static int[][] paper;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int ans;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		paper = new int[102][102];
		
		for (int i = 0; i <N; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			
      //일단 입력 받고
			for (int j = x; j <x+10; j++) {
				for (int j2 = y; j2 <y+10; j2++) {
					paper[j][j2] = 1;
				}
			}
		}		
		for (int j = 0; j <100; j++) {
			for (int k = 0; k < 100; k++) {
        //1이면 (색종이 범위면)
				if(paper[j][k]==1) {
          //4방탐색해서
					for (int l = 0; l <4; l++) {
						nr = j +dr[l];
						nc = k +dc[l];
						//주위 0 개수가 둘레에 반영됨. ex.코너부분
						if(paper[nr][nc]==0) ans++;
					}
				}
			}
		}
			System.out.println(ans);
		
	}

}

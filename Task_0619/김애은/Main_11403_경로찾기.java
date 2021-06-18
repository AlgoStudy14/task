package algo0619;
/*
 * 플로이드 와샬문제
 
 * <문제 요약>
 * 구해야 하는 것:경로 찾기
 * 제약 사항: 1<=N<100
 * 문제 유형: 플로이드 와샬
 * 요구 개념: 플로이드 와샬
 * 
 * <풀이법>
 * 틀만 외우면 언제나 풀수있을듯?
 * 그리고 i->j로 가는게 i->k, k->j랑 같은말
 * 그것만 알면 될거같다.
 * for (int k = 0; k <N ; k++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
	여기서 한번 볼거는 이미 1로 되어있는건 연결이 되어있다는거...
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11403_경로찾기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int arr[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for (int k = 0; k <N ; k++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(arr[i][k]==1 && arr[k][j]==1) {
						arr[i][j]=1;
					}
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

}

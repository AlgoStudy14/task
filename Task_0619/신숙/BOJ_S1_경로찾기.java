import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_S1_경로찾기 {
	/*
	 * <문제>
	 * 가중치 없는 방향그래프가 주어지고, 이에 대해 i -> j로 가는 경로가 있는지 구하는 프로그램.
	 * 이를 매트릭스로 출력
	 * 
	 * <풀이>
	 * 플로이드-와샬 기본문제.
	 * 		: 모든 정점에서의 최단거리를 구하는 알고리즘.
	 */
	
	static int N;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++)
                map[i][j] = input[j].charAt(0) == '1';
        }

        for (int k = 0; k < N; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    if (map[i][k] & map[k][j])
                        map[i][j] = true;

		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j])
					System.out.print(1 + " ");
				else
					System.out.print(0 + " ");
			}
			System.out.println();
		}
	}
}

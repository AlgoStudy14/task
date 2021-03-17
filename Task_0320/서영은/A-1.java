import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

   static int T, N, answer, curSeonLen, finalCoreCnt;
   static int[][] cells;
   static ArrayList<int[]> core; // core 위치 저장
   static int[] dr= {-1,1,0,0};
   static int[] dc= {0,0,-1,1};
   
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(in.readLine());
      
      T = Integer.parseInt(st.nextToken());
      
      for (int t = 1; t <= T; t++) {
         st = new StringTokenizer(in.readLine());
         // 입력
         N = Integer.parseInt(st.nextToken());
         
         core = new ArrayList<>();
         cells = new int[N][N];
         
         for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < N; j++) {
               cells[i][j] = Integer.parseInt(st.nextToken());
               
               if(i==0 || i==N-1 || j==0 || j==N-1) continue;
               if(cells[i][j] == 1) {
                  core.add(new int[]{i,j});
               }
            }
         }
         
         answer = Integer.MAX_VALUE;
         finalCoreCnt = Integer.MIN_VALUE;
         //로직
         DFS(0, 0, 0);
         
         //출력 
         System.out.println("#"+t+" "+answer);
      }
   }
   
   //백트래킹
   private static void DFS(int idx, int lenSum, int coreCnt) {
      //기저 조건 
      if(idx == core.size()) {
         if(finalCoreCnt < coreCnt) {
            finalCoreCnt = coreCnt;
            answer = lenSum;
         } else if (finalCoreCnt == coreCnt) {
            answer = Math.min(answer, lenSum);
         }
         return;
      }

      //유도조건
      int curX = core.get(idx)[0];
      int curY = core.get(idx)[1];
      
      for (int dir = 0; dir < 4; dir++) {
         if(checkSeon(curX, curY, dir)) {
            makeSeon(curX, curY, dir, 2); // 선을 2로 놓기
            DFS(idx+1, lenSum+curSeonLen, coreCnt+1);
            makeSeon(curX, curY, dir, 0); // 선 놓은거 되돌려 놓기
         }
      }
      DFS(idx+1, lenSum, coreCnt);
   }

   // cell에 선 놓기도 하고 뺴기도 하기 
   private static void makeSeon(int curX, int curY, int dir, int status) {
      int nx = curX+dr[dir];
      int ny = curY+dc[dir];
      
      while(check(nx,ny)) {
         cells[nx][ny] = status;
         nx += dr[dir];
         ny += dc[dir];
      }
   }

   // 선을 방향대로 놓아보았을 때, 놓을 수 있는지 확인하는 함수 
   private static boolean checkSeon(int curX, int curY, int dir) {
      
      int nx = curX+dr[dir];
      int ny = curY+dc[dir];
      int cnt=0;
      
      while(check(nx,ny)) {
         if(cells[nx][ny] != 0) return false;
         nx += dr[dir];
         ny += dc[dir];
         ++cnt;
      }
      curSeonLen = cnt;
      return true;
   }

   private static boolean check(int x, int y) {
      return x>=0 && x<N && y>=0 && y<N;
   }
}

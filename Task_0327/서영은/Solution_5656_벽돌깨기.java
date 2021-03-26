package algo210327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: N, W, H, 벽돌들의 정보가 주어질 때, 남은 벽돌의 개수를 구하기(최대한 많은 벽돌을 제거할 때)
 * 제약 사항: 구슬은 N번만 쏠 수 있다. 벽돌들의 정보는 WxH 배열로 주어진다.
 * 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨뜨릴 수 있다.
 * 벽돌은 숫자 1~9로 표현, 구슬이 명중한 벽돌은 상하좌우로(벽돌이 적힌 숫자-1) 칸 만큼 같이 제거된다.
 * => 제거되는 범위 내에 있는 벽돌은 동시에 제거된다.
 * 문제 유형: dfs, bfs
 * 요구 개념: dfs, bfs 시뮬레이션
 * 
 * <풀이법 요약>
 * 1. W를 N개만큼 순열로 뽑기(중복도 가능!)
 * 2. copy에 map 복사 => 순서대로 벽돌 맞춰서 없애기
 * 3. 떨어지는 벽돌은 queue에 담아서 구현
 * 4. 남은 벽돌의 개수 구하기! => 벽돌의 수가 가장 적은 경우!!
 * 
 * => 시간 초과 나서 벽돌이 하나도 안남으면 바로 끝내버리게 함. 
 */
public class Solution_5656_벽돌깨기 {

	static class Block {
		int h;
		int w;
		int number;
		public Block(int h, int w, int number) {
			super();
			this.h = h;
			this.w = w;
			this.number = number;
		}
	}
	
	static int T, N, W, H, answer, allBlockCnt, tempBlockCnt;
	static boolean finish;
	static int[][] map, temp;
	static int[] HSize;
	static Queue<Block> queue = new LinkedList<>();
	static Queue<Integer> fall = new LinkedList<Integer>(); 
	static int[] order;
	static int[] dh= {1,-1,0,0};
	static int[] dw= {0,0,1,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			temp = new int[H][W];
			order = new int[N];
			answer = Integer.MAX_VALUE;
			allBlockCnt=0;
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] != 0) ++allBlockCnt; 
				}
			}
			
			nHr(0);
			
			System.out.println("#"+t+" "+answer);
		}
	}

	private static void nHr(int cnt) {
		if(cnt == N) {
			removeBlock();
			//남은 벽돌의 개수
//			checkBlock();
			if(finish) {
				answer = 0;
				return;
			} else {
				answer = Math.min(answer, allBlockCnt - tempBlockCnt);
				return;
			}
		}
		
		//벽돌이 하나도 안남으면 그냥 다 끝내버리기
		if(answer == 0) return;
		
		for (int i = 0; i < W; i++) {
			order[cnt] = i;
			nHr(cnt+1);
		}
	}

	private static void removeBlock() {
		finish = false;
		copyMap();
		tempBlockCnt = 0;
		
		for (int a = 0; a < N; a++) {
			int curW = order[a];
			
			queue.clear();
			Block start = null;
			// 구슬이 떨어지는 첫 벽돌 찾기
			boolean goNext = false;
			for (int i = 0; i < H; i++) {
				if(temp[i][curW]!=0) {
					start = new Block(i, curW, temp[i][curW]);
					queue.offer(start);
					break;
				}
				if(i==H-1 && temp[i][curW]==0) {
					goNext = true;
					break;
				}
			}
			
			if(goNext) continue;
			//벽돌 제거 
			while(!queue.isEmpty()) {
				Block curBlock = queue.poll();
				
				if(curBlock.number==1) {
					temp[curBlock.h][curBlock.w] = 0;
					++tempBlockCnt;
				} else {
					for (int dir = 0; dir < 4; dir++) {
						go(curBlock.h, curBlock.w, curBlock.number, dir);
					}
				}
			}
			
			//벽돌이 하나도 없으면 밑에 할필요 없다.
			if(tempBlockCnt == allBlockCnt) {
				finish = true;
				return;
			}
			
			//하나만 깨지면, 벽돌 정렬 필요 없다.
			if(start.number == 1) continue;
			
			// 빈 공간이 있을 경우, 벽돌은 밑으로 떨어지게 된다.
			fall.clear();
			for (int i = 0; i < W; i++) {
				for (int j = H-1; j >= 0; j--) {
					if(temp[j][i] != 0) fall.offer(temp[j][i]);
				}
				
				int hIdx = H-1;
				while(!fall.isEmpty()) {
					temp[hIdx--][i] = fall.poll();
				}
				while(hIdx>=0) {
					temp[hIdx--][i] = 0; 
				}
			}
		}
	}

	private static void copyMap() {
		for (int i = 0; i < H; i++) {
			System.arraycopy(map[i], 0, temp[i], 0, W);
		}
	}

	private static void go(int h, int w, int number, int dir) {
		for (int i = 0; i < number; i++) {
			int nh = h + i*dh[dir];
			int nw = w + i*dw[dir];
			
			if(!check(nh,nw)) continue;
			if(temp[nh][nw] != 0 && temp[nh][nw] != 1) queue.offer(new Block(nh, nw, temp[nh][nw]));
			if(temp[nh][nw] != 0) {
				temp[nh][nw] = 0;
				++tempBlockCnt;
			}
		}
	}

	private static boolean check(int h, int w) {
		return h>=0 && h<H && w>=0 && w<W;
	}
}

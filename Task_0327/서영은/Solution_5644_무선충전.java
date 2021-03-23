package algo210327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 * <문제 요약>
 * 구해야 하는 것: 모든 사용자 충전량 합의 최대값 
 * 제약 사항: 지도 밖으로 이동하는 경우는 없다. 여러명의 사용자가 접속한 경우, 접속한 사용자의 수만큼 충전 양을 균둥하게 분배
 * 두개 다 있는 경우, 하나를 선택하여 접속할 수 있다.
 * 문제 유형: 시뮬레이션 
 * 요구 개념: 시뮬레이션 
 * 
 * <풀이법 요약>
 * 1. 충전기 클래스를 만들어 arraylist로 충전기 다 저장 
 * 2. 사용자들의 궤적 배열에 표시 
 * 3. 시뮬레이션 돌리기 
 * (처음에 풀 때는 좌표로 접근했는데 그렇게하면 시간초가 같을 때를 구분하지 못해서 풀지 못함)
 * -> 매 초마다 움직이는 좌표를 충전기 클래스에서 확인.
 */
class BC {
	int num;
	int x;
	int y;
	int c;
	int p;

	public BC(int num, int x, int y, int c, int p) {
		super();
		this.num = num;
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
	}
}

public class Solution_5644_무선충전 {

	static int T,M,A,chargeSum;
	static int[] Adir;
	static int[] Bdir;
	static int[] dr = {0,0,1,0,-1};
	static int[] dc = {0,-1,0,1,0};
	static ArrayList<BC> bcList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(in.readLine(), " ");
			
			Adir = new int[M];
			Bdir = new int[M];
			// A 사용자 입력 
			for (int i = 0; i < M; i++) {
				
				Adir[i] = Integer.parseInt(st.nextToken());
				
			}

			st = new StringTokenizer(in.readLine(), " ");
			// B 사용자 입력 
			for (int i = 0; i < M; i++) {
				Bdir[i] = Integer.parseInt(st.nextToken());
			}
			
			//AP 정보
			bcList = new ArrayList<BC>();
			
			int chargeNum = 0;
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				
				++chargeNum;
				insertBcList(chargeNum,x,y,c,p);
			}
			
			chargeSum = 0;
			
			checkCharge();
			
			System.out.println("#"+t+" "+chargeSum);
		}
		

	}
	
private static void checkCharge() {
	int ACurR = 1;
	int ACurC = 1;
	int BCurR = 10;
	int BCurC = 10;
	
	ArrayList<BC> Abcs = checkBcList(ACurR, ACurC);
	ArrayList<BC> Bbcs = checkBcList(BCurR, BCurC);
	
	if(Abcs.size() >= 1 && Bbcs.size() == 0) {
		
		int Amax = 0;
		for (BC abc : Abcs) {
			if(Amax < abc.p) {
				Amax = abc.p;
			}
		}
		
		chargeSum = chargeSum + Amax;
		
	} else if(Abcs.size() == 0 && Bbcs.size() >= 1) {
		int Bmax = 0;
		for (BC bbc : Bbcs) {
			if(Bmax < bbc.p) {
				Bmax = bbc.p;
			}
		}
		
		chargeSum = chargeSum + Bmax;
	} else {
		int Amax = 0;
		for (BC abc : Abcs) {
			if(Amax < abc.p) {
				Amax = abc.p;
			}
		}
		
		int Bmax = 0;
		for (BC bbc : Bbcs) {
			if(Bmax < bbc.p) {
				Bmax = bbc.p;
			}
		}

		chargeSum = chargeSum + Amax + Bmax;
	}
	
	for (int i = 0; i < M; i++) {
		ACurR = ACurR + dr[Adir[i]];
		ACurC = ACurC + dc[Adir[i]];
		
		BCurR = BCurR + dr[Bdir[i]];
		BCurC = BCurC + dc[Bdir[i]];
		
		Abcs.clear();
		Abcs = checkBcList(ACurR, ACurC);
		Bbcs.clear();
		Bbcs = checkBcList(BCurR, BCurC);
		
		// A와 B가 같은 시간에 해당 칸에 한개씩밖에 없을 때 
		if(Abcs.size() == 1 && Bbcs.size()==1) {
			if(Abcs.get(0).num != Bbcs.get(0).num) {
				chargeSum = chargeSum + Abcs.get(0).p + Bbcs.get(0).p;
			} else {
				chargeSum += Abcs.get(0).p;
			}
		} else if(Abcs.size() >= 1 && Bbcs.size() == 0) {
			int Amax = 0;
			for (BC abc : Abcs) {
				if(Amax < abc.p) {
					Amax = abc.p;
				}
			}
			
			chargeSum = chargeSum + Amax;
		} else if(Abcs.size() == 0 && Bbcs.size() >= 1) {
			int Bmax = 0;
			for (BC bbc : Bbcs) {
				if(Bmax < bbc.p) {
					Bmax = bbc.p;
				}
			}
			
			chargeSum = chargeSum + Bmax;
		} else if(Abcs.size() == 0 && Bbcs.size() == 0){ 
		
		} else{ // Abcs, Bbcs, 전부 있을 때
			//A의 max 구하기 
			BC Abc = null;
			BC Bbc = null;
			
			int Amax = 0;
			for (BC abc : Abcs) {
				if(Amax < abc.p) {
					Amax = abc.p;
					Abc = abc;
				}
			}
			
			// B의 max 구하기 
			int Bmax = 0;
			for(BC bbc : Bbcs) {
				if(Bmax < bbc.p) {
					Bmax = bbc.p;
					Bbc = bbc;
				}
			}
			
			// 같은 시간에 같은 충전기에 있으면 
			if(Abc.num == Bbc.num) {
				if(Abcs.size() == 1) {
					
					int secBmax = 0;
					for(BC bbc : Bbcs) {
						if(secBmax < bbc.p && bbc.p != Bmax) {
							secBmax = bbc.p;
						}
					}
					
					chargeSum = chargeSum + Amax + secBmax;
				} else if(Bbcs.size() == 1) {
					int secAmax = 0;
					for(BC abc : Abcs) {
						if(secAmax < abc.p && abc.p != Amax) {
							secAmax = abc.p;
						}
					}
					chargeSum = chargeSum + Bmax + secAmax;
				} else {
					int secAmax = 0;
					for(BC abc : Abcs) {
						if(secAmax < abc.p && abc.p != Amax) {
							secAmax = abc.p;
						}
					}
					
					int secBmax = 0;
					for(BC bbc : Bbcs) {
						if(secBmax < bbc.p && bbc.p != Bmax) {
							secBmax = bbc.p;
						}
					}
					
					if(secBmax > secAmax) {
						chargeSum = chargeSum + Amax + secBmax;
					} else {
						chargeSum = chargeSum + Bmax + secAmax;
					}
				}
			} else {
				chargeSum = chargeSum + Amax + Bmax;
			}
		}
	}
		
}

	// 해당 좌표에서 충전이 가능한지 확인 
	private static ArrayList<BC> checkBcList(int r, int c) {
		ArrayList<BC> nowBcList = new ArrayList<BC>();
		for (BC bc : bcList) {
			if(bc.x == r && bc.y == c) {
				nowBcList.add(bc);
			}
		}
		
		return nowBcList;
	}

	//충전기 좌표 세팅 
	private static void insertBcList(int num, int x, int y, int c, int p) {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				if((Math.abs(i-x) + Math.abs(j-y)) <= c) {
					bcList.add(new BC(num,i,j,c,p));
				}
			}
		}
	}

}

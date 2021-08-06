package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14891_톱니바퀴 {
	static int top[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		top= new int[4][8];
		String s;
		for (int i = 0; i < 4; i++) {
			s =br.readLine();
			for (int j = 0; j < 8; j++) {
				top[i][j]=s.charAt(j)-'0';
			}
		}
		StringTokenizer st;
		int cnt=Integer.parseInt(br.readLine());
		for (int i = 0; i < cnt; i++) {
			st=new StringTokenizer(br.readLine());
			int what = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			boolean ch[]= check(top);
			if(what==0) {
				if(!ch[0]) {
					if(!ch[1]) {
						if(!ch[2]) 
							move(3,c);
						move(2,c);
					}
					move(1,c);
				}
				move(0,c);
			}
			else if(what==3) {
				if(!ch[2]) {
					if(!ch[1]) {
						if(!ch[0]) 
							move(0,c);
						move(1,c);
					}
					move(2,c);
				}
				move(3,c);
			}
			else if(what==1){
				move(1,c);
				if(!ch[0]) {
					move(0,c);
				}if(!ch[1]) {
					move(2,c);
					if(!ch[2])
						move(3,c);
				}
			}
			else if(what==2){
				move(2,c);
				if(!ch[2]) {
					move(3,c);
				}if(!ch[1]) {
					move(1,c);
					if(!ch[0])
						move(0,c);
				}
			}
		}
		int answer=0;
		if(top[0][0]==1)answer+=1;
		if(top[1][0]==1)answer+=2;
		if(top[2][0]==1)answer+=4;
		if(top[3][0]==1)answer+=8;
		System.out.println(answer);
	}

	private static void move(int i, int c) {
		if(c==1) {
			int temp = top[i][7];
			for (int j = 6; j >0; j--) {
				top[i][j]=top[i][j-1];
				top[i][0]=temp;
			}
		}
		else {
			int temp = top[i][0];
			for (int j = 7; j < 2; j++) {
				top[i][j-1]=top[i][j];
				top[i][7]=temp;
			}
		}
	}

	private static boolean[] check(int[][] top) {
		boolean check[]=new boolean[3];
		for (int i = 0; i < 3; i++) {
			if(top[i][2]==top[i+1][6])check[i]=true;
			else check[i]=false;
		}
		return check;
	}


}

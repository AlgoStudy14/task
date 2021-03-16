package algo0315;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_1238 {
	static int len, start;
	static Queue<int[]> que;
	static ArrayList<int[]> arr;
	static boolean[] V;
	static int max;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//for(int t=1;t<=10;t++) {
			max=0;
			que= new LinkedList<int[]>();
			arr = new ArrayList<>();
			len=sc.nextInt();
			start=sc.nextInt();
			V=new boolean[len/2];
			for(int i=0;i<len/2;i++) {
				int from= sc.nextInt();
				int to = sc.nextInt();
				arr.add(new int[] {from,to});
			}
			for(int i=0;i<arr.size();i++) {
				if(arr.get(i)[0]==start) {
					if(V[i]==false) {
						V[i]=true;
						que.offer(new int[] {arr.get(i)[0],arr.get(i)[1]});
						if(max<arr.get(i)[1])max=arr.get(i)[1];
					}
				}
			}
			bfs();
			System.out.println("#"+" "+max);
		}

//	}
	private static void bfs() {
		while(!que.isEmpty()) {
			int p[] = que.poll();
			int s=p[0];
			int e=p[1];
			
			for(int i=0;i<arr.size();i++) {
				if(arr.get(i)[0]==e && !V[i]) {
					V[i]=true;
					System.out.println(Arrays.toString(p));
					que.offer(new int[] {arr.get(i)[0],arr.get(i)[1]});
					if(max<arr.get(i)[1]) {
						max=arr.get(i)[1];
					}
				}
			}
		}
		
	}

}

package algo_myselr;

import java.util.Arrays;
import java.util.Scanner;

public class Main_jeong_1205 {
	static int N;
	static int max=Integer.MIN_VALUE;
	static int []arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		int zero=0;
		arr=new int[N];
		for(int i=0;i<N;i++) {
			arr[i]=sc.nextInt();
			if(arr[i]==0)//0개수
				zero+=1;
		}
		Arrays.sort(arr);
		if(zero==arr.length) {//모두 0이면
			max=zero;
			}
		for(int i=zero;i<N-1;i++) {//0개수,0시작
			go(i,zero);
		}
		System.out.println(max);
	}
	private static void go(int i, int zero) {
		int temp=1;
		while(zero>=0) {//0이상이면
			if(i==N-1)
				break;
			if(arr[i+1]-arr[i]==1) {//차이가 1이면
				temp++;
			}
			else if(arr[i+1]==arr[i]) {//같으면 그냥 넘어가기 대신 다음인덱스부터
				i++;
				continue;
			}
			else {//zero가 0보다 작으면
				int x=arr[i+1]-arr[i]-1;//6-3이면 0은 2개가 들어가야 된다.
				if(x<=zero) {//zero보다 작으면 
					temp+=x+1; //하지만 실제 길이에서는 3개.
				}
				else {//x가 zero보다 크다
					if(zero!=0) 
						temp+=zero;//그냥 뒤에 zero개수 더해주기
				}
				zero=zero-x;//zero 쓴만큼
				}
			i++;
		}
		max=Math.max(max, temp);
		return;
	}

}

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
			if(arr[i]==0)
				zero+=1;
		}
		Arrays.sort(arr);
		if(zero==arr.length) {
			max=zero;
			}
		for(int i=zero;i<N-1;i++) {
			go(i,zero);
		}
		System.out.println(max);
	}
	private static void go(int i, int zero) {
		int temp=1;
		while(zero>=0) {
			if(i==N-1)
				break;
			if(arr[i+1]-arr[i]==1) {
				temp++;
			}
			else if(arr[i+1]==arr[i]) {
				i++;
				continue;
			}
			else {
				int x=arr[i+1]-arr[i]-1;
				if(x<=zero) {
					temp+=x+1;
				}
				else {
					if(zero!=0) 
						temp+=zero;
				}
				zero=zero-x;
				}
			i++;
		}
		max=Math.max(max, temp);
		return;
	}

}

package algo_myselr;

import java.util.Scanner;

public class Main_1592 {
	static int N,M,L;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		L=sc.nextInt();
		int arr[]=new int[N+1];
		arr[0]=1;
		int tot=0;
		int i=0;
		while(true) {
			if(arr[i]%2!=0) {
				i-=L;
				if(i<0)
					i=i+N;
			}else {
				i+=L;
				if(i>N-1)
					i=i-N;
			}
			arr[i]+=1;
			tot++;
			if(arr[i]==M) {
				System.out.println(tot);
				break;
			}
		}

	}

}

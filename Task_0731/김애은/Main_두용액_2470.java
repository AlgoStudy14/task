package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_두용액_2470 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int arr[]=new int[N];
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i <N ; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		int min=Integer.MAX_VALUE;
		Arrays.sort(arr);
		int low=0;
		int high=N-1;
		int answer1=0, answer2=0;
		while(low<high) {
			int num=arr[low]+arr[high];
			int temp=Math.abs(num);
			if(min>temp) {
				min=temp;
				answer1=arr[low];
				answer2=arr[high];
				
			}
			//이부분에서 틀림
			//else가 된다고 생각 왜? 
			//중간에 바뀌는게 없을거라고 생각한듯..... 어렵네...
			if(num>0)high--;
			else low++;
			
		}
		System.out.println(answer1+" "+answer2);
	}

}

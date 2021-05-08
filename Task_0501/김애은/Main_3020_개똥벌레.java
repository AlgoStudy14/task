package study0501;
/*
 * 이문제도 시초 문제
 * 하나의 배열로만 하려고 하니 엄청 많이 돈다는걸 앍게됨
 * 그래서 석순과 종유석으로 각 배열을 만들어 해당 높이의 칸에 +1씩해줌
 * 거꾸로 생각하기!!
 */
import java.util.Arrays;
import java.util.Scanner;

public class Main_3020_개똥벌레 {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int N=sc.nextInt();
		int H=sc.nextInt();
		int suk[]=new int[H+1];
		int jong[]=new int[H+1];
		int tot[] = new int[H+1];
		
		for(int i=0;i<N;i++) {
			int h=sc.nextInt();
			if(i%2==0) {
				suk[h]+=1;
			}else {
				jong[h]+=1;
			}
		}
		int cnt=0;
		for(int i=H-1;i>0;i--) {
			suk[i]+=suk[i+1];
			jong[i]+=jong[i+1];
		}
		for(int i=1;i<=H;i++) {
			tot[i]=suk[i]+jong[H-i+1];
		}
		Arrays.sort(tot);
		for(int i=1;i<=H;i++) {
			if(tot[1]==tot[i])cnt++;
		}
		System.out.println(tot[1]+" "+cnt);
	}
}

package study0710;
/*
 * 정렬을 해서 제일 처음 두개를 더해야된다는건 문제에서 나옴
 * 더하고 나면? 포함해서 그다음 작은 수 두개 더해야됨.
 * 그걸 체크하기 위해 pq를 사용
 * 
 */
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_1714정렬하기 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long result=0;
		int N=sc.nextInt();
		PriorityQueue<Long> pq = new PriorityQueue<>();
		for(int i=0;i<N;i++) {
			pq.add(sc.nextLong());
		}
		while(pq.size()>1) {
			long a=pq.poll();
			long b=pq.poll();
			pq.add(a+b);
			result+=a+b;
		}
		System.out.println(result);
	}

}

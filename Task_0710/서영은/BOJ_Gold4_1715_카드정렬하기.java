package algo210710;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_Gold4_1715_카드정렬하기 {

	static int N, num[];
	static Queue<Long> pq = new PriorityQueue<Long>();
	static long dp[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(in.readLine());
		
		for (int idx = 0; idx < N; idx++) {
			pq.offer(Long.parseLong(in.readLine()));
		}
		//logic
		long sum = 0;
		while(pq.size() >= 2) {
			long curNum = pq.poll() + pq.poll();
			sum += curNum;
			pq.offer(curNum);
		}
		
		bw.write(sum + "\n");
		
		in.close();
		bw.flush();
		bw.close();
	}
}

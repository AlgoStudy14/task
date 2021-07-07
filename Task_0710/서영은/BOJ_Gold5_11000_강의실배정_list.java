package algo210710;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// arraylist 이용! 시간초과가 발생한다.
public class BOJ_Gold5_11000_강의실배정_list {

	static int N, lecture[][];
	static List<Integer> finishTime = new ArrayList<Integer>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(in.readLine());
		lecture = new int[N][2];
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			lecture[i][0] = Integer.parseInt(st.nextToken());
			lecture[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(lecture, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) return o1[1] - o2[1];
				else return o1[0] - o2[0];
			}
		});
		
		finishTime.add(lecture[0][1]);
		
		for (int idx = 1; idx < N; idx++) {
			
			for(int listIdx = 0; listIdx < finishTime.size(); listIdx++) {
				if(lecture[idx][0] >= finishTime.get(listIdx)) {
					finishTime.remove(listIdx);
					break;
				}
			}
			finishTime.add(lecture[idx][1]);
		}
		
		bw.write(finishTime.size() + "\n");
		
		in.close();
		bw.flush();
		bw.close();
	}
}

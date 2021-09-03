package 하반기;
/*
 * 매우 얼떨떨하다
 * 15분컷했다
 * 근데 살짝 노가다같다
 * 
 * 일단 푸는법은
 * queue에 진실 아는사람을 다 넣어둔다 어떻게 보면 queue이름이 liar가 아니라 sincere로 했어야 하는거 아닌가
 * 그리고 파티 배열을 만든다.
 * 처음에 만약 아는사람 0명? 그럼 파티 개수만큼 return 하고 종료
 * 그 뒤 bfs식으로 진실 아는사람들을 모두 돌린다
 * boolean 배열로 진실 아는사람 한명이라도 있으면 true로 만들고 false는 계속 돈다
 * queue가 빌때까지 돌고 다 돌고나서 false개수 찾아서 return 하면 문제 끝
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1043_거짓말 {
//	static ArrayList<Integer> lier = new ArrayList<>();
	static Queue<Integer> liar = new LinkedList<Integer>();
	static ArrayList<Integer>[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int people = Integer.parseInt(st.nextToken());
		int party = Integer.parseInt(st.nextToken());
		arr = new ArrayList[party];
		for (int i = 0; i < party; i++) {
			arr[i]=new ArrayList<Integer>();
		}
		boolean check[] = new boolean[party];
		st=new StringTokenizer(br.readLine());
		int kn = Integer.parseInt(st.nextToken());
		if(kn==0) {
			System.out.println(party);
			System.exit(0);
		}
		for (int i = 0; i < kn; i++) {
			liar.add(Integer.parseInt(st.nextToken()));
		}
		for (int i = 0; i < party; i++) {
			st=new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			for (int j = 0; j < c; j++) {
				int t = Integer.parseInt(st.nextToken());
				arr[i].add(t);
			}
		}
		while(!liar.isEmpty()) {
			int lie=liar.poll();
			for (int i = 0; i < party; i++) {
				if(check[i])continue;
				if(arr[i].contains(lie)) {
					check[i]=true;
					for (int j = 0; j < arr[i].size(); j++) {
						if(lie!=arr[i].get(j)) {
							if(!liar.contains(arr[i].get(j))){
								liar.add(arr[i].get(j));
							}
						}
					}
				}
			}
		}
		int answer=0;
		for (int i = 0; i < party; i++) {
			if(!check[i])
				answer+=1;
		}
		System.out.println(answer);
	}

}

package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_문자열게임2_20437 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for (int t = 0; t <T; t++) {
			int min=10000;
			int max=0;
			String line = br.readLine();
			st=new StringTokenizer(br.readLine());
			int cou = Integer.parseInt(st.nextToken());
			List<Integer>list[] = new ArrayList[26];
			for (int i = 0; i < 26; i++) {
				list[i]=new ArrayList<>();
			}
			for (int i = 0; i < line.length(); i++) {
				int c = line.charAt(i)-97;
				list[c].add(i);
			}
			for (int i = 0; i < 26; i++) {
				int le = list[i].size();
				if(le>=cou) {
					for(int j=0;j<=le-cou;j++) {
						min=Math.min(min, list[i].get(j+cou-1)-list[i].get(j)+1);
						max=Math.max(max, list[i].get(j+cou-1)-list[i].get(j)+1);
	
					}
				}
			}
			if(min==10000&&max==0)System.out.println(-1);
			else System.out.println(min+" "+max);
		}
	}

}

package algo0312;
/*
 * 여행경로 문제 
 * dfs
 * 여행경로를 사용해 처음 시작은 인천 하나씩 가면서 tickets를 다 사용하여라
 * 만약 같을 경우  sort해라
 * 근데 도대체 난 뭐가 문제인지 모르겟당...
 * 
 * 
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution_prog_여행경로 {
	static boolean V[];
	static ArrayList<String> answer = new ArrayList<>();
	static String sb="";
	public static void main(String[] args) {
		String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
		int le=tickets.length;
		
		for(int i=0;i<le;i++) {
			V=new boolean[le];
			if(tickets[i][0].equals("ICN")) {
				sb+=tickets[i][0]+",";
				V[i]=true;
				dfs(tickets,tickets[i][1],1);
				V[i]=false;
				
			}
		}
		System.out.println(answer);
		Collections.sort(answer);
		String[] sol = answer.get(0).split(",");
		
		System.out.println(Arrays.toString(sol));
	}
	private static void dfs(String[][] tickets, String str,int cnt) {
		sb+=str+',';
		if(tickets.length==cnt) {
			answer.add(sb);
			return;
		}
		for(int i=0;i<tickets.length;i++) {
			if(!V[i] && tickets[i][0].equals(str)) {
				V[i]=true;
				dfs(tickets,tickets[i][1],cnt+1);
				V[i]=false;
				sb=sb.substring(0,sb.length()-4);
			}
		}
		
	}

}

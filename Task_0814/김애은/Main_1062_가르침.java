package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1062_가르침 {
	static int N,K;
	static String[] arr;
	static boolean V[] = new boolean[26];
	static int max = Integer.MIN_VALUE
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		arr=new String[N];
		if(K<5) System.out.println(0);
		else if(K==26)System.out.println(N);
		else {
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				arr[i]=str.substring(4,str.length()-4);
			}
			K-=5;
			V['a'-'a']=true;
			V['n'-'a']=true;
			V['t'-'a']=true;
			V['i'-'a']=true;
			V['c'-'a']=true;
			dfs(0,0);
		}
			
	}
	  private static void dfs(int start, int count) {
	        // TODO Auto-generated method stub
	        if(count == K) {
	            int rs = 0;
	            for(int i=0; i<N; i++) {
	                boolean isTrue = true;
	                for(int j=0; j<stArr[i].length(); j++) {
	                    if(!visit[stArr[i].charAt(j)-97]) {
	                        isTrue = false;
	                        break;
	                    }
	                }
	                if(isTrue) {
	                    rs++;
	                }
	            }
	            max = Math.max(max, rs);
	            return;
	        }
	        
	        for(int i=start; i<26; i++) {
	            if(!visit[i]) {
	                visit[i]=true;
	                dfs(i, count+1);
	                visit[i]=false;
	            }
	        }
	    }

}

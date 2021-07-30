package algo0729;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15723_N단논법 {
	static int N,M, cnt=0;
	static char a,b;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
	    N = Integer.parseInt(br.readLine());
	    parent = new int[27];	    
	    
	    for (int i = 0; i < N; i++) {
	    	st = new StringTokenizer(br.readLine()," ");
	    	
	    	a = st.nextToken().charAt(0);
	    	st.nextToken();
	        b = st.nextToken().charAt(0);
	        parent[a-'a'+1] = b-'a'+1;
	    }
	    
	    M = Integer.parseInt(br.readLine());
	    for (int i = 0; i < M; i++) {
	    	st = new StringTokenizer(br.readLine()," ");
	        a = st.nextToken().charAt(0);
	        st.nextToken();
	        b = st.nextToken().charAt(0);
	        a-=('a'-1);
	        b-=('a'-1);
		// 부모 찾
	        while(a!=b && parent[a]!=0) {
	        	a = (char)parent[a];
	        }
	        sb.append(a==b?'T':'F').append("\n");
	    }
	    System.out.println(sb);
	}

}


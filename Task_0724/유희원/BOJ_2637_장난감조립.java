package algo0724;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2637_장난감조립 {
    static class Info{
        int idx,val;
        public Info(int idx, int val){
            this.idx = idx;
            this.val = val;
        }
    }
    
    static int need[],conn[];
    static boolean isElement[];
    static int node,query,result;
    static List<Info> li[];
    
	public static void main (String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String str = br.readLine();
	    StringTokenizer st = new StringTokenizer(str);
	    node = Integer.parseInt(st.nextToken());
	    
	    //초기화
	    result=0;
	    need = new int[node+1];
	    conn = new int[node+1];
	    isElement = new boolean[node+1];
	    li = new List[node+1];
	    for(int i=1;i<=node;i++){
	        li[i] = new ArrayList<>();
	        isElement[i]=true;
	    }
	    
	    str = br.readLine();
	    st = new StringTokenizer(str);
	    query = Integer.parseInt(st.nextToken());
	    
	    for(int i=0;i<query;i++){
	        str = br.readLine();
	        st = new StringTokenizer(str);
	        int a = Integer.parseInt(st.nextToken());
	        int b = Integer.parseInt(st.nextToken());
	        int val = Integer.parseInt(st.nextToken());
	        conn[b]++;
	        li[a].add(new Info(b,val));
	        isElement[a]=false;
	    }
	    Queue<Integer> q = new LinkedList<>();
	    q.offer(node);
	    need[node]=1;
	    while(!q.isEmpty()){
	        int cidx = q.poll();
	        int cv = need[cidx];
	        for(int i=0;i<li[cidx].size();i++){
	            int next = li[cidx].get(i).idx;
	            int nv = li[cidx].get(i).val;
	            conn[next]--;
	            need[next]+=(cv*nv);
	            if(conn[next]==0) q.offer(next);
	        }
	    }
	    for(int i=1;i<=node;i++){
	        if(isElement[i]==true){
	            System.out.println(i+" "+need[i]);
	        }
	    }
	}
}

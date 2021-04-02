import java.util.*;

	public class 그래프_가장먼노드 {

	    public int solution(int n, int[][] edge) {
	        int answer = 0;
	        int count = 1;
	        boolean[] arr = new boolean[n];
	        arr[0] = true;
	        
	        Queue<Integer> tree = new LinkedList<>();
	        tree.add(1);
	        
	        while(!tree.isEmpty())
	        {
	            int countNext = 0;
	            
	            for(int i = 0; i < count; i++)
	            {
	                int num = tree.poll();
	                
	                for(int j = 0; j < edge.length; j++)
	                {
	                    if(edge[j][0] == num)
	                    {
	                        if(arr[edge[j][1] - 1] == false)
	                        {
	                            arr[edge[j][1] - 1] = true;
	                            tree.add(edge[j][1]);
	                            countNext++;
	                        }
	                    }
	                    
	                    else if(edge[j][1] == num)
	                    {
	                        if(arr[edge[j][0] - 1] == false)
	                        {
	                            arr[edge[j][0] - 1] = true;
	                            tree.add(edge[j][0]);
	                            countNext++;
	                        }
	                    }
	                }    
	            }
	            
	            if(countNext == 0)
	                return count;
	            
	            count = countNext;
	        }
	        return answer;
	    }
	
}

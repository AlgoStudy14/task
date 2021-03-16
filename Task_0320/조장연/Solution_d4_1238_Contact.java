import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_d4_1238_Contact {
	static int data, start;
	static int T = 10;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		for (int t = 1; t <= T; t++) {
			data = sc.nextInt();
			start = sc.nextInt();
			ArrayList<int[]> edgeList = new ArrayList<>();
			ArrayList<Integer> nodeList = new ArrayList<>();
			Queue<Integer> queue = new LinkedList<>();
			int max = Integer.MIN_VALUE;
			queue.offer(-1);
			queue.offer(start);
			
			for (int i = 0; i < data/2; i++) {
				int[] edge = new int[2];
				edge[0] = sc.nextInt();
				edge[1] = sc.nextInt();
				
				if(!nodeList.contains(edge[0]))
					nodeList.add(edge[0]);
				if(!nodeList.contains(edge[1]))
					nodeList.add(edge[1]);
				
				edgeList.add(edge);
			}
			int startIndex = nodeList.indexOf(start);
			nodeList.remove(startIndex);
			
			while(true)
			{
				int queueNum = queue.poll();
				if(queueNum == -1 && queue.isEmpty())
				{
					System.out.println("#" + t + " " + max);
					break;
				}
				else if(queueNum == -1 && !(queue.isEmpty()))
				{
					max = Integer.MIN_VALUE;
					queue.offer(-1);
				}
				else if(queueNum != -1)
				{
					max = Math.max(max, queueNum);
				}
				
				
				for (int i = 0; i < edgeList.size(); i++) {
					if(edgeList.get(i)[0] == queueNum && nodeList.contains(edgeList.get(i)[1]))
					{
						queue.offer(edgeList.get(i)[1]);
						int num = nodeList.indexOf(edgeList.get(i)[1]);
						nodeList.remove(num);
						edgeList.remove(i);
						i--;
					}
				}
			}
		}
	}

}

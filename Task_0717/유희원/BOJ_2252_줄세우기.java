package algo0717;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 /*
  * 위상정렬 대표적인 문제
  */
public class BOJ_2252_줄세우기 {
 
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        int[] arr = new int[N+1];
        ArrayList<Integer>[] list = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<Integer>();
        }
        for(int i=0; i<M; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            list[x].add(y);
            arr[y]++;
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i=1; i<=N; i++){
            if(arr[i]==0){
                queue.add(i);
            }
        }
        while(!queue.isEmpty()){
            System.out.print(queue.peek()+" ");
            int current = queue.poll();
            
            for(int i=0; i<list[current].size(); i++){
                int next = list[current].get(i);
                arr[next]--;
                if(arr[next]==0){
                    queue.add(next);
                }
            }
        }
        
    }
 
}


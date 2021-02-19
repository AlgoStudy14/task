import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int end =0;
        int jobsIdx = 0;
        int count = 0;
        
        //요청시간 기준 오름차순 정렬
        Arrays.sort(jobs, (o1,o2)->o1[0]-o2[0]);
        //처리시간 기준 오름차순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->o1[1]-o2[1]);
        //하나의 작업이 완료되는 시점까지 들어온 모든 요청을 큐에 넣음
        while(count<jobs.length){
            while(jobsIdx<jobs.length&&jobs[jobsIdx][0]<=end){
                pq.add(jobs[jobsIdx++]);
            }
            //비어있다 = 앞에 작업 끝나고 다시 들어오는 상황.
            if(pq.isEmpty()){
            //end를 요청의 가장 처음으로 맞춰주기
                end = jobs[jobsIdx][0];
            }else{ //그게 아니면 끝나기 전에 들어온 요청이니까 그중에 수행시간 가장 짧은애부터 수행
                int[] temp = pq.poll();
                answer += temp[1] + end-temp[0];
                end+=temp[1];
                count++;
            }
        }
            
        //버림: Math.floor
        return (int)Math.floor(answer/jobs.length);
    }
}

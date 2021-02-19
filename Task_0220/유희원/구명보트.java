import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
       int answer = 0;
        int min = 0;
        int max = people.length-1;
        Arrays.sort(people);
        while(min<=max){
            //max--해가며 둘이 갈수있나 체크. 둘이 갈수 있으면 최솟값 위치(min) ++
            if(people[min]+people[max]<=limit) min++;
            answer++;
            max--;
        }
        return answer;
        
    }
}

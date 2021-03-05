/*
 * <문제 요약>
 * 주어진 수들을 적절히 더하거나 빼서 원하는 숫자 만들기
 * 
 * <풀이법 요약>
 * DFS 방법으로 한글자 한글자 더하거나 빼서 전체 경우의 수 탐색(완전탐색)
 */

public class DFS_타겟넘버 {
    static int answer = 0;    
    
	public static void main(String[] args) {
		DFS_타겟넘버 pm = new DFS_타겟넘버();
		int[] numbers = { 1, 1, 1, 1, 1 };
		int target = 3;
		System.out.println(pm.solution(numbers, target));
	}

	public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }
    
     public void dfs (int[] numbers, int target, int count, int num)
    {
        if(count < numbers.length)
        {
            int num2 = num + numbers[count];
            int num3 = num - numbers[count];
            dfs(numbers, target, count+1, num2);
            dfs(numbers, target, count+1, num3);
        }
        else
        {
            if(num == target)
                answer++;
        }
    }
}

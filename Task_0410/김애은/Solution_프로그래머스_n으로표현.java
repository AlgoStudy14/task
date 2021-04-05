package algo0402;
/*
 * dfs로 풀었다.
 * dp는 자꾸 틀려서
 * 종료 조건은 2가지
 * cnt가 8넘어가면 -1 return
 * cnt가 8이하이면 dfs를 반복한다.
 */
public class Solution_프로그래머스_n으로표현 {
	static int result=Integer.MAX_VALUE;
	public static void main(String[] args) {
		int N= 5;
		int number=12;
		System.out.println(solution(N,number));
		System.out.println(result);
	}
	    public static int solution(int N, int number) {
	        dfs(0,0,N,number);
	        if(result>8) result=-1; //8이상이면 -1
	        return result;
	    }
	    private static void dfs(int cnt, int res, int n, int number) {
			if(cnt>8) //최솟값이 8보다 크면 -1 return
				return;
			if(res==number) {
				result=Math.min(cnt, result);
				return;
			}
			int temp=n;
			for(int i=1;i<=8;i++) {
				dfs(cnt+i,res+temp,n,number);
				dfs(cnt+i,res-temp,n,number);
				dfs(cnt+i,res/temp,n,number);
				dfs(cnt+i,res*temp,n,number);
				temp=temp*10+n;
			}
		}

	}


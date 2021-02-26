import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int L = sc.nextInt();
		int index = 0;
		int cnt = 0;
		int ans =0;
		int[] arr = new int[N];
		while(true) {
      //공 받은 횟수가 짝수인지 홀수인지 판단해야됨
			cnt = ++arr[index];
      //짝수면 시계방향, 뒤로 가야되는데 범위 안벗어나게 나머지 연산
			if(cnt%2==0) index = (index+L)%N;
      //홀수면 반대방향인데 인덱스가 -가 없으니까 음수일 경우 N을 더해줌
			else {
				index -=L;
				if(index<0) index = N+index;
			}
      //M번 돌리면 break!
			if (cnt==M) break;
			ans++;
		}
		System.out.println(ans);

	}

}

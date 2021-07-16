package algo0717;

import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.BufferedReader;

//투포인터, 원형: N,M이 같을땐 도둑질 방법이 하나임!! 주의!!
public class BOJ_13422_도둑 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);
            int K = Integer.parseInt(input[2]);
            int[] house = new int[N];
            int sum = 0;

            input = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                house[j] = Integer.parseInt(input[j]);
                if(j<M)
                    sum += house[j];
            }
            int left = 0;
            int right = M;

            if(N==M) {
                if(sum<K)
                    System.out.println(1);
                else
                    System.out.println(0);
            }

            else {
                int cnt = 0;

                while(left<N) {
                    if(sum<K)
                        cnt++;

                    sum -= Integer.parseInt(input[left]);
                    sum += Integer.parseInt(input[right%N]);
                    left++;
                    right++;
                }

                System.out.println(cnt);
            }
        }
    }
}

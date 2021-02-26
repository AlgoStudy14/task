package imprepare.problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_1234_비밀번호 {
	

	static int[] arr;
	public static void main(String[] args) throws Exception {
		
		//이거 for문 밑에 넣었더니 똘끼
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <=10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String s = st.nextToken();
			Stack<Integer> stack = new Stack<>();
			for (int i = 0; i <N; i++) {
				
				if(stack.isEmpty()) stack.push(s.charAt(i)-'0');
				else {
          //넣으려는 수랑 스택peek(직전에 넣은수)가 같으면 안넣고 스택 pop
					if(stack.peek()==s.charAt(i)-'0') {
						stack.pop();
						continue;
					}
					stack.push(s.charAt(i)-'0');
				}
			}
			StringBuilder sb = new StringBuilder();
			while(!stack.isEmpty()) {
        //스택은 반대로 나오니까 insert로 앞에다 
				sb.insert(0, stack.pop());
			}
			
			
			System.out.println("#"+t+" "+sb);
		}

	}

}

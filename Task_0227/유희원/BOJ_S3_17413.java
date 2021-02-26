package imprepare.problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17413_단어뒤집기2 {

	static String[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," |<|>",true);
		arr = new String[st.countTokens()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = st.nextToken();
		}
		for (int i = 0; i < arr.length; i++) {
			//처음에 arr[i]=="<" 해서 태그고 뭐고 걍 다 뒤집. 제발!!!!!!!!!!!!!
			if(arr[i].equals("<")) {
				System.out.print(arr[i]);
				int j=0;
        //닫는 태그 만날때까지 이 사이 부분은 그대로 출력하기 위한 부분
				for (j=i+1;!arr[j].equals(">"); j++) {
					System.out.print(arr[j]);
				}
				//System.out.print(arr[j]);
				
				//i=j+1 했었는데 그럼 <><>처럼 태그가 두번 연속인걸 못잡아서 틀림. 테케 없었으면 못풀었을듯
				i=j;
				if(i>=arr.length) break;
				
			}
			//태그가 아닌 부분 거꾸로 출력해주는 부분
			for (int j = arr[i].length()-1; j >=0; j--) {
				System.out.print(arr[i].charAt(j));
			}
			
		}
		
		
	}

}

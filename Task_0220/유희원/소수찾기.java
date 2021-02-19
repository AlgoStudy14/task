import java.util.*;

class Solution {
    public static int answer;
    public static Set<Integer> totalNumbers; 
    
    public int solution(String numbers) {
        answer = 0;
        
        totalNumbers = new HashSet<>();
        int[] numbersArr = new int[numbers.length()];
        boolean[] usedNumbers = new boolean[numbers.length()];
        
        for(int i=0; i<numbersArr.length; i++){
            numbersArr[i] = Integer.parseInt(numbers.charAt(i) + "");
            usedNumbers[i] = false;
        }

        for(int r=1; r<=numbersArr.length; r++){
            getResult(numbersArr, usedNumbers, "", r, 0);
        }
        
        return answer;
    }
    
    public static void getResult(int[] numbersArr, boolean[] usedNumbers, String num, int r, int current){
        
        if(current >= r){
            // 숫자를 totalNumbers에서 있는지 Integer형으로 찾아봄(중복 확인)
            // 없으면 저장하고 숫자가 소수인지 판단
            if(!totalNumbers.contains(Integer.parseInt(num))){
                totalNumbers.add(Integer.parseInt(num));
                
                // 소수 판단
                if(isPrime(Integer.parseInt(num))){
                    answer++;
                    //System.out.println(num);
                }
                    
            }
            return;
            
        }
        else{
            for(int i=0; i<numbersArr.length; i++){
                //System.out.println(current+ ": " + i + ", " + numbersArr[i] );
                if(!usedNumbers[i]){
                    num += Integer.toString(numbersArr[i]);
                    usedNumbers[i] = true;
                    getResult(numbersArr, usedNumbers, num, r, current+1);
                    usedNumbers[i] = false;
                    num = num.substring(0, num.length()-1);
                }
                
            }
        }
    }
    
    public static boolean isPrime(int num){
		int sqrt = (int) Math.sqrt(num);

		// 2는 유일한 짝수 소수
		if (num == 2)
			return true;

		// 짝수와 1은 소수가 아님
		if (num % 2 == 0 || num == 1)
			return false;

		// 제곱근까지만 홀수로 나눠보면 됨
		for (int i = 3; i <= sqrt; i += 2) {
			if (num % i == 0)
				return false;
		}

		return true;
    }
}

class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftSum =0;
        int rightSum = 0;
        int leftQ =0;
        int rightQ = 0;
        for(int i=0; i<n; i++){
        char c = num.charAt(i);
        if(c == '?'){
            if(i < n / 2){
              leftQ++;
            }else{
                rightQ++;
            }
        }else{
            int digit = c - '0';
            if( i < n / 2){
                leftSum += digit;
            }else{
                rightSum += digit;
            }
        }
        }
        if((leftQ + rightQ) %2 == 1){
            return true;
        }
        if(leftSum - rightSum != 9 *(rightQ - leftQ)/ 2){
            return true;
        }
        return false;
    }
}
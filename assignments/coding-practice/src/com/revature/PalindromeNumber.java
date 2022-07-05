package com.revature;

public class PalindromeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static boolean isPalindrome(int x) {
        String test = Integer.toString(x);
        char[] chars = test.toCharArray();
        for(int i=0; i<chars.length/2; i++){
            if(chars[i] != chars[chars.length-1-i]){
                return false;
            }
        }
        return true;
    }

}

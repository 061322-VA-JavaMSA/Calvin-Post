package com.revature;

public class RomanToInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int romanToInt(String s) {
        int num = 0;
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length - 1; i ++) {
            Roman cur = Roman.valueOf(String.valueOf(chars[i]));
            Roman next = Roman.valueOf(String.valueOf(chars[i + 1]));
            if(next.greaterThan(cur)) {
                num -= cur.val();
            } else {
                num += cur.val();
            }
        }
        num += Roman.valueOf(String.valueOf(chars[chars.length - 1])).val();
        return num;
    }
    
    public enum Roman {
        I(1), V(5), X(10), L(50), C(100), D(500), M(1000);
        
        private int val;
        
        Roman(int val) {
            this.val = val;
        }
        
        public boolean greaterThan(Roman r) {
            return this.val > r.val;
        }
        
        public int val(){
            return val;
        }

    }
}

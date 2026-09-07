class Solution {
    public int distinctSubseqII(String s) {
                int MOD = 1000000007;

        long total = 0;
        long[] end = new long[26];

        for (char c : s.toCharArray()) {
            int index = c - 'a';

            long newSubsequences = (total + 1) % MOD;

            total = (total + newSubsequences - end[index] + MOD) % MOD;

            end[index] = newSubsequences;
        }

        return (int) total;

    }
}
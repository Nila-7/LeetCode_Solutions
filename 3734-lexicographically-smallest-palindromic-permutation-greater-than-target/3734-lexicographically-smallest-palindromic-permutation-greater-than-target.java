class Solution {
    public String lexPalindromicPermutation(String s, String target) {
         String calendrix = s;

        int[] count = new int[26];

        // Count characters
        for (char c : calendrix.toCharArray()) {
            count[c - 'a']++;
        }

        // Check whether a palindrome is possible
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        // We only need to construct the left half.
        int[] halfCount = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        int halfLength = s.length() / 2;
        StringBuilder left = new StringBuilder();

        // Build the left half
        for (int pos = 0; pos < halfLength; pos++) {

            boolean chosen = false;

            // Try characters from smallest to largest
            for (int c = 0; c < 26; c++) {

                if (halfCount[c] == 0) {
                    continue;
                }

                // Temporarily use this character
                halfCount[c]--;
                left.append((char) ('a' + c));

                // Build the largest possible remaining palindrome
                StringBuilder maxLeft = new StringBuilder(left);

                for (int x = 25; x >= 0; x--) {
                    for (int j = 0; j < halfCount[x]; j++) {
                        maxLeft.append((char) ('a' + x));
                    }
                }

                String leftPart = maxLeft.toString();

                StringBuilder palindrome = new StringBuilder();
                palindrome.append(leftPart);

                if (s.length() % 2 == 1) {
                    palindrome.append(middle);
                }

                palindrome.append(new StringBuilder(leftPart).reverse());

                // Is there ANY possible palindrome greater than target?
                if (palindrome.toString().compareTo(target) > 0) {
                    chosen = true;
                    break;
                }

                // This character cannot lead to an answer.
                left.deleteCharAt(left.length() - 1);
                halfCount[c]++;
            }

            if (!chosen) {
                return "";
            }
        }

        // Construct final palindrome
        String leftPart = left.toString();

        StringBuilder answer = new StringBuilder();
        answer.append(leftPart);

        if (s.length() % 2 == 1) {
            answer.append(middle);
        }

        answer.append(new StringBuilder(leftPart).reverse());

        String result = answer.toString();

        if (result.compareTo(target) > 0) {
            return result;
        }

        return "";
    }
}
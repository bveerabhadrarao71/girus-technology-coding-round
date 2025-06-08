public class BitMatPat {

    public static int NxtHigBit(int num) {
        //checking the edge case 
        if (num == 0) return -1;

        int count = num;
        int count0 = 0; // counting will starts with 0's
        int count1 = 0; // counting of 1's stats with 0's

        // counting will starts with 0's
        while (((count & 1) == 0) && (count != 0)) {
            count0++;
            count >>= 1;
        }

        // counting of 1's stats with 0's
        while ((count & 1) == 1) {
            count1++;
            count >>= 1;
        }
        if (count0 + count1 == 31 || count0 + count1 == 0) return -1;

        // Position of rightvalue non-scalling 0's (position)
        int position = count0 + count1;

        // Step 1: change rightvalue  for non-scalling 0's
        num |= (1 << position);

        // Step 2: Clear all bits to the rightside  of position to instant position
        num &= ~((1 << position) - 1);

        // Step 3: count1-1 1's on the rightside
        num |= (1 << (count1 - 1)) - 1;

        return num;
    }

    public static void main(String[] args) {
        int num1 = 8; // Binary value of 8: 1000 -> Output: 16 (10000)
        int num2 = 9; // Binary value of 9: 1001 -> Output: 10 (1010)
        int num3 = 143; // Binary value of 143 : 10001111 -> Output: 151 (10010111)

        System.out.println(" Value of Next for 8 is : " + NxtHigBit(num1));
        System.out.println(" Value of Next for 9 is : " + NxtHigBit(num2));
        System.out.println(" Value of Next for 143 is : " + NxtHigBit(num3));
    }
}
//1000
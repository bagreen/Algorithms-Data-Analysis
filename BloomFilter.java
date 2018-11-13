import edu.princeton.cs.algs4.StdOut;

public class BloomFilter<T> {
    // creates private variables that will be explained and changed in the object method below
    private long[] filter;
    private int lowMask;
    private int highMask;


    /** creates object BloomFilter
     * filter is our array of bits that are changed based off of what is added
     * lowMask is 16 1's in binary, will use later to the lower half of bits of hash
     * highMask is lowMask moved up 16, so 16 1's in binary with 16 0's behind it
     * this makes it so that when it is anded with a hash, you will get the higher half of bits of a hash
     */
    public BloomFilter() {
        this.filter = new long[1024];
        this.lowMask = (1 << 16) - 1;
        this.highMask = this.lowMask << 16;
    }


    /**
     * adds input url to filter
     * does this by first getting a hash of the url inputted
     * then, using highMask and lowMask, it finds the higher bits and lower bits
     * after this, we find the bit position in the array value that we need to make 1 if it is not already
     * after we find the bit positions, we then make that array value 1 at the location if it isn't already
     */
    void add(String url) {
        // finds the hash using the hashCode library
        int hash = url.hashCode();

        // ands these values to find the higher and lower bits of the hash
        int lowHash = hash & this.lowMask;
        int highHash = hash & this.highMask;

        // need to move highHash over 16 so that it will be at the correct location
        highHash = highHash >>> 16;

        // needs to divide by 64 to break it into 32 bit halfs
        int lowIndex = lowHash / 64;
        int highIndex = highHash / 64;

        // needs to find the remainder of 64 and the higher and lower hashes to find where in the long bit it should be turned on
        int lowBitPosition = lowHash % 64;
        // highBitPosition needs to be a long as ints are limited at 2^16, and highBitPosition will be larger than that
        long highBitPosition = highHash % 64;

        // changes index of the higher and lower hash if they are not already that value
        this.filter[lowIndex] = ((1L << lowBitPosition) | this.filter[lowIndex]);
        this.filter[highIndex] = ((1L << highBitPosition) | this.filter[highIndex]);
    }


    /**
     * this method is nearly identical to add until the last two lines
     * this is because to find which values should be 1's for the array to contain it, we need to calculate a lot of the same values again
     * difference is that instead of turning bits on, we will check to see if the bits are already on
     */
    boolean mightContain(String url) {
        // finds the hash using the hashCode library
        int hash = url.hashCode();

        // ands these values to find the higher and lower bits of the hash
        int lowHash = hash & this.lowMask;
        int highHash = hash & this.highMask;

        // need to move highHash over 16 so that it will be at the correct location
        highHash = highHash >>> 16;

        // needs to divide by 64 to break it into 32 bit halfs
        int lowIndex = lowHash / 64;
        int highIndex = highHash / 64;

        // needs to find the remainder of 64 and the higher and lower hashes to find where in the long bit it should be turned on
        int lowBitPosition = lowHash % 64;
        // highBitPosition needs to be a long as ints are limited at 2^16, and highBitPosition will be larger than that
        long highBitPosition = highHash % 64;

        // finds if the values at the higher and lower hashes indicies is what it should be if the url was added to the filter
        boolean lowCheck = ((this.filter[lowIndex] & (1L << lowBitPosition)) != 0);
        boolean highCheck = ((this.filter[highIndex] & (1L << highBitPosition)) != 0);

        // if one is false, it is impossible/incredibly unlikely that the url was added to the filter, so only true if both are
        return lowCheck && highCheck;
    }


    /*
     * when we store something in our filter, we make 2 bits true
     * to check how many bits are on, we use bitCount, which finds the amount of 1's in a number
     * method then returns how many bits are turned on in all of the values in our filter
     */
    int trueBits() {
        // makes variable to count amount of bit turned on
        int trueBits = 0;

        // checks each value in array, and finds how many bits are 1's using the bitCount method
        for (long bit : this.filter) {
            trueBits+= Long.bitCount(bit);
        }

        // returns total amount of bits that are true
        return trueBits;
    }
}
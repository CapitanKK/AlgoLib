package fenwicktree;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class FenwickTreeSumUpdateRangeTest {

    private final static int COUNT_RANDOM_TESTS = 10000;
    private final static int RANDOM_MAX_LENGTH = 50;
    private final static int COUNT_OPERATIONS = 100;
    private final static int MAX_ADD_VALUE = 1000;

    @Test
    public void testRandom() throws Exception {
        Random rand = new Random();
        for(int t = 0; t < COUNT_RANDOM_TESTS; t++) {
            int n = rand.nextInt(RANDOM_MAX_LENGTH) + 1;
            FenwickTreeSumUpdateRange fenwickTree = new FenwickTreeSumUpdateRange(n);
            FenwickTreeSumUpdateRangeNaiveImpl fenwickTreeNaive = new FenwickTreeSumUpdateRangeNaiveImpl(n);
            for(int i = 0; i < COUNT_OPERATIONS; i++) {
                if(rand.nextInt(2) == 0) {
                    int left = rand.nextInt(n);
                    int right = rand.nextInt(n);
                    if(left > right) {
                        int tmp = left;
                        left = right;
                        right = tmp;
                    }
                    int add = rand.nextInt(MAX_ADD_VALUE);
                    fenwickTree.add(left, right, add);
                    fenwickTreeNaive.add(left, right, add);
                } else {
                    int left = rand.nextInt(n);
                    int right = rand.nextInt(n);
                    if(left > right) {
                        int temp = left;
                        left = right;
                        right = temp;
                    }
                    int sum = fenwickTree.sum(left, right);
                    int sumNaive = fenwickTreeNaive.sum(left, right);
                    assertEquals(sum, sumNaive);
                }
            }
        }
    }
}

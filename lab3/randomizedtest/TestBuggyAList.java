package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> bugL = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                bugL.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int bugSize = bugL.size();
                assertEquals(size, bugSize);
            } else if (operationNumber == 2) {
                assertEquals(L.size() > 0 ? L.removeLast() : null,
                             bugL.size() > 0 ? bugL.removeLast() : null);
            }
        }
    }

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> normalAlist = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();
        for (int i = 0; i < 3; i++) {
            normalAlist.addLast(i + 4);
            buggyAList.addLast(i + 4);
        }

        for (int i = 0; i < 3; i++) {
            assertEquals(normalAlist.removeLast(), buggyAList.removeLast());
        }
    }
}

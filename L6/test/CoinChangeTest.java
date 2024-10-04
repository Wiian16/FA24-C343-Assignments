import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class CoinChangeTest {

    @Test
    void minChange() {
        assertEquals(0, CoinChange.minChange(0));
        assertEquals(1, CoinChange.minChange(1));
        assertEquals(1, CoinChange.minChange(5));
        assertEquals(1, CoinChange.minChange(10));
        assertEquals(1, CoinChange.minChange(25));
        assertEquals(2, CoinChange.minChange(6));
        assertEquals(3, CoinChange.minChange(31));
    }

    //TODO: More test cases for each method here
    @Test
    void minChangeReg() {
        assertEquals(0, CoinChangeTopDown.minChangeReg(0));
        assertEquals(1, CoinChangeTopDown.minChangeReg(1));
        assertEquals(1, CoinChangeTopDown.minChangeReg(5));
        assertEquals(1, CoinChangeTopDown.minChangeReg(10));
        assertEquals(1, CoinChangeTopDown.minChangeReg(25));
        assertEquals(2, CoinChangeTopDown.minChangeReg(6));
        assertEquals(3, CoinChangeTopDown.minChangeReg(31));
    }

    @Test
    void minChangeMemo() {
        assertEquals(0, CoinChangeTopDown.minChangeMemo(0));
        assertEquals(1, CoinChangeTopDown.minChangeMemo(1));
        assertEquals(1, CoinChangeTopDown.minChangeMemo(5));
        assertEquals(1, CoinChangeTopDown.minChangeMemo(10));
        assertEquals(1, CoinChangeTopDown.minChangeMemo(25));
        assertEquals(2, CoinChangeTopDown.minChangeMemo(6));
        assertEquals(3, CoinChangeTopDown.minChangeMemo(31));
    }
}
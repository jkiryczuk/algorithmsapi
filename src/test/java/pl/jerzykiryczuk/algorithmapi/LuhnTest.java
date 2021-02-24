package pl.jerzykiryczuk.algorithmapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LuhnTest {

    private LuhnAlgorithmComponent component;

    @BeforeEach
    public void setUp() throws Exception {
        component = new LuhnAlgorithmComponent();
    }

    @Test
    public void shouldCheckValidityTrue(){
        assertTrue(component.checkValidity("924803"));
    }


    @Test
    public void shouldCheckValidityFalse(){
        assertFalse(component.checkValidity("924802"));
    }

    @Test
    public void shouldCalculateNumberWithControlDigit(){
        assertEquals("924803", component.calculateCheckDigit("92480"));
    }

}

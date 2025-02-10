package cz.robotdreams.java.lekce18;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KalkulackaTest {

    @Test
    public void secti() {
        int vysledek = Kalkulacka.sectiCisla(10, 20);
        assertEquals(30, vysledek);
    }

    @Test
    @Disabled
    public void vydel() {
        int vysledek = Kalkulacka.vydelCisla(20, 10);
        assertEquals(2, vysledek);
    }

    @Test
    public void nelzeDelitNulou() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> Kalkulacka.vydelCisla(20, 0) );
        assertEquals("Nelze delit nulou", ex.getMessage());
    }



}

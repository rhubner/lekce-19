package cz.robotdreams.java.lekce18;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class KalkulackaAssertJ {

    @Test
    public void secti() {
        int vysledek = Kalkulacka.sectiCisla(10, 20);
        assertThat(vysledek).isEven();
        assertThat(vysledek).isEqualTo(30);
    }

    @Test
    @Disabled
    public void vydel() {
        int vysledek = Kalkulacka.vydelCisla(20, 10);
        assertThat(vysledek).isEqualTo(30);
    }

    @Test
    public void nelzeDelitNulou() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Kalkulacka.vydelCisla(20, 1))
                .withMessage("Nelze delit nulou");
    }

    @Test
    public void listExample() {
        List<String> seznam = List.of("Honza", "Pepa", "Petr");
        assertThat(seznam).containsExactly("Honza", "Pepa", "Petr");
        assertThat(seznam).contains("Honza", "Pepa");
        assertThat(seznam).containsAnyOf("Honza", "Roman", "Radek");




    }



}

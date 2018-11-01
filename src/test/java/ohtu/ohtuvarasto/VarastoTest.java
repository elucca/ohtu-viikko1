package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }
    
    @Test
    public void toStringNayttaaOikeanTilavuudenJaSaldon() {
        varasto = new Varasto(10, 4);
        assertEquals("saldo = 4.0, vielä tilaa 6.0 asdasd", varasto.toString());
    }

    @Test
    public void virheellistaLisaystaEiTehda() {
        varasto.lisaaVarastoon(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void virheellistaOttamistaEiTehda() {
        varasto = new Varasto(10, 4);
        varasto.otaVarastosta(-2);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldoMeneNollaanKunYritetaanOttaaLiikaa() {
        varasto = new Varasto(10, 4);
        varasto.otaVarastosta(5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaOtetaanKaikkiKunYritetaanOttaaLiikaa() {
        varasto = new Varasto(10, 4);
        assertEquals(4, varasto.otaVarastosta(5), vertailuTarkkuus);
    }

    @Test
    public void varastoTayttyyKunLisataanLiikaa() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenTilavuusNollataan() {
        varasto = new Varasto(-2);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenTilavuusNollataanAlkusaldollisessaVarastossa() {
        varasto = new Varasto(-2, 0);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenAlkusaldoNollataan() {
        varasto = new Varasto(2, -1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoTayttyyKunAlkusaldoYlittaaTilavuuden() {
        varasto = new Varasto(2, 3);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoAlkusaldollisenVaraston() {
        varasto = new Varasto(11, 10);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}

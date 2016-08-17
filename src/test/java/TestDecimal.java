import org.junit.Test;

public class TestDecimal {

    @Test
    public void testDecimal() throws Exception {
//        double d = 125.00001;
//        System.out.println(d);
//        System.out.println(d+0.1);
//        double d2 = Math.round((d + 0.1)*10000000)/10000000;
//        System.out.println(d2);

        Long riskAmount = Math.round(33333333 / 0.0011);
        System.out.println(riskAmount);

    }
}

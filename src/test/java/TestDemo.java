import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class TestDemo {
    @Test
    public void testPass() throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(0);

        String s = "$2a$10$DG96AgOh6H46/G5UnseqE.Gsnejz1q/6nG3U.vwdUFLP0/oVwkaEW";
        System.out.println(passwordEncoder.encode("admin"));
        Assert.assertNotNull(passwordEncoder);
        Assert.assertTrue(passwordEncoder.matches("admin", s));
    }
}
package nl.jaysh.brugnl

import nl.jaysh.brugnl.features.helper.TestApplicationContextInitializer
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [TestApplicationContextInitializer::class])
class BrugnlApplicationTests {

    @Test
    fun contextLoads() {
    }
}


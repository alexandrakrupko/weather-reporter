package weather;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(args = "--integration.openweather.api-key=a1234")
class WeatherReporterApplicationTests {

	@Test
	void contextLoads() {
	}

}

package lotbook.lotbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("lotbook.lotbook.repository")
public class LotbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotbookApplication.class, args);
	}

}

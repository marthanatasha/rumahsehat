package apap.tugas.akhir.rumahsehat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import apap.tugas.akhir.rumahsehat.service.GeneralService;

@SpringBootApplication
public class RumahsehatApplication {

	public static void main(String[] args) {
		GeneralService.testing();
		SpringApplication.run(RumahsehatApplication.class, args);
	}

}

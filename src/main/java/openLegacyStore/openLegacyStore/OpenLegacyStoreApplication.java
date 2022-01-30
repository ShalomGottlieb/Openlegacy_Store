package openLegacyStore.openLegacyStore;

import openLegacyStore.openLegacyStore.MyUtils.Art;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpenLegacyStoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(OpenLegacyStoreApplication.class, args);
		System.out.println(Art.chekMe);
		System.out.println("I had much more ideas and things to approve and add but only 1:30 hours to work at home..");

	}

}

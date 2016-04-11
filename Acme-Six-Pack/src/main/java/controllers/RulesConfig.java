package controllers;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


@Configuration
public class RulesConfig {

//	KieServices kieServices = KieServices.Factory.get();
////	KieContainer kieContainer = kieServices.getKieClasspathContainer();
//	
//	ReleaseId releaseId = kieServices.newReleaseId( "org.Design-and-Testing", "Acme-Six-Pack", "1.0" );
//	KieContainer kieContainer = kieServices.newKieContainer( releaseId );
//	KieScanner kScanner = kieServices.newKieScanner( kieContainer );
//	
//	// Start the KieScanner polling the Maven repository every 10 seconds
//	kScanner.start( 10000L );
//	
//	
////	KieScanner kScanner = kieServices.newKieScanner(kieContainer);
//
//	// Start the KieScanner polling the Maven repository every 10 seconds
////	kScanner.start(1000L);

	
    @Bean
    public KieContainer kieContainer() throws IOException {
        KieServices ks = KieServices.Factory.get();
        return ks.getKieClasspathContainer();
    }
}
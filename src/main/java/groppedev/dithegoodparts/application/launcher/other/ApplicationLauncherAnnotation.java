package groppedev.dithegoodparts.application.launcher.other;

import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import groppedev.dithegoodparts.application.Application;

public class ApplicationLauncherAnnotation 
{
	public static void main(String[] args) 
	{
		Objects.requireNonNull(System.getProperty(ACTIVE_PROFILES_PROPERTY_NAME), 
				"Nessun profilo impostato, impossibile avviare l'applicazione!");

		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		((AnnotationConfigApplicationContext) applicationContext).scan("groppedev");
		applicationContext.refresh();

		System.out.println(String.format("Container di Spring avviato, profili attivi: [%s]", 
				Arrays.toString(applicationContext.getEnvironment().getActiveProfiles())));

		ProviderExample component = applicationContext.getBean(ProviderExample.class);
		component.test();

		applicationContext.close();
	}
}
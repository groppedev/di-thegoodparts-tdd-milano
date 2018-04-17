package groppedev.dithegoodparts.examples.experiments.launcher;

import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import groppedev.dithegoodparts.examples.injectionpoint.FieldInjection;
import groppedev.dithegoodparts.examples.injectionpoint.QualifiedDependent;

public class ApplicationLauncherAnnotation 
{
	public static void main(String[] args) 
	{
		Objects.requireNonNull(System.getProperty(ACTIVE_PROFILES_PROPERTY_NAME), 
				"Nessun profilo impostato, impossibile avviare l'applicazione!");

		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		try
		{
			((AnnotationConfigApplicationContext) applicationContext).scan("groppedev");
			applicationContext.refresh();

			System.out.println(String.format("Container di Spring avviato, profili attivi: [%s]", 
					Arrays.toString(applicationContext.getEnvironment().getActiveProfiles())));

			FieldInjection component = applicationContext.getBean(FieldInjection.class);
			component.execute();

			// Esempio bean qualificati
			applicationContext.getBean(QualifiedDependent.class);
		}
		finally
		{
			applicationContext.close();
		}
	}
}
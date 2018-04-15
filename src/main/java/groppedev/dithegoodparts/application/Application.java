package groppedev.dithegoodparts.application;

import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import groppedev.dithegoodparts.application.spellcheck.ApplicationRuntimeConfiguration;
import groppedev.dithegoodparts.application.spellcheck.Configuration;
import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;
import groppedev.dithegoodparts.domain.lexicon.services.SuggestionStrategyType;
import groppedev.dithegoodparts.domain.spellcheck.SpellChecker;

public class Application 
{
	public static ApplicationAPI startApplication()
	{
		Application app = new Application();
		app.startXMLBasedApp();
		return app.new ApplicationAPI();
	}
	
	Application() {}
	
	private AbstractApplicationContext applicationContext;
			
	AbstractApplicationContext startXMLBasedApp()
	{
		Objects.requireNonNull(System.getProperty(ACTIVE_PROFILES_PROPERTY_NAME), 
				"Nessun profilo impostato, impossibile avviare l'applicazione!");
		
		applicationContext = new ClassPathXmlApplicationContext("injector-config.xml");
		// Autoinjection.
		ComponentSpringProvider componentProvider = applicationContext.getBean(ComponentSpringProvider.class);
		componentProvider.setApplicationContext(applicationContext);
		
		System.out.println(String.format("Container di Spring avviato, profili attivi: [%s]", 
				Arrays.toString(applicationContext.getEnvironment().getActiveProfiles())));
		
		return applicationContext;
	}
	
	AbstractApplicationContext startAnnotationBasedApp() 
	{
		Objects.requireNonNull(System.getProperty(ACTIVE_PROFILES_PROPERTY_NAME), 
				"Nessun profilo impostato, impossibile avviare l'applicazione!");
		
		applicationContext = new AnnotationConfigApplicationContext();
		((AnnotationConfigApplicationContext) applicationContext).scan("groppedev");
		applicationContext.refresh();
		
		System.out.println(String.format("Container di Spring avviato, profili attivi: [%s]", 
				Arrays.toString(applicationContext.getEnvironment().getActiveProfiles())));
		
		return applicationContext;
	}

	void stop()
	{
		applicationContext.close();
	}
	
	public class ApplicationAPI implements AutoCloseable
	{
		public boolean checkWord(Word word, LexiconType lexiconType)
		{
			return newSpellChecker(new Configuration(lexiconType, SuggestionStrategyType.NO_STRATEGY)).check(word);
		}
		
		public Collection<Word> suggestions(Word typo, LexiconType lexiconType, SuggestionStrategyType strategyType)
		{
			return newSpellChecker(new Configuration(lexiconType, strategyType)).suggestions(typo);
		}
		
		private SpellChecker newSpellChecker(Configuration conf) 
		{
			ApplicationRuntimeConfiguration.configure(conf);
			try
			{
				return applicationContext.getBean(SpellChecker.class);
			}
			finally
			{
				ApplicationRuntimeConfiguration.destroy();
			}
		}

		@Override
		public void close()
		{
			stop();
		}
	}
}

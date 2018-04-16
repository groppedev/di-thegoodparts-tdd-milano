package groppedev.dithegoodparts.application;

import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ReflectionUtils;

import groppedev.dithegoodparts.application.spellcheck.ApplicationRuntimeConfiguration;
import groppedev.dithegoodparts.application.spellcheck.Configuration;
import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;
import groppedev.dithegoodparts.domain.lexicon.services.SuggestionStrategyType;
import groppedev.dithegoodparts.domain.spellcheck.SpellChecker;

public class Application 
{
	public static ApplicationStaticAPI startStaticApplication()
	{
		Application app = new Application();
		app.startXMLBasedApp();
		return app.new ApplicationStaticAPI();
	}

	public static ApplicationDynamicAPI startDynamicApplication()
	{
		Application app = new Application();
		app.startXMLBasedApp();
		return app.new ApplicationDynamicAPI();
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

	void stop()
	{
		applicationContext.close();
	}

	public class ApplicationStaticAPI implements AutoCloseable
	{
		public boolean checkWord(Word word)
		{
			return applicationContext.getBean(SpellChecker.class).check(word);
		}

		public Collection<Word> suggestions(Word typo)
		{
			return applicationContext.getBean(SpellChecker.class).suggestions(typo);
		}

		@Override
		public void close()
		{
			stop();
		}
	}

	public class ApplicationDynamicAPI implements AutoCloseable
	{
		public boolean checkWord(Word word, LexiconType lexiconType)
		{
			ApplicationRuntimeConfiguration.configure(new Configuration(lexiconType, SuggestionStrategyType.NO_STRATEGY));
			try
			{
				return applicationContext.getBean(SpellChecker.class).check(word);
			}
			finally
			{
				ApplicationRuntimeConfiguration.destroy();
			}
		}

		public Collection<Word> suggestions(Word typo, LexiconType lexiconType, SuggestionStrategyType strategyType)
		{
			ApplicationRuntimeConfiguration.configure(new Configuration(lexiconType, strategyType));
			try
			{
				return applicationContext.getBean(SpellChecker.class).suggestions(typo);
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

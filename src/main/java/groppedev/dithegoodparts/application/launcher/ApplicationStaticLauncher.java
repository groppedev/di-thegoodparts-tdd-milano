package groppedev.dithegoodparts.application.launcher;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import groppedev.dithegoodparts.application.Application;
import groppedev.dithegoodparts.application.Application.ApplicationStaticAPI;
import groppedev.dithegoodparts.domain.Word;

public class ApplicationStaticLauncher 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStaticLauncher.class);
	
	public static void main(String[] args) 
	{
		try(ApplicationStaticAPI app = Application.startStaticApplication())
		{
			Word pizzaWord = Word.aWord("p");
			boolean isPizzaValid = app.checkWord(pizzaWord);
			LOGGER.info("Parola [{}] trovata nel dizionario? [{}]", pizzaWord, isPizzaValid);
			if(!isPizzaValid)
			{
				Collection<Word> suggestions = app.suggestions(pizzaWord);
				LOGGER.info("Parola [{}] errata, Suggerimenti trovati {}", pizzaWord, suggestions);
				
				Collection<Word> doubleSuggestions = app.suggestions(pizzaWord);
				LOGGER.info("Parola [{}] errata, Suggerimenti trovati {}", pizzaWord, doubleSuggestions);
			}
		}
	}
}

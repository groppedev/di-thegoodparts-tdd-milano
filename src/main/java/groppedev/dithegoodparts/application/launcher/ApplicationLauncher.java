package groppedev.dithegoodparts.application.launcher;

import java.util.Collection;

import groppedev.dithegoodparts.application.Application;
import groppedev.dithegoodparts.application.Application.ApplicationAPI;
import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;
import groppedev.dithegoodparts.domain.lexicon.services.SuggestionStrategyType;

public class ApplicationLauncher 
{
	public static void main(String[] args) 
	{
		try(ApplicationAPI app = Application.startApplication())
		{
			Word pizzaWord = Word.aWord("pisza");
			boolean isPizzaValid = app.checkWord(pizzaWord, LexiconType.ITALIAN_SPORT);
			System.out.println(isPizzaValid);
			if(!isPizzaValid)
			{
				Collection<Word> suggestions = app.suggestions(pizzaWord, LexiconType.ITALIAN_SPORT, SuggestionStrategyType.START_WITH);
				System.out.println(suggestions);
			}
		}
	}
}

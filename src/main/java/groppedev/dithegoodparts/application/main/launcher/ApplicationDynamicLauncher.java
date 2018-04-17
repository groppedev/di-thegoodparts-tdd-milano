package groppedev.dithegoodparts.application.main.launcher;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import groppedev.dithegoodparts.application.Application;
import groppedev.dithegoodparts.application.Application.ApplicationDynamicAPI;
import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;
import groppedev.dithegoodparts.domain.lexicon.services.suggestions.strategy.SuggestionStrategyType;

public class ApplicationDynamicLauncher 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationDynamicLauncher.class);

	public static void main(String[] args) 
	{
		try(ApplicationDynamicAPI app = Application.startDynamicApplication())
		{
			{
				Word pizzaWord = Word.aWord("pizz");
				boolean isPizzaValid = app.checkWord(pizzaWord, LexiconType.ITALIAN_FOOD);
				LOGGER.info("Parola [{}] trovata nel dizionario? [{}]", pizzaWord, isPizzaValid);
				Collection<Word> suggestions = app.suggestions(pizzaWord, LexiconType.ITALIAN_FOOD, SuggestionStrategyType.START_WITH);
				LOGGER.info("Parola [{}], Suggerimenti trovati {}", pizzaWord, suggestions);
			}

			{
				Word calcioWord = Word.aWord("calcio");
				boolean isCalcioValid = app.checkWord(calcioWord, LexiconType.ITALIAN_SPORT);
				LOGGER.info("Parola [{}] trovata nel dizionario? [{}]", calcioWord, isCalcioValid);
				Collection<Word> suggestionsCalcio = app.suggestions(calcioWord, LexiconType.ITALIAN_SPORT, SuggestionStrategyType.START_WITH);
				LOGGER.info("Parola [{}], Suggerimenti trovati {}", calcioWord, suggestionsCalcio);
			}
			
			{
				Word calcioWord = Word.aWord("calcio");
				boolean isCalcioValid = app.checkWord(calcioWord, LexiconType.ITALIAN_FOOD);
				LOGGER.info("Parola [{}] trovata nel dizionario [{}] ? [{}]", calcioWord, LexiconType.ITALIAN_FOOD, isCalcioValid);
				Collection<Word> suggestionsCalcio = app.suggestions(calcioWord, LexiconType.ITALIAN_SPORT, SuggestionStrategyType.END_WITH);
				LOGGER.info("Parola [{}], Suggerimenti trovati nel dizionario [{}] {}", calcioWord, LexiconType.ITALIAN_SPORT, suggestionsCalcio);
			}
		}
	}
}

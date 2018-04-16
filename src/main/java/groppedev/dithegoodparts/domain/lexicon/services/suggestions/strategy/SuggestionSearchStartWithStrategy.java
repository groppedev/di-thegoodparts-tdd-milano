package groppedev.dithegoodparts.domain.lexicon.services.suggestions.strategy;

import groppedev.dithegoodparts.domain.Word;

/**
 * Strategia di ricerca dei suggerimenti che ha stato
 */
public class SuggestionSearchStartWithStrategy implements SuggestionStrategy
{
	private final Word typo;
	
	public SuggestionSearchStartWithStrategy(Word typo) 
	{
		this.typo = typo;
	}

	@Override
	public boolean evaluate(Word aWord) 
	{
		return aWord.startsWith(typo);
	}
}

package groppedev.dithegoodparts.domain.lexicon.services.suggestions.strategy;

import groppedev.dithegoodparts.domain.Word;

/**
 * Strategia di ricerca dei suggerimenti che ha stato
 */
public class SuggestionSearchEndWithStrategy implements SuggestionStrategy
{
	private final Word typo;
	
	public SuggestionSearchEndWithStrategy(Word typo) 
	{
		this.typo = typo;
	}

	@Override
	public boolean evaluate(Word aWord) 
	{
		return aWord.endsWith(typo);
	}
}

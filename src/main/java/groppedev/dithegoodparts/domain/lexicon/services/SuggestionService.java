package groppedev.dithegoodparts.domain.lexicon.services;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.collections4.CollectionUtils.select;

import java.util.Collection;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.Lexicon;

public class SuggestionService 
{
	private final Lexicon lexicon;
	private final SuggestionStrategyFactory strategyFactory;
	
	public SuggestionService(Lexicon lexicon, SuggestionStrategyFactory strategyFactory) 
	{
		requireNonNull(lexicon, "Lessico non impostato");
		requireNonNull(strategyFactory, "Factory delle strategie di suggerimento non impostata");
		
		this.lexicon = lexicon;
		this.strategyFactory = strategyFactory;
	}

	public Collection<Word> searchSuggestions(Word typo)
	{
		return select(lexicon.words(), this.strategyFactory.newStrategy(typo));
	}
}

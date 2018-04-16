package groppedev.dithegoodparts.domain.lexicon.services.suggestions;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.collections4.CollectionUtils.select;

import java.util.Collection;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.Lexicon;
import groppedev.dithegoodparts.domain.lexicon.services.suggestions.strategy.SuggestionStrategyFactory;

public class SuggestionService 
{
	private final Lexicon lexicon;
	private final SuggestionStrategyFactory suggestionStrategyFactory;
	
	public SuggestionService(Lexicon lexicon, SuggestionStrategyFactory suggestionStrategyFactory) 
	{
		requireNonNull(lexicon, "Lessico non impostato");
		requireNonNull(suggestionStrategyFactory, "Factory per la strategia dei suggerimenti non impostato");
		
		this.lexicon = lexicon;
		this.suggestionStrategyFactory = suggestionStrategyFactory;
	}

	public Collection<Word> searchSuggestions(Word typo)
	{
		return select(lexicon.words(), suggestionStrategyFactory.newStrategy(typo));
	}
}

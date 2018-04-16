package groppedev.dithegoodparts.domain.lexicon.services;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.collections4.CollectionUtils.select;

import java.util.Collection;

import groppedev.dithegoodparts.application.ComponentProvider;
import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.Lexicon;

/**
 * Potenzialmente questo componente può essere singleton ed avere delle dipendenze di tipo prototipo.
 */
public class SuggestionService 
{
	private final Lexicon lexicon;
	private final ComponentProvider provider;
	
	public SuggestionService(Lexicon lexicon, ComponentProvider provider) 
	{
		requireNonNull(lexicon, "Lessico non impostato");
		requireNonNull(provider, "Provider della factory per la strategia dei suggerimenti non impostato");
		
		this.lexicon = lexicon;
		this.provider = provider;
	}

	public Collection<Word> searchSuggestions(Word typo)
	{
		// Disaccoppiamento tra gli SCOPE
		SuggestionStrategyFactory factory = suggestionStrategyFactory();
		return select(lexicon.words(), factory.newStrategy(typo));
	}

	private SuggestionStrategyFactory suggestionStrategyFactory() 
	{
		return provider.component(SuggestionStrategyFactory.class);
	}
}

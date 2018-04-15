package groppedev.dithegoodparts.domain.lexicon.services;

import org.apache.commons.collections4.Predicate;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconQueryExecutor;

/**
 * Factory che costruisce le strategie per la ricerca dei suggerimenti.
 * La tipologia di strategia può essere impostata staticamente quando parte l'applicazione, oppure ogni volta che viene 
 * richiesto un oggetto di tipo SuggestionStrategyDecorator
 */
public class SuggestionStrategyFactory 
{
	private final SuggestionStrategyType strategyType;
	private final LexiconQueryExecutor lexiconQueryExecutor;

	public SuggestionStrategyFactory(SuggestionStrategyType strategyType, LexiconQueryExecutor lexiconQueryExecutor) 
	{
		this.strategyType = strategyType;
		this.lexiconQueryExecutor = lexiconQueryExecutor;
	}

	public Predicate<Word> newStrategy(Word typo)
	{
		return new SuggestionStrategyDecorator(lexiconQueryExecutor, selectStrategy(typo));
	}

	private Predicate<Word> selectStrategy(Word typo) 
	{
		switch (strategyType) 
		{
		case START_WITH:
			return new SuggestionSearchStartWithStrategy(typo);
		case END_WITH:
			return new SuggestionSearchEndWithStrategy(typo);
		default:
			throw new IllegalStateException();
		}
	}
}

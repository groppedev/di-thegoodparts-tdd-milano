package groppedev.dithegoodparts.domain.lexicon.services.suggestions.strategy;

import org.apache.commons.collections4.Predicate;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconQueryExecutor;

public class SuggestionStrategyDecorator implements Predicate<Word>
{
	private final LexiconQueryExecutor lexiconQueryExecutor;
	private final Predicate<Word> delegate;

	public SuggestionStrategyDecorator(LexiconQueryExecutor lexiconQueryExecutor, Predicate<Word> delegate) 
	{
		this.lexiconQueryExecutor = lexiconQueryExecutor;
		this.delegate = delegate;
	}

	@Override
	public boolean evaluate(Word word) 
	{
		if(lexiconQueryExecutor.isSuggestableWord(word))
		{
			return this.delegate.evaluate(word);
		}
		return false;
	}
}

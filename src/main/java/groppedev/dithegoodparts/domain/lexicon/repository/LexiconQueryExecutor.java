package groppedev.dithegoodparts.domain.lexicon.repository;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;

public class LexiconQueryExecutor 
{
	private final LexiconRepository lexiconREPO;

	public LexiconQueryExecutor(LexiconRepository lexiconREPO) 
	{
		this.lexiconREPO = lexiconREPO;
	}
	
	public boolean matchWord(LexiconType type, final Word wordToMatch)
	{
		return IterableUtils.matchesAny(lexiconREPO.words(type), new Predicate<Word>() 
		{
			public boolean evaluate(Word word) 
			{
				return wordToMatch.equals(word);
			}
		});
	}
	
	/**
	 * @return true se la {@link Word} in input può essere utilizzata come suggerimento.
	 */
	public boolean isSuggestableWord(Word word)
	{
		return true;
	}
}

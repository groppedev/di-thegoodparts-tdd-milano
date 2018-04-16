package groppedev.dithegoodparts.domain.lexicon;

import java.util.Collection;
import java.util.Objects;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconQueryExecutor;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconRepository;

public class LexiconTyped implements Lexicon
{
	private final LexiconType lexiconType;
	private final LexiconRepository lexiconREPO;
	private final LexiconQueryExecutor lexiconQE;
	
	public LexiconTyped(LexiconType type, LexiconRepository lexiconREPO, LexiconQueryExecutor lexiconQE) 
	{
		Objects.requireNonNull(type, "Tipologia di lessico non impostata");
		Objects.requireNonNull(lexiconREPO, "Repository del lessico non impostato");
		Objects.requireNonNull(lexiconQE, "Query Executor del lessico non impostato");

		this.lexiconType = type;
		this.lexiconQE = lexiconQE;
		this.lexiconREPO = lexiconREPO;
	}

	@Override
	public boolean hasWord(Word word) 
	{
		return lexiconQE.matchWord(lexiconType, word);
	}

	@Override
	public Collection<Word> words()
	{
		return lexiconREPO.words(lexiconType);
	}

	@Override
	public String toString()
	{
		return "Lexicon [type=" + lexiconType + ", words=[" + words() + "]]";
	}
}

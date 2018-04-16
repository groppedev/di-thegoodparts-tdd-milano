package groppedev.dithegoodparts.domain.lexicon.factory.other;

import groppedev.dithegoodparts.domain.lexicon.LexiconItalianFood;
import groppedev.dithegoodparts.domain.lexicon.LexiconItalianSport;
import groppedev.dithegoodparts.domain.lexicon.Lexicon;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;
import groppedev.dithegoodparts.domain.lexicon.factory.LexiconFactory;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconQueryExecutor;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconRepository;

public class LexiconStandardFactory implements LexiconFactory
{
	private final LexiconRepository lexiconREPO;
	private final LexiconQueryExecutor lexiconQE;
	
	private final LexiconType lexiconType;
	
	public LexiconStandardFactory(LexiconType lexiconType, LexiconRepository lexiconREPO, LexiconQueryExecutor lexiconQE) 
	{
		this.lexiconType = lexiconType;
		
		this.lexiconREPO = lexiconREPO;
		this.lexiconQE = lexiconQE;
	}

	@Override
	public Lexicon newLexicon() 
	{
		switch (lexiconType) 
		{
		case ITALIAN_FOOD:
			return new LexiconItalianFood(lexiconREPO, lexiconQE);
		case ITALIAN_SPORT:
			return new LexiconItalianSport(lexiconREPO, lexiconQE);
		default:
			throw new IllegalStateException();
		}
	}
}

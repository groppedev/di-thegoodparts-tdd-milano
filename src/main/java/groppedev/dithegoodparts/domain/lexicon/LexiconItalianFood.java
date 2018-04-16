package groppedev.dithegoodparts.domain.lexicon;

import static groppedev.dithegoodparts.domain.lexicon.LexiconType.ITALIAN_FOOD;

import java.util.Collection;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconQueryExecutor;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconRepository;


public class LexiconItalianFood implements Lexicon
{
	private final LexiconTyped myLexicon;
	
	public LexiconItalianFood(LexiconRepository lexiconREPO, LexiconQueryExecutor lexiconQE) 
	{
		this.myLexicon = new LexiconTyped(ITALIAN_FOOD, lexiconREPO, lexiconQE);
	}

	@Override
	public boolean hasWord(Word word) 
	{
		return myLexicon.hasWord(word);
	}
	
	@Override
	public Collection<Word> words() 
	{
		return myLexicon.words();
	}
	
	@Override
	public String toString() 
	{
		return "ItalianFoodLexicon [myLexicon=" + myLexicon + "]";
	}
}

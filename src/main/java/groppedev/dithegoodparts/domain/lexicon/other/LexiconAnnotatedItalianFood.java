package groppedev.dithegoodparts.domain.lexicon.other;

import java.util.Collection;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.Lexicon;
import groppedev.dithegoodparts.domain.lexicon.LexiconMeta;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;
import groppedev.dithegoodparts.domain.lexicon.LexiconTyped;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconQueryExecutor;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconRepository;

@LexiconMeta(type = LexiconType.ITALIAN_FOOD)
public class LexiconAnnotatedItalianFood implements Lexicon
{
	private final LexiconTyped myLexicon;

	public LexiconAnnotatedItalianFood(LexiconRepository lexiconREPO, LexiconQueryExecutor lexiconQE) 
	{
		this.myLexicon = new LexiconTyped(getClass().getAnnotation(LexiconMeta.class).type(), lexiconREPO, lexiconQE);
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

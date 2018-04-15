package groppedev.dithegoodparts.domain.lexicon.repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;

public class LexiconInMemoryRepository implements LexiconRepository 
{
	private final Set<LexiconWord> lexiconWords;

	public LexiconInMemoryRepository() 
	{
		lexiconWords = new HashSet<LexiconWord>();
	}

	public void init()
	{
		lexiconWords.add(new LexiconWord(LexiconType.ITALIAN_FOOD, Word.aWord("pizza")));
		lexiconWords.add(new LexiconWord(LexiconType.ITALIAN_FOOD, Word.aWord("lasagne")));
		lexiconWords.add(new LexiconWord(LexiconType.ITALIAN_FOOD, Word.aWord("pasta")));

		lexiconWords.add(new LexiconWord(LexiconType.ITALIAN_SPORT, Word.aWord("calcio")));
		lexiconWords.add(new LexiconWord(LexiconType.ITALIAN_SPORT, Word.aWord("pallavolo")));
		lexiconWords.add(new LexiconWord(LexiconType.ITALIAN_SPORT, Word.aWord("pallacanestro")));
	}
	
	@Override
	public Collection<Word> words(LexiconType type) 
	{
		return LexiconUtils.toWord(type, lexiconWords);
	}
}

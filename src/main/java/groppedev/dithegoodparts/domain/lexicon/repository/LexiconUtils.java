package groppedev.dithegoodparts.domain.lexicon.repository;

import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;

/**
 * Classe statica di utilità che non ha dipendenze esterne e non deve mai essere sostituita in fase di test.
 * 
 * NON va gestita tramite l'injector!
 */
public class LexiconUtils 
{
	public static Collection<Word> toWord(LexiconType type, Collection<LexiconWord> lexicondWords)
	{
		Collection<LexiconWord> currentTypeWords = CollectionUtils.select(lexicondWords, new Predicate<LexiconWord>()
		{
			public boolean evaluate(LexiconWord lexiconWord) 
			{
				return lexiconWord.matchesType(type);
			}
		});
		
		return CollectionUtils.collect(currentTypeWords, new Transformer<LexiconWord, Word>()
		{
			@Override
			public Word transform(LexiconWord lexiconWord) 
			{
				return lexiconWord.toWord();
			}
		});

	}
}

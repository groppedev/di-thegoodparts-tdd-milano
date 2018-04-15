package groppedev.dithegoodparts.domain.lexicon.repository;

import java.util.Collection;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;

public interface LexiconRepository 
{
	Collection<Word> words(LexiconType type);
}

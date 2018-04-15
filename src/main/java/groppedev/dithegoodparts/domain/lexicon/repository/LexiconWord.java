package groppedev.dithegoodparts.domain.lexicon.repository;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;

class LexiconWord
{
	private final LexiconType type;
	private final Word word;
	
	public LexiconWord(LexiconType type, Word word) 
	{
		this.type = type;
		this.word = word;
	}
	
	public boolean matchesType(LexiconType type)
	{
		return this.type == type;
	}
	
	public Word toWord()
	{
		return this.word;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LexiconWord other = (LexiconWord) obj;
		if (type != other.type)
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
}
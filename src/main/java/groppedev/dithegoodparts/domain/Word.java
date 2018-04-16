package groppedev.dithegoodparts.domain;

/**
 * Value Object Word
 */
public class Word 
{
	private final String text;
	
	private Word(String text) 
	{
		this.text = text;
	}
	
	public boolean startsWith(Word word)
	{
		return this.text.startsWith(word.text);
	}

	public boolean endsWith(Word word)
	{
		return this.text.endsWith(word.text);
	}
	
	public static Word aWord(String text)
	{
		return new Word(text);
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Word other = (Word) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() 
	{
		return text;
	}
}


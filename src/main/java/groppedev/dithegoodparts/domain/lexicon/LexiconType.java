package groppedev.dithegoodparts.domain.lexicon;

public enum LexiconType 
{
	ITALIAN_FOOD("spellcheck.lexicon.italianFood"),
	ITALIAN_SPORT("spellcheck.lexicon.italianSport"),
	NO_LEXICON("");
	
	private String id;

	private LexiconType(String id) 
	{
		this.id = id;
	}
	
	public String id()
	{
		return this.id;
	}
}

package groppedev.dithegoodparts.domain.lexicon;

public enum LexiconType 
{
	ITALIAN_FOOD
	{
		public Class<LexiconItalianFood> toImplementationType() 
		{
			return LexiconItalianFood.class;
		}
		public String id()
		{
			return "spellcheck.lexicon.italianFood";
		}
	},
	ITALIAN_SPORT 
	{
		@Override
		public Class<LexiconItalianSport> toImplementationType() 
		{
			return LexiconItalianSport.class;
		}
		@Override
		public String id()
		{
			return "spellcheck.lexicon.italianSport";
		}
	},
	NO_LEXICON;
	
	public Class<? extends Lexicon> toImplementationType()
	{
		return null;
	}
	
	public String id()
	{
		return "";
	}
}

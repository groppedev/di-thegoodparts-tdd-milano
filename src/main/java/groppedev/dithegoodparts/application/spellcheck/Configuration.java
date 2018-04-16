package groppedev.dithegoodparts.application.spellcheck;

import groppedev.dithegoodparts.domain.lexicon.LexiconType;
import groppedev.dithegoodparts.domain.lexicon.services.SuggestionStrategyType;

public class Configuration 
{
	public static final Configuration NO_CONFIGURATION = new Configuration(LexiconType.NO_LEXICON, SuggestionStrategyType.NO_STRATEGY);
	
	private final LexiconType lexicon;
	private final SuggestionStrategyType suggestion;
	
	public Configuration(LexiconType lexicon, SuggestionStrategyType suggestion) 
	{
		this.lexicon = lexicon;
		this.suggestion = suggestion;
	}

	public LexiconType lexiconType()
	{
		return lexicon;
	}

	public SuggestionStrategyType suggestionStrategyType() 
	{
		return suggestion;
	}

	@Override
	public String toString() 
	{
		return "Configuration [lexicon=" + lexicon + ", suggestion=" + suggestion + "]";
	}
}

package groppedev.dithegoodparts.application.spellcheck;

import groppedev.dithegoodparts.domain.lexicon.LexiconType;
import groppedev.dithegoodparts.domain.lexicon.services.suggestions.strategy.SuggestionStrategyType;

public class ApplicationRuntimeConfiguration 
{
	private static final ThreadLocal<Configuration> confHolder = new ThreadLocal<>();

	public static void configure(Configuration conf)
	{
		confHolder.set(conf);
	}
	
	public static LexiconType lexiconType()
	{
		return confHolder.get().lexiconType();
	}

	public static SuggestionStrategyType suggestionStrategyType() 
	{
		return confHolder.get().suggestionStrategyType();
	} 
	
	public static void destroy()
	{
		confHolder.remove();
	}
}

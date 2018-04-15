package groppedev.dithegoodparts.domain.spellcheck;

import java.util.Collection;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.Lexicon;
import groppedev.dithegoodparts.domain.lexicon.services.SuggestionService;

/**
 * https://www.amazon.it/gp/product/0134685997/ref=ox_sc_act_title_1?smid=A11IL2PNWYJU7H&psc=1
 *
 */
public class SpellChecker
{
	private final Lexicon lexicon;
	private final SuggestionService suggestionService;
	
	public SpellChecker(Lexicon lexicon, SuggestionService suggestionService) 
	{
		Objects.requireNonNull(lexicon, "Lessico non impostato!");
		Objects.requireNonNull(lexicon, "Servizio per i suggerimenti non impostato!");

		this.lexicon = lexicon;
		this.suggestionService = suggestionService;
	}

	public boolean check(Word wordToCheck)
	{
		return lexicon.hasWord(wordToCheck);
	}
	
	public Collection<Word> suggestions(Word typo)
	{
		if(!lexicon.hasWord(typo))
		{
			return this.suggestionService.searchSuggestions(typo);
		}
		return CollectionUtils.emptyCollection();
	}
}

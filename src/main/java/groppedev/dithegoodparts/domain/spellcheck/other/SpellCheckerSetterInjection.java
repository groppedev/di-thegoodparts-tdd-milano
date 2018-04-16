package groppedev.dithegoodparts.domain.spellcheck.other;

import java.util.Collection;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.Lexicon;
import groppedev.dithegoodparts.domain.lexicon.services.suggestions.SuggestionService;

/**
 * - Non sono evidenti le modifiche nei TEST
 * - Non sono evidenti le modifiche in configurazione.
 * - Bisogna fare la validazione delle dipendenze ovunque.
 * - Si favorisce la mutabilità del servizio
 * - Bisogna gestire la concorrenza.
 */
public class SpellCheckerSetterInjection
{
	private volatile Lexicon lexicon;
	private volatile SuggestionService suggestionService;

	public boolean check(Word wordToCheck)
	{
		Objects.requireNonNull(lexicon, "Lessico non impostato!");
		
		return lexicon.hasWord(wordToCheck);
	}
	
	public Collection<Word> suggestions(Word typo)
	{
		Objects.requireNonNull(lexicon, "Lessico non impostato!");
		Objects.requireNonNull(lexicon, "Servizio per i suggerimenti non impostato!");
		
		if(!lexicon.hasWord(typo))
		{
			return this.suggestionService.searchSuggestions(typo);
		}
		return CollectionUtils.emptyCollection();
	}

	public void setLexicon(Lexicon lexicon) 
	{
		this.lexicon = lexicon;
	}

	public void setSuggestionService(SuggestionService suggestionService) 
	{
		this.suggestionService = suggestionService;
	}
}

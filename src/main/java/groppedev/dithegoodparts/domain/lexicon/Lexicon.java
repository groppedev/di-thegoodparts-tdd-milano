package groppedev.dithegoodparts.domain.lexicon;

import java.util.Collection;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.services.suggestions.SuggestionService;

/**
 * il metodo {@link #words()} viola l'incapsulamento dell'oggetto {@link Lexicon}
 * ma a scopo esemplificativo viene lasciato in interfaccia al posto di incapsulare il 
 * servizio {@link SuggestionService} direttamente in {@link Lexicon}
 */
public interface Lexicon 
{
	boolean hasWord(Word word);
	
	Collection<Word> words();
}

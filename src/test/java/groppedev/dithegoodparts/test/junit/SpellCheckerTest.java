package groppedev.dithegoodparts.test.junit;

import static groppedev.dithegoodparts.domain.Word.aWord;

import java.util.Arrays;
import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.Lexicon;
import groppedev.dithegoodparts.domain.lexicon.services.suggestions.SuggestionService;
import groppedev.dithegoodparts.domain.spellcheck.SpellChecker;

@RunWith(JUnit4.class)
@SuppressWarnings("static-method")
public class SpellCheckerTest 
{
	@Test
	public void isValidTest()
	{
		// Fixture.
		Lexicon lexicon = Mockito.mock(Lexicon.class);
		SuggestionService suggestionService = Mockito.mock(SuggestionService.class);
		// Stubbing
		Mockito.when(lexicon.hasWord(Word.aWord("massimo"))).thenReturn(true);
		// Run Test.
		SpellChecker spellChecker = new SpellChecker(lexicon, suggestionService);
		boolean isValid = spellChecker.check(Word.aWord("massimo"));
		// Assertions.
		Assert.assertTrue(isValid);
	}
	
	@Test
	public void suggestionsTest()
	{
		// Fixture.
		Lexicon lexicon = Mockito.mock(Lexicon.class);
		SuggestionService suggestionService = Mockito.mock(SuggestionService.class);
		// Stubbing
		Mockito.when(lexicon.hasWord(aWord("max"))).thenReturn(false);
		
		Mockito.when(suggestionService.searchSuggestions(aWord("max"))).thenReturn(Arrays.asList(aWord("massimo"), aWord("massimiliano")));
		// Run Test.
		SpellChecker spellChecker = new SpellChecker(lexicon, suggestionService);
		boolean isValid = spellChecker.check(aWord("max"));
		Collection<Word> suggestions = spellChecker.suggestions(aWord("max"));
		// Assertions.
		Assert.assertFalse(isValid);
		Assertions.assertThat(suggestions).hasSize(2);
		Assertions.assertThat(suggestions).containsExactlyInAnyOrder(aWord("massimo"), aWord("massimiliano"));
	}
}

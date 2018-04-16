package groppedev.dithegoodparts.integrationtest;

import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.core.env.AbstractEnvironment;

import groppedev.dithegoodparts.application.Application;
import groppedev.dithegoodparts.application.Application.ApplicationDynamicAPI;
import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;
import groppedev.dithegoodparts.domain.lexicon.services.suggestions.strategy.SuggestionStrategyType;

@RunWith(JUnit4.class)
public class ApplicationDynamicTest 
{
	private static ApplicationDynamicAPI app;
	
	@BeforeClass
	public static void startApplication()
	{
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "TEST,DYNAMIC_APP");
		app = Application.startDynamicApplication();
	}
	
	@AfterClass
	public static void stopApplication()
	{
		app.close();
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "");
	}
	
	@Test
	public void validWordItalianFoodTest()
	{
		// Run Test.
		boolean validWord = app.checkWord(Word.aWord("pizza"), LexiconType.ITALIAN_FOOD);
		// Assertion.
		Assert.assertTrue(validWord);
	}
	
	@Test
	public void validWordItalianSportTest()
	{
		// Run Test.
		boolean validWord = app.checkWord(Word.aWord("calcio"), LexiconType.ITALIAN_SPORT);
		// Assertion.
		Assert.assertTrue(validWord);
	}
	
	@Test
	public void invalidWordItalianFoodTest()
	{
		// Run Test.
		boolean invalidWord = app.checkWord(Word.aWord("nighiri"), LexiconType.ITALIAN_FOOD);
		// Assertion.
		Assert.assertFalse(invalidWord);
	}
	
	@Test
	public void invalidWordItalianSportTest()
	{
		// Run Test.
		boolean invalidWord = app.checkWord(Word.aWord("football americano"), LexiconType.ITALIAN_SPORT);
		// Assertion.
		Assert.assertFalse(invalidWord);
	}
	
	@Test
	public void invalidWordNoSuggestionsTest()
	{
		// Run Test.
		Collection<Word> suggestions = app.suggestions(Word.aWord("paella"), LexiconType.ITALIAN_FOOD, SuggestionStrategyType.START_WITH);
		// Assertion.
		Assertions.assertThat(suggestions).isEmpty();
	}
	
	@Test
	public void invalidWordWithSuggestionsItalianSportTest()
	{
		// Run Test.
		Collection<Word> suggestions = app.suggestions(Word.aWord("p"), LexiconType.ITALIAN_SPORT, SuggestionStrategyType.START_WITH);
		// Assertion.
		Assertions.assertThat(suggestions).containsExactlyInAnyOrder(Word.aWord("pallacanestro"), Word.aWord("pallavolo"));
	}
	
	@Test
	public void invalidWordWithSuggestionsEndsWithItalianSportTest()
	{
		// Run Test.
		Collection<Word> suggestions = app.suggestions(Word.aWord("o"), LexiconType.ITALIAN_SPORT, SuggestionStrategyType.END_WITH);
		// Assertion.
		Assertions.assertThat(suggestions).containsExactlyInAnyOrder(Word.aWord("calcio"), Word.aWord("pallacanestro"), Word.aWord("pallavolo"));
	}
	
	@Test
	public void invalidWordWithSuggestionsItalianFoodTest()
	{
		// Run Test.
		Collection<Word> suggestions = app.suggestions(Word.aWord("p"), LexiconType.ITALIAN_FOOD, SuggestionStrategyType.START_WITH);
		// Assertion.
		Assertions.assertThat(suggestions).containsExactlyInAnyOrder(Word.aWord("pizza"), Word.aWord("pasta"));
	}
}

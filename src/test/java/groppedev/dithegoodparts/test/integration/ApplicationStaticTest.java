package groppedev.dithegoodparts.test.integration;

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
import groppedev.dithegoodparts.application.Application.ApplicationStaticAPI;
import groppedev.dithegoodparts.domain.Word;

/**
 * CONFIGURAZIONE STATICA:
 * 
 * 	spellcheck.lexicon.type=ITALIAN_FOOD
 * 	spellcheck.suggestions.strategy=START_WITH
 */
@RunWith(JUnit4.class)
public class ApplicationStaticTest 
{
	private static ApplicationStaticAPI app;
	
	@BeforeClass
	public static void startApplication()
	{
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "TEST,STATIC_APP");
		app = Application.startStaticApplication();
	}
	
	@AfterClass
	public static void stopApplication()
	{
		app.close();
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "");
	}
	
	@Test
	public void validWordTest()
	{
		// Run Test.
		boolean validWord = app.checkWord(Word.aWord("pizza"));
		// Assertion.
		Assert.assertTrue(validWord);
	}
	
	@Test
	public void invalidWordTest()
	{
		// Run Test.
		boolean invalidWord = app.checkWord(Word.aWord("nighiri"));
		// Assertion.
		Assert.assertFalse(invalidWord);
	}
	
	@Test
	public void invalidWordNoSuggestionsTest()
	{
		// Run Test.
		Collection<Word> suggestions = app.suggestions(Word.aWord("paella"));
		// Assertion.
		Assertions.assertThat(suggestions).isEmpty();
	}
	
	@Test
	public void invalidWordWithSuggestionsTest()
	{
		// Run Test.
		Collection<Word> suggestions = app.suggestions(Word.aWord("p"));
		// Assertion.
		Assertions.assertThat(suggestions).containsExactlyInAnyOrder(Word.aWord("pizza"), Word.aWord("pasta"));
	}
}

package groppedev.dithegoodparts.examples.noioc;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import groppedev.dithegoodparts.domain.Word;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconFileRepository;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconInMemoryRepository;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconQueryExecutor;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconRepository;

@RunWith(JUnit4.class)
public class SpellCheckerNoIoCTest 
{
	@Before
	public void setup()
	{
		// Nei test si vuole utilizzare l'implementazione in memoria.
		LexiconInMemoryRepository lexiconREPO = new LexiconInMemoryRepository();
		LexiconFactory.getInstance().setLexiconREPO(lexiconREPO);
		LexiconFactory.getInstance().setLexiconQE(new LexiconQueryExecutor(lexiconREPO));
		LexiconFactory.getInstance().init();
	}
	@After
	public void teardown()
	{
		// Al termine del test va impostata la vecchia implementazione.
		LexiconRepository lexiconREPO = new LexiconFileRepository();
		LexiconFactory.getInstance().setLexiconREPO(lexiconREPO);
		LexiconFactory.getInstance().setLexiconQE(new LexiconQueryExecutor(lexiconREPO));
		LexiconFactory.getInstance().destroy();
	}
	@Test
	public void test()
	{
		// Run Test
		SpellCheckerNoIoC spellChecker = new SpellCheckerNoIoC();
		boolean isValid = spellChecker.check(Word.aWord("pizza"));
		// Assertion
		Assert.assertTrue(isValid);
	}
}

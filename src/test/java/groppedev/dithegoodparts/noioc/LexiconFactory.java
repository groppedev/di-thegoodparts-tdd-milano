package groppedev.dithegoodparts.noioc;

import org.assertj.core.util.VisibleForTesting;

import groppedev.dithegoodparts.domain.lexicon.Lexicon;
import groppedev.dithegoodparts.domain.lexicon.LexiconItalianFood;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconFileRepository;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconInMemoryRepository;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconQueryExecutor;
import groppedev.dithegoodparts.domain.lexicon.repository.LexiconRepository;

public class LexiconFactory
{
	// SCOPE Singleton nel sorgente e non determinato dal contesto.
	private static final LexiconFactory INSTANCE;
	static
	{
		INSTANCE = new LexiconFactory();
		INSTANCE.init();
	}
	
	private volatile LexiconRepository lexiconREPO = new LexiconFileRepository();
	private volatile LexiconQueryExecutor lexiconQE = new LexiconQueryExecutor(lexiconREPO);

	public static LexiconFactory getInstance()
	{
		return INSTANCE;
	}
	private LexiconFactory() {}
	
	// Factory method
	public Lexicon newItalianFoodLexicon()
	{
		// Composizione manuale del grafo, nessuna flessibilità
		return new LexiconItalianFood(lexiconREPO, lexiconQE);
	}
	
	public void init()
	{
		if(lexiconREPO instanceof LexiconInMemoryRepository)
		{
			((LexiconInMemoryRepository)lexiconREPO).init();
		}
	}
	public void destroy()
	{
		if(lexiconREPO instanceof LexiconInMemoryRepository)
		{
			((LexiconInMemoryRepository)lexiconREPO).destroy();
		}
		else if(lexiconREPO instanceof LexiconFileRepository)
		{
			((LexiconFileRepository)lexiconREPO).close();
		}
	}
	
	@VisibleForTesting
	public void setLexiconREPO(LexiconRepository lexiconREPO)
	{
		this.lexiconREPO = lexiconREPO;
	}
	@VisibleForTesting
	public void setLexiconQE(LexiconQueryExecutor lexiconQE) 
	{
		this.lexiconQE = lexiconQE;
	}
}

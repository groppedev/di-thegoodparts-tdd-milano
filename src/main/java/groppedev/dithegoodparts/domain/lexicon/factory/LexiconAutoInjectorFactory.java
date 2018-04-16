package groppedev.dithegoodparts.domain.lexicon.factory;

import groppedev.dithegoodparts.application.provider.ComponentProvider;
import groppedev.dithegoodparts.domain.lexicon.Lexicon;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;

/**
 * - Poco codice da scrivere coinciso
 * - Non c'è l'overhead di dover iniettare le dipendenze dei prodotto nelle factory.
 * - Non si perdono i vantaggi degli scope e dei profili (Senza dover gestire una cache specifica)
 * - Non si usa direttamente il container di dependency inejction, ma una sua astrazione.
 * - Facilmente testabile
 * 
 * - Si deve utlizzare il {@link ComponentProvider}
 * - Mappatura manuale tra {@link LexiconType} ed il tipo di implementazione di {@link Lexicon}
 */
public class LexiconAutoInjectorFactory implements LexiconFactory
{
	private final LexiconType lexiconType;
	private final ComponentProvider componentProvider;
	
	public LexiconAutoInjectorFactory(LexiconType lexiconType, ComponentProvider provider)
	{
		this.lexiconType = lexiconType;
		this.componentProvider = provider;
	}

	@Override
	public Lexicon newLexicon() 
	{
		return lexiconByType(lexiconType);
	}
	
	private Lexicon lexiconByType(LexiconType lexiconType)
	{
		return componentProvider.component(lexiconType.toImplementationType());
	}
}

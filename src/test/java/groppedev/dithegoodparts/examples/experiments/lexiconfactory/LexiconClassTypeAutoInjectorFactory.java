package groppedev.dithegoodparts.examples.experiments.lexiconfactory;

import groppedev.dithegoodparts.application.provider.ComponentProvider;
import groppedev.dithegoodparts.domain.lexicon.Lexicon;
import groppedev.dithegoodparts.domain.lexicon.LexiconItalianFood;
import groppedev.dithegoodparts.domain.lexicon.LexiconItalianSport;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;
import groppedev.dithegoodparts.domain.lexicon.factory.LexiconFactory;

/**
 * - Poco codice da scrivere coinciso
 * - Non c'è l'overhead di dover iniettare le dipendenze dei prodotto nelle factory.
 * - Non si perdono i vantaggi degli scope e dei profili (Senza dover gestire una cache specifica)
 * - Non si usa direttamente il container di dependency inejction, ma una sua astrazione.
 * - Facilmente testabile
 * 
 * - Vanno definiti i bean dei prodotti
 * - Si deve utlizzare il {@link ComponentProvider}
 * - Mappatura manuale tra {@link LexiconType} ed il tipo di implementazione di {@link Lexicon}
 * - Se si aggiunge un nuovo tipo di {@link Lexicon} è necessario modificare anche il codice della factory
 */
public class LexiconClassTypeAutoInjectorFactory implements LexiconFactory
{
	private final LexiconType lexiconType;
	private final ComponentProvider componentProvider;
	
	public LexiconClassTypeAutoInjectorFactory(LexiconType lexiconType, ComponentProvider componentProvider) 
	{
		this.lexiconType = lexiconType;
		this.componentProvider = componentProvider;
	}

	@Override
	public Lexicon newLexicon() 
	{
		switch (lexiconType) 
		{
		case ITALIAN_FOOD:
			return componentProvider.component(LexiconItalianFood.class);
		case ITALIAN_SPORT:
			return componentProvider.component(LexiconItalianSport.class);
		default:
			throw new IllegalStateException();
		}
	}
}

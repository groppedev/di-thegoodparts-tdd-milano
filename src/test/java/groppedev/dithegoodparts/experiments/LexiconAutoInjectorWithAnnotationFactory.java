package groppedev.dithegoodparts.experiments;

import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.reflections.Reflections;

import groppedev.dithegoodparts.application.provider.ComponentProvider;
import groppedev.dithegoodparts.domain.lexicon.Lexicon;
import groppedev.dithegoodparts.domain.lexicon.LexiconMeta;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;
import groppedev.dithegoodparts.domain.lexicon.factory.LexiconFactory;

/**
 * - Poco codice da scrivere coinciso
 * - Non c'è l'overhead di dover iniettare le dipendenze dei prodotto nelle factory.
 * - Non si perdono i vantaggi degli scope e dei profili (Senza dover gestire una cache specifica)
 * - Non si usa direttamente il container di dependency inejction, ma una sua astrazione.
 * - Facilmente testabile
 * - Mappatura automatica tra tipologia ed implementazione
 * - Se si aggiunge un nuovo tipo di dizionario NON è necessario aggiornare il codice della factory.
 * 
 * - Vanno definiti i bean dei prodotti
 * - Si deve utlizzare il {@link ComponentProvider}
 * - Si aggiunge complessità di implementazione.
 * - Si deve specificare il classpath in cui cercare l'implementazione.
 */
public class LexiconAutoInjectorWithAnnotationFactory implements LexiconFactory
{
	private static final String LEXICON_PACKAGE = "groppedev.dithegoodparts.domain.lexicon";
	
	private final LexiconType lexiconType;
	private final ComponentProvider componentProvider;
	
	public LexiconAutoInjectorWithAnnotationFactory(LexiconType lexiconType, ComponentProvider componentProvider) 
	{
		this.lexiconType = lexiconType;
		this.componentProvider = componentProvider;
	}

	@Override
	public Lexicon newLexicon() 
	{
		return componentProvider.component(searchImplementation());
	}

	/**
	 * Questo metodo se non è possibile applicare un filtro selettivo sui package potrebbe essere dispendioso. 
	 * Valutare di eseguire la ricerca quando si istanzia la Factory e non tutte le volte che viene richiesto un prodotto.
	 */
	private Class<? extends Lexicon> searchImplementation() 
	{
		Set<Class<? extends Lexicon>> lexiconClasses = new Reflections(LEXICON_PACKAGE).getSubTypesOf(Lexicon.class);
		Class<? extends Lexicon> lexiconImplementation = IterableUtils.find(lexiconClasses, new Predicate<Class<? extends Lexicon>>() 
		{
			@Override
			public boolean evaluate(Class<? extends Lexicon> lexiconClass) 
			{
				LexiconMeta annotation = lexiconClass.getAnnotation(LexiconMeta.class);
				return annotation != null && annotation.type() == lexiconType;
			}
		});
		// NOTA: Per semplicità non viene fatto alcun controllo sui duplicati
		Objects.requireNonNull(lexiconImplementation, "Non è stata trovata nessuna implementazione per il tipo di lessico");
		
		return lexiconImplementation;
	}
}

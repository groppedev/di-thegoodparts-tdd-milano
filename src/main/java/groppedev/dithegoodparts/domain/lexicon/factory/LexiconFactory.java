package groppedev.dithegoodparts.domain.lexicon.factory;

import groppedev.dithegoodparts.domain.lexicon.Lexicon;
import groppedev.dithegoodparts.domain.lexicon.factory.other.LexiconAutoInjectorWithAnnotationFactory;
import groppedev.dithegoodparts.domain.lexicon.factory.other.LexiconClassTypeAutoInjectorFactory;
import groppedev.dithegoodparts.domain.lexicon.factory.other.LexiconStandardFactory;

/**
 * 1) Se non è necessario cambiare dinamicamente il tipo di implementazione:
 * NON utilizzare una Factory.
 * 2) Se è necessario cambiare dinamicamente il tipo di implementazione:
 * Iniziare con factory {@link LexiconStandardFactory} 
 * se non sono importanti scope e profili, viceversa utilizzare {@link LexiconClassTypeAutoInjectorFactory}
 * 2) Al crescere del numero di prodotti {@link LexiconAutoInjectorWithAnnotationFactory}
 */
public interface LexiconFactory 
{
	public Lexicon newLexicon();
}

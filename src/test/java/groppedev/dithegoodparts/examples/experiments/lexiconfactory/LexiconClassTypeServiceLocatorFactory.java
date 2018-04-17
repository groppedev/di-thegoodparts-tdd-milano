package groppedev.dithegoodparts.examples.experiments.lexiconfactory;

import groppedev.dithegoodparts.domain.lexicon.Lexicon;
import groppedev.dithegoodparts.domain.lexicon.LexiconItalianFood;
import groppedev.dithegoodparts.domain.lexicon.LexiconItalianSport;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;
import groppedev.dithegoodparts.domain.lexicon.factory.LexiconFactory;

public class LexiconClassTypeServiceLocatorFactory implements LexiconFactory
{
	private final LexiconType lexiconType;
	private final LexiconClassTypeServiceFactory lexiconServiceLocator;

	public LexiconClassTypeServiceLocatorFactory(LexiconType lexiconType, LexiconClassTypeServiceFactory lexiconServiceLocator)
	{
		this.lexiconType = lexiconType;
		this.lexiconServiceLocator = lexiconServiceLocator;
	}

	@Override
	public Lexicon newLexicon() 
	{
		switch (lexiconType) 
		{
		case ITALIAN_FOOD:
			return lexiconServiceLocator.newItalianFoodLexicon();
		case ITALIAN_SPORT:
			return lexiconServiceLocator.newItalianSportLexicon();
		default:
			throw new IllegalStateException();
		}
	}

	/*
 <beans>

   <!-- Prototype bean since we have state -->
   <bean id="myService" class="a.b.c.MyService" singleton="false"/>

   <!-- will lookup the above 'myService' bean by *TYPE* -->
   <bean id="myServiceFactory"
            class="org.springframework.beans.factory.config.ServiceLocatorFactoryBean">
     <property name="serviceLocatorInterface" value="a.b.c.ServiceFactory"/>
   </bean>

   <bean id="clientBean" class="a.b.c.MyClientBean">
     <property name="myServiceFactory" ref="myServiceFactory"/>
   </bean>

</beans>
	 */
	public interface LexiconClassTypeServiceFactory
	{
		LexiconItalianSport newItalianSportLexicon();

		LexiconItalianFood newItalianFoodLexicon();
	}
}

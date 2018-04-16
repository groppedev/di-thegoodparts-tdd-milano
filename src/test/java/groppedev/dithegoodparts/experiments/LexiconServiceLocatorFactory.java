package groppedev.dithegoodparts.experiments;

import groppedev.dithegoodparts.domain.lexicon.Lexicon;
import groppedev.dithegoodparts.domain.lexicon.LexiconType;
import groppedev.dithegoodparts.domain.lexicon.factory.LexiconFactory;

/**
 DA: https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/beans/factory/config/ServiceLocatorFactoryBean.html
 
 Such service locators permit the decoupling of calling code from the BeanFactory API, by using an appropriate custom locator interface. 
 They will typically be used for prototype beans, i.e. for factory methods that are supposed to return a new instance for each call. 
 The client receives a reference to the service locator via setter or constructor injection, to be able to invoke the locator's factory methods on demand. 
 For singleton beans, direct setter or constructor injection of the target bean is preferable.
 */
public class LexiconServiceLocatorFactory implements LexiconFactory
{
	private final LexiconType lexiconType;
	private final LexiconNameServiceLocator lexiconServiceLocator;

	public LexiconServiceLocatorFactory(LexiconType lexiconType, LexiconNameServiceLocator lexiconServiceLocator)
	{
		this.lexiconType = lexiconType;
		this.lexiconServiceLocator = lexiconServiceLocator;
	}

	@Override
	public Lexicon newLexicon() 
	{
		return lexiconServiceLocator.lexiconService(lexiconType.id());
	}
	
	public interface LexiconNameServiceLocator
	{
		Lexicon lexiconService(String serviceName);
	}

	/*
<beans>

   <!-- Prototype beans since we have state (both extend MyService) -->
   <bean id="specialService" class="a.b.c.SpecialService" singleton="false"/>
   <bean id="anotherService" class="a.b.c.AnotherService" singleton="false"/>

   <bean id="myServiceFactory"
            class="org.springframework.beans.factory.config.ServiceLocatorFactoryBean">
     <property name="serviceLocatorInterface" value="a.b.c.ServiceFactory"/>
   </bean>

   <bean id="clientBean" class="a.b.c.MyClientBean">
     <property name="myServiceFactory" ref="myServiceFactory"/>
   </bean>

</beans>
	 */
}

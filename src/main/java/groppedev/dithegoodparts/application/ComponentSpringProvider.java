package groppedev.dithegoodparts.application;

import java.util.Objects;

import org.springframework.context.support.AbstractApplicationContext;

public class ComponentSpringProvider implements ComponentProvider
{
	private volatile AbstractApplicationContext applicationContext;

	public <T> T component(Class<T> componentType)
	{
		Objects.requireNonNull(applicationContext, "Inizializzazione del provider non effettuata");
		
		return applicationContext.getBean(componentType);
	}

	protected void setApplicationContext(AbstractApplicationContext applicationContext) 
	{
		this.applicationContext = applicationContext;
	}
}

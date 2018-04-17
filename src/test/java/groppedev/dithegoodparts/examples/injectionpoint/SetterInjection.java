package groppedev.dithegoodparts.examples.injectionpoint;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

// Annotations JSR-330.
@Named 
public class SetterInjection implements InjectionPoint
{
	private Provider<Example> provider;
	
	public void execute() 
	{ 
		Objects.requireNonNull(provider);
		System.out.println(provider.get());
	}
	public void executeBis() { 
		Objects.requireNonNull(provider);
		System.out.println(provider.get());
	}
	@Inject public void setProvider(Provider<Example> provider) 
	{
		this.provider = provider;
	}
}

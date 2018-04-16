package groppedev.dithegoodparts.experiments;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

@Named
@Singleton
public class ProviderExample
{
	@Inject
	private Provider<Prova> provider;
	
	public void test()
	{
		System.out.println(provider.get());
	}
}

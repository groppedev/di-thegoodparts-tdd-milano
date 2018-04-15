package groppedev.dithegoodparts.application;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

@Named
public class ProviderExample 
{
	private final Provider<Provaa> provider;

	@Inject
	public ProviderExample(Provider<Provaa> provider, Provaa prova)
	{
		this.provider = provider;
	}
	
	public void test()
	{
		System.out.println(provider.get());
		System.out.println(provider.get());
	}
}

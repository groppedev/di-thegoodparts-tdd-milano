package groppedev.dithegoodparts.application;

import org.springframework.context.support.AbstractApplicationContext;

public class ApplicationLauncherProvaAnnotation 
{
	public static void main(String[] args) 
	{
		Application app = new Application();
		AbstractApplicationContext provider = app.startAnnotationBasedApp();
		ProviderExample component = provider.getBean(ProviderExample.class);
		component.test();
		app.stop();
	}
}

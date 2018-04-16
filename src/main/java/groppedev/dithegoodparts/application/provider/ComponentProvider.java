package groppedev.dithegoodparts.application.provider;

import javax.inject.Provider;

/**
 * I componenti che dipendono dal {@link ComponentProvider} devono essere gestiti in modalità LAZY
 * 
 * @see Provider
 */
public interface ComponentProvider 
{
	/**
	 * @param componentType Tipo del componente che si vuole recuperare.
	 * @return Istanza del componente del tipo indicato.
	 */
	<T> T component(Class<T> componentType);
}

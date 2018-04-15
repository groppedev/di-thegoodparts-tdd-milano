package groppedev.dithegoodparts.application;

import javax.inject.Provider;

/**
 * @see Provider
 */
public interface ComponentProvider 
{
	/**
	 * @param componentType Tipo del componente che si vuole recuperare.
	 * @return Istanza del componente del tipo indicato.
	 */
	<T> T component(Class<T> componentType);
	
	<T> T component(String componentID, Class<T> componentType);
}

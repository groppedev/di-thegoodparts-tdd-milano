package groppedev.dithegoodparts.examples.injectionpoint;

import javax.inject.Inject;
import javax.inject.Named;


@SuppressWarnings("unused")
@Named
public class QualifiedDependent
{
	private final QualifiedDepdendency dependencyA;
	private final QualifiedDepdendency dependencyB;

	@Inject
	public QualifiedDependent(@Named(value="spellcheck.example.qualified.a") QualifiedDepdendency dependencyA, 
			                  @Named(value="spellcheck.example.qualified.b") QualifiedDepdendency dependencyB) 
	{
		this.dependencyA = dependencyA;
		this.dependencyB = dependencyB;
	}
	
	/*
	Se non si qualificano le dipendenze viene sollevato il seguente errore:

	Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException: 
	  No qualifying bean of type 'groppedev.dithegoodparts.examples.injectionpoint.QualifiedDepdendency' available: 
	   expected single matching bean but found 2: spellcheck.example.qualified.a,spellcheck.example.qualified.b
		at org.springframework.beans.factory.config.DependencyDescriptor.resolveNotUnique(DependencyDescriptor.java:215)
	 */
	/* @Inject
	public QualifiedDependent(QualifiedDepdendency dependencyA, QualifiedDepdendency dependencyB) 
	{
		this.dependencyA = dependencyA;
		this.dependencyB = dependencyB;
	}
	*/
}

package org.xtext.orcasdk.entitymodel.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute;
import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity;
import org.xtext.orcasdk.entitymodel.entityModel.AppConstants;
import org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage;
import org.xtext.orcasdk.entitymodel.entityModel.LinkProperties;
import org.xtext.orcasdk.entitymodel.entityModel.Model;
import org.xtext.orcasdk.entitymodel.entityModel.Packages;
import org.xtext.orcasdk.entitymodel.services.EntityModelGrammarAccess;

@SuppressWarnings("all")
public class EntityModelSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private EntityModelGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == EntityModelPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case EntityModelPackage.ANDROID_ATTRIBUTE:
				if(context == grammarAccess.getAndroidAttributeRule()) {
					sequence_AndroidAttribute(context, (AndroidAttribute) semanticObject); 
					return; 
				}
				else break;
			case EntityModelPackage.ANDROID_ENTITY:
				if(context == grammarAccess.getAndroidEntityRule()) {
					sequence_AndroidEntity(context, (AndroidEntity) semanticObject); 
					return; 
				}
				else break;
			case EntityModelPackage.APP_CONSTANTS:
				if(context == grammarAccess.getAppConstantsRule()) {
					sequence_AppConstants(context, (AppConstants) semanticObject); 
					return; 
				}
				else break;
			case EntityModelPackage.LINK_PROPERTIES:
				if(context == grammarAccess.getLinkPropertiesRule()) {
					sequence_LinkProperties(context, (LinkProperties) semanticObject); 
					return; 
				}
				else break;
			case EntityModelPackage.MODEL:
				if(context == grammarAccess.getModelRule()) {
					sequence_Model(context, (Model) semanticObject); 
					return; 
				}
				else break;
			case EntityModelPackage.PACKAGES:
				if(context == grammarAccess.getPackagesRule()) {
					sequence_Packages(context, (Packages) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (
	 *         (type='string' name=ID) | 
	 *         (type='boolean' name=ID) | 
	 *         (type='long' name=ID) | 
	 *         (type='double' name=ID) | 
	 *         (type='double[]' name=ID) | 
	 *         (type='date' name=ID) | 
	 *         (type='image' name=ID) | 
	 *         (type='link' name=ID objectType=[AndroidEntity|ID] linkproperties+=LinkProperties+)
	 *     )
	 */
	protected void sequence_AndroidAttribute(EObject context, AndroidAttribute semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID value+=INT+ attributes+=AndroidAttribute+)
	 */
	protected void sequence_AndroidEntity(EObject context, AndroidEntity semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (valueappname+=STRING* valueapikey+=STRING* packageentities+=Packages+)
	 */
	protected void sequence_AppConstants(EObject context, AppConstants semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (type='string' name=ID) | 
	 *         (type='boolean' name=ID) | 
	 *         (type='long' name=ID) | 
	 *         (type='double' name=ID) | 
	 *         (type='double[]' name=ID) | 
	 *         (type='date' name=ID)
	 *     )
	 */
	protected void sequence_LinkProperties(EObject context, LinkProperties semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     appconstants=AppConstants?
	 */
	protected void sequence_Model(EObject context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=QualifiedName entities+=AndroidEntity+)
	 */
	protected void sequence_Packages(EObject context, Packages semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}

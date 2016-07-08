/**
 */
package org.xtext.orcasdk.entitymodel.entityModel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.Model#getAppconstants <em>Appconstants</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject
{
  /**
   * Returns the value of the '<em><b>Appconstants</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Appconstants</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Appconstants</em>' containment reference.
   * @see #setAppconstants(AppConstants)
   * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#getModel_Appconstants()
   * @model containment="true"
   * @generated
   */
  AppConstants getAppconstants();

  /**
   * Sets the value of the '{@link org.xtext.orcasdk.entitymodel.entityModel.Model#getAppconstants <em>Appconstants</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Appconstants</em>' containment reference.
   * @see #getAppconstants()
   * @generated
   */
  void setAppconstants(AppConstants value);

} // Model

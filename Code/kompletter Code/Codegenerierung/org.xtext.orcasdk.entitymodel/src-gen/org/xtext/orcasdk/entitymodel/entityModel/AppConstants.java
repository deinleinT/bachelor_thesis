/**
 */
package org.xtext.orcasdk.entitymodel.entityModel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>App Constants</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.AppConstants#getValueappname <em>Valueappname</em>}</li>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.AppConstants#getValueapikey <em>Valueapikey</em>}</li>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.AppConstants#getPackageentities <em>Packageentities</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#getAppConstants()
 * @model
 * @generated
 */
public interface AppConstants extends EObject
{
  /**
   * Returns the value of the '<em><b>Valueappname</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Valueappname</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Valueappname</em>' attribute list.
   * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#getAppConstants_Valueappname()
   * @model unique="false"
   * @generated
   */
  EList<String> getValueappname();

  /**
   * Returns the value of the '<em><b>Valueapikey</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Valueapikey</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Valueapikey</em>' attribute list.
   * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#getAppConstants_Valueapikey()
   * @model unique="false"
   * @generated
   */
  EList<String> getValueapikey();

  /**
   * Returns the value of the '<em><b>Packageentities</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.orcasdk.entitymodel.entityModel.Packages}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Packageentities</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Packageentities</em>' containment reference list.
   * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#getAppConstants_Packageentities()
   * @model containment="true"
   * @generated
   */
  EList<Packages> getPackageentities();

} // AppConstants

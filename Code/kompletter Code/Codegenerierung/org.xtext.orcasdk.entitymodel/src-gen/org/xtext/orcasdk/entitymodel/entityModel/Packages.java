/**
 */
package org.xtext.orcasdk.entitymodel.entityModel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Packages</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.Packages#getName <em>Name</em>}</li>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.Packages#getEntities <em>Entities</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#getPackages()
 * @model
 * @generated
 */
public interface Packages extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#getPackages_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.xtext.orcasdk.entitymodel.entityModel.Packages#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Entities</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entities</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entities</em>' containment reference list.
   * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#getPackages_Entities()
   * @model containment="true"
   * @generated
   */
  EList<AndroidEntity> getEntities();

} // Packages

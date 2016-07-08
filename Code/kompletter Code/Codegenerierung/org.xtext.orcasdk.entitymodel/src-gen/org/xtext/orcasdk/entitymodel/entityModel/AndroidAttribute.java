/**
 */
package org.xtext.orcasdk.entitymodel.entityModel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Android Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getType <em>Type</em>}</li>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getName <em>Name</em>}</li>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getObjectType <em>Object Type</em>}</li>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getLinkproperties <em>Linkproperties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#getAndroidAttribute()
 * @model
 * @generated
 */
public interface AndroidAttribute extends EObject
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute.
   * @see #setType(String)
   * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#getAndroidAttribute_Type()
   * @model
   * @generated
   */
  String getType();

  /**
   * Sets the value of the '{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see #getType()
   * @generated
   */
  void setType(String value);

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
   * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#getAndroidAttribute_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Object Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Object Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Object Type</em>' reference.
   * @see #setObjectType(AndroidEntity)
   * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#getAndroidAttribute_ObjectType()
   * @model
   * @generated
   */
  AndroidEntity getObjectType();

  /**
   * Sets the value of the '{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getObjectType <em>Object Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Object Type</em>' reference.
   * @see #getObjectType()
   * @generated
   */
  void setObjectType(AndroidEntity value);

  /**
   * Returns the value of the '<em><b>Linkproperties</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.orcasdk.entitymodel.entityModel.LinkProperties}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Linkproperties</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Linkproperties</em>' containment reference list.
   * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#getAndroidAttribute_Linkproperties()
   * @model containment="true"
   * @generated
   */
  EList<LinkProperties> getLinkproperties();

} // AndroidAttribute

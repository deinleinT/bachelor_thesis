/**
 */
package org.xtext.orcasdk.entitymodel.entityModel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage
 * @generated
 */
public interface EntityModelFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  EntityModelFactory eINSTANCE = org.xtext.orcasdk.entitymodel.entityModel.impl.EntityModelFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model</em>'.
   * @generated
   */
  Model createModel();

  /**
   * Returns a new object of class '<em>App Constants</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>App Constants</em>'.
   * @generated
   */
  AppConstants createAppConstants();

  /**
   * Returns a new object of class '<em>Packages</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Packages</em>'.
   * @generated
   */
  Packages createPackages();

  /**
   * Returns a new object of class '<em>Android Entity</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Android Entity</em>'.
   * @generated
   */
  AndroidEntity createAndroidEntity();

  /**
   * Returns a new object of class '<em>Android Attribute</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Android Attribute</em>'.
   * @generated
   */
  AndroidAttribute createAndroidAttribute();

  /**
   * Returns a new object of class '<em>Link Properties</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Link Properties</em>'.
   * @generated
   */
  LinkProperties createLinkProperties();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  EntityModelPackage getEntityModelPackage();

} //EntityModelFactory

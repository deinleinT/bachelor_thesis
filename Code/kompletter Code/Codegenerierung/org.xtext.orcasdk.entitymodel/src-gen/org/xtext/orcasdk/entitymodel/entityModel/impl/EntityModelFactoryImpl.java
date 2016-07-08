/**
 */
package org.xtext.orcasdk.entitymodel.entityModel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.xtext.orcasdk.entitymodel.entityModel.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EntityModelFactoryImpl extends EFactoryImpl implements EntityModelFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static EntityModelFactory init()
  {
    try
    {
      EntityModelFactory theEntityModelFactory = (EntityModelFactory)EPackage.Registry.INSTANCE.getEFactory(EntityModelPackage.eNS_URI);
      if (theEntityModelFactory != null)
      {
        return theEntityModelFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new EntityModelFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityModelFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case EntityModelPackage.MODEL: return createModel();
      case EntityModelPackage.APP_CONSTANTS: return createAppConstants();
      case EntityModelPackage.PACKAGES: return createPackages();
      case EntityModelPackage.ANDROID_ENTITY: return createAndroidEntity();
      case EntityModelPackage.ANDROID_ATTRIBUTE: return createAndroidAttribute();
      case EntityModelPackage.LINK_PROPERTIES: return createLinkProperties();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Model createModel()
  {
    ModelImpl model = new ModelImpl();
    return model;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AppConstants createAppConstants()
  {
    AppConstantsImpl appConstants = new AppConstantsImpl();
    return appConstants;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Packages createPackages()
  {
    PackagesImpl packages = new PackagesImpl();
    return packages;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AndroidEntity createAndroidEntity()
  {
    AndroidEntityImpl androidEntity = new AndroidEntityImpl();
    return androidEntity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AndroidAttribute createAndroidAttribute()
  {
    AndroidAttributeImpl androidAttribute = new AndroidAttributeImpl();
    return androidAttribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LinkProperties createLinkProperties()
  {
    LinkPropertiesImpl linkProperties = new LinkPropertiesImpl();
    return linkProperties;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityModelPackage getEntityModelPackage()
  {
    return (EntityModelPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static EntityModelPackage getPackage()
  {
    return EntityModelPackage.eINSTANCE;
  }

} //EntityModelFactoryImpl

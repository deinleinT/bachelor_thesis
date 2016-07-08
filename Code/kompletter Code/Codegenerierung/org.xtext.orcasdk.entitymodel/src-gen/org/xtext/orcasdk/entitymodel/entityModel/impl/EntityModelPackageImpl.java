/**
 */
package org.xtext.orcasdk.entitymodel.entityModel.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute;
import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity;
import org.xtext.orcasdk.entitymodel.entityModel.AppConstants;
import org.xtext.orcasdk.entitymodel.entityModel.EntityModelFactory;
import org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage;
import org.xtext.orcasdk.entitymodel.entityModel.LinkProperties;
import org.xtext.orcasdk.entitymodel.entityModel.Model;
import org.xtext.orcasdk.entitymodel.entityModel.Packages;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EntityModelPackageImpl extends EPackageImpl implements EntityModelPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass appConstantsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass packagesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass androidEntityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass androidAttributeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass linkPropertiesEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private EntityModelPackageImpl()
  {
    super(eNS_URI, EntityModelFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link EntityModelPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static EntityModelPackage init()
  {
    if (isInited) return (EntityModelPackage)EPackage.Registry.INSTANCE.getEPackage(EntityModelPackage.eNS_URI);

    // Obtain or create and register package
    EntityModelPackageImpl theEntityModelPackage = (EntityModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EntityModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EntityModelPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theEntityModelPackage.createPackageContents();

    // Initialize created meta-data
    theEntityModelPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theEntityModelPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(EntityModelPackage.eNS_URI, theEntityModelPackage);
    return theEntityModelPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModel()
  {
    return modelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModel_Appconstants()
  {
    return (EReference)modelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAppConstants()
  {
    return appConstantsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAppConstants_Valueappname()
  {
    return (EAttribute)appConstantsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAppConstants_Valueapikey()
  {
    return (EAttribute)appConstantsEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAppConstants_Packageentities()
  {
    return (EReference)appConstantsEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPackages()
  {
    return packagesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPackages_Name()
  {
    return (EAttribute)packagesEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPackages_Entities()
  {
    return (EReference)packagesEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAndroidEntity()
  {
    return androidEntityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAndroidEntity_Name()
  {
    return (EAttribute)androidEntityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAndroidEntity_Value()
  {
    return (EAttribute)androidEntityEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAndroidEntity_Attributes()
  {
    return (EReference)androidEntityEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAndroidAttribute()
  {
    return androidAttributeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAndroidAttribute_Type()
  {
    return (EAttribute)androidAttributeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAndroidAttribute_Name()
  {
    return (EAttribute)androidAttributeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAndroidAttribute_ObjectType()
  {
    return (EReference)androidAttributeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAndroidAttribute_Linkproperties()
  {
    return (EReference)androidAttributeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLinkProperties()
  {
    return linkPropertiesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLinkProperties_Type()
  {
    return (EAttribute)linkPropertiesEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLinkProperties_Name()
  {
    return (EAttribute)linkPropertiesEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityModelFactory getEntityModelFactory()
  {
    return (EntityModelFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    modelEClass = createEClass(MODEL);
    createEReference(modelEClass, MODEL__APPCONSTANTS);

    appConstantsEClass = createEClass(APP_CONSTANTS);
    createEAttribute(appConstantsEClass, APP_CONSTANTS__VALUEAPPNAME);
    createEAttribute(appConstantsEClass, APP_CONSTANTS__VALUEAPIKEY);
    createEReference(appConstantsEClass, APP_CONSTANTS__PACKAGEENTITIES);

    packagesEClass = createEClass(PACKAGES);
    createEAttribute(packagesEClass, PACKAGES__NAME);
    createEReference(packagesEClass, PACKAGES__ENTITIES);

    androidEntityEClass = createEClass(ANDROID_ENTITY);
    createEAttribute(androidEntityEClass, ANDROID_ENTITY__NAME);
    createEAttribute(androidEntityEClass, ANDROID_ENTITY__VALUE);
    createEReference(androidEntityEClass, ANDROID_ENTITY__ATTRIBUTES);

    androidAttributeEClass = createEClass(ANDROID_ATTRIBUTE);
    createEAttribute(androidAttributeEClass, ANDROID_ATTRIBUTE__TYPE);
    createEAttribute(androidAttributeEClass, ANDROID_ATTRIBUTE__NAME);
    createEReference(androidAttributeEClass, ANDROID_ATTRIBUTE__OBJECT_TYPE);
    createEReference(androidAttributeEClass, ANDROID_ATTRIBUTE__LINKPROPERTIES);

    linkPropertiesEClass = createEClass(LINK_PROPERTIES);
    createEAttribute(linkPropertiesEClass, LINK_PROPERTIES__TYPE);
    createEAttribute(linkPropertiesEClass, LINK_PROPERTIES__NAME);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getModel_Appconstants(), this.getAppConstants(), null, "appconstants", null, 0, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(appConstantsEClass, AppConstants.class, "AppConstants", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAppConstants_Valueappname(), ecorePackage.getEString(), "valueappname", null, 0, -1, AppConstants.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAppConstants_Valueapikey(), ecorePackage.getEString(), "valueapikey", null, 0, -1, AppConstants.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAppConstants_Packageentities(), this.getPackages(), null, "packageentities", null, 0, -1, AppConstants.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(packagesEClass, Packages.class, "Packages", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPackages_Name(), ecorePackage.getEString(), "name", null, 0, 1, Packages.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPackages_Entities(), this.getAndroidEntity(), null, "entities", null, 0, -1, Packages.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(androidEntityEClass, AndroidEntity.class, "AndroidEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAndroidEntity_Name(), ecorePackage.getEString(), "name", null, 0, 1, AndroidEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAndroidEntity_Value(), ecorePackage.getEInt(), "value", null, 0, -1, AndroidEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAndroidEntity_Attributes(), this.getAndroidAttribute(), null, "attributes", null, 0, -1, AndroidEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(androidAttributeEClass, AndroidAttribute.class, "AndroidAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAndroidAttribute_Type(), ecorePackage.getEString(), "type", null, 0, 1, AndroidAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAndroidAttribute_Name(), ecorePackage.getEString(), "name", null, 0, 1, AndroidAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAndroidAttribute_ObjectType(), this.getAndroidEntity(), null, "objectType", null, 0, 1, AndroidAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAndroidAttribute_Linkproperties(), this.getLinkProperties(), null, "linkproperties", null, 0, -1, AndroidAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(linkPropertiesEClass, LinkProperties.class, "LinkProperties", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLinkProperties_Type(), ecorePackage.getEString(), "type", null, 0, 1, LinkProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLinkProperties_Name(), ecorePackage.getEString(), "name", null, 0, 1, LinkProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //EntityModelPackageImpl

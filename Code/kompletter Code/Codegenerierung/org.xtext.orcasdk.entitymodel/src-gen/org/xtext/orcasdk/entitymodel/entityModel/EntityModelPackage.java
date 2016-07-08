/**
 */
package org.xtext.orcasdk.entitymodel.entityModel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.xtext.orcasdk.entitymodel.entityModel.EntityModelFactory
 * @model kind="package"
 * @generated
 */
public interface EntityModelPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "entityModel";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.xtext.org/orcasdk/entitymodel/EntityModel";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "entityModel";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  EntityModelPackage eINSTANCE = org.xtext.orcasdk.entitymodel.entityModel.impl.EntityModelPackageImpl.init();

  /**
   * The meta object id for the '{@link org.xtext.orcasdk.entitymodel.entityModel.impl.ModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.orcasdk.entitymodel.entityModel.impl.ModelImpl
   * @see org.xtext.orcasdk.entitymodel.entityModel.impl.EntityModelPackageImpl#getModel()
   * @generated
   */
  int MODEL = 0;

  /**
   * The feature id for the '<em><b>Appconstants</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__APPCONSTANTS = 0;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.orcasdk.entitymodel.entityModel.impl.AppConstantsImpl <em>App Constants</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.orcasdk.entitymodel.entityModel.impl.AppConstantsImpl
   * @see org.xtext.orcasdk.entitymodel.entityModel.impl.EntityModelPackageImpl#getAppConstants()
   * @generated
   */
  int APP_CONSTANTS = 1;

  /**
   * The feature id for the '<em><b>Valueappname</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int APP_CONSTANTS__VALUEAPPNAME = 0;

  /**
   * The feature id for the '<em><b>Valueapikey</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int APP_CONSTANTS__VALUEAPIKEY = 1;

  /**
   * The feature id for the '<em><b>Packageentities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int APP_CONSTANTS__PACKAGEENTITIES = 2;

  /**
   * The number of structural features of the '<em>App Constants</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int APP_CONSTANTS_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.xtext.orcasdk.entitymodel.entityModel.impl.PackagesImpl <em>Packages</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.orcasdk.entitymodel.entityModel.impl.PackagesImpl
   * @see org.xtext.orcasdk.entitymodel.entityModel.impl.EntityModelPackageImpl#getPackages()
   * @generated
   */
  int PACKAGES = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGES__NAME = 0;

  /**
   * The feature id for the '<em><b>Entities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGES__ENTITIES = 1;

  /**
   * The number of structural features of the '<em>Packages</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGES_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.xtext.orcasdk.entitymodel.entityModel.impl.AndroidEntityImpl <em>Android Entity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.orcasdk.entitymodel.entityModel.impl.AndroidEntityImpl
   * @see org.xtext.orcasdk.entitymodel.entityModel.impl.EntityModelPackageImpl#getAndroidEntity()
   * @generated
   */
  int ANDROID_ENTITY = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANDROID_ENTITY__NAME = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANDROID_ENTITY__VALUE = 1;

  /**
   * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANDROID_ENTITY__ATTRIBUTES = 2;

  /**
   * The number of structural features of the '<em>Android Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANDROID_ENTITY_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.xtext.orcasdk.entitymodel.entityModel.impl.AndroidAttributeImpl <em>Android Attribute</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.orcasdk.entitymodel.entityModel.impl.AndroidAttributeImpl
   * @see org.xtext.orcasdk.entitymodel.entityModel.impl.EntityModelPackageImpl#getAndroidAttribute()
   * @generated
   */
  int ANDROID_ATTRIBUTE = 4;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANDROID_ATTRIBUTE__TYPE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANDROID_ATTRIBUTE__NAME = 1;

  /**
   * The feature id for the '<em><b>Object Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANDROID_ATTRIBUTE__OBJECT_TYPE = 2;

  /**
   * The feature id for the '<em><b>Linkproperties</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANDROID_ATTRIBUTE__LINKPROPERTIES = 3;

  /**
   * The number of structural features of the '<em>Android Attribute</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANDROID_ATTRIBUTE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.xtext.orcasdk.entitymodel.entityModel.impl.LinkPropertiesImpl <em>Link Properties</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.orcasdk.entitymodel.entityModel.impl.LinkPropertiesImpl
   * @see org.xtext.orcasdk.entitymodel.entityModel.impl.EntityModelPackageImpl#getLinkProperties()
   * @generated
   */
  int LINK_PROPERTIES = 5;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK_PROPERTIES__TYPE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK_PROPERTIES__NAME = 1;

  /**
   * The number of structural features of the '<em>Link Properties</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINK_PROPERTIES_FEATURE_COUNT = 2;


  /**
   * Returns the meta object for class '{@link org.xtext.orcasdk.entitymodel.entityModel.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.Model
   * @generated
   */
  EClass getModel();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.orcasdk.entitymodel.entityModel.Model#getAppconstants <em>Appconstants</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Appconstants</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.Model#getAppconstants()
   * @see #getModel()
   * @generated
   */
  EReference getModel_Appconstants();

  /**
   * Returns the meta object for class '{@link org.xtext.orcasdk.entitymodel.entityModel.AppConstants <em>App Constants</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>App Constants</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.AppConstants
   * @generated
   */
  EClass getAppConstants();

  /**
   * Returns the meta object for the attribute list '{@link org.xtext.orcasdk.entitymodel.entityModel.AppConstants#getValueappname <em>Valueappname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Valueappname</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.AppConstants#getValueappname()
   * @see #getAppConstants()
   * @generated
   */
  EAttribute getAppConstants_Valueappname();

  /**
   * Returns the meta object for the attribute list '{@link org.xtext.orcasdk.entitymodel.entityModel.AppConstants#getValueapikey <em>Valueapikey</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Valueapikey</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.AppConstants#getValueapikey()
   * @see #getAppConstants()
   * @generated
   */
  EAttribute getAppConstants_Valueapikey();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.orcasdk.entitymodel.entityModel.AppConstants#getPackageentities <em>Packageentities</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Packageentities</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.AppConstants#getPackageentities()
   * @see #getAppConstants()
   * @generated
   */
  EReference getAppConstants_Packageentities();

  /**
   * Returns the meta object for class '{@link org.xtext.orcasdk.entitymodel.entityModel.Packages <em>Packages</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Packages</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.Packages
   * @generated
   */
  EClass getPackages();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.orcasdk.entitymodel.entityModel.Packages#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.Packages#getName()
   * @see #getPackages()
   * @generated
   */
  EAttribute getPackages_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.orcasdk.entitymodel.entityModel.Packages#getEntities <em>Entities</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entities</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.Packages#getEntities()
   * @see #getPackages()
   * @generated
   */
  EReference getPackages_Entities();

  /**
   * Returns the meta object for class '{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity <em>Android Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Android Entity</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity
   * @generated
   */
  EClass getAndroidEntity();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity#getName()
   * @see #getAndroidEntity()
   * @generated
   */
  EAttribute getAndroidEntity_Name();

  /**
   * Returns the meta object for the attribute list '{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Value</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity#getValue()
   * @see #getAndroidEntity()
   * @generated
   */
  EAttribute getAndroidEntity_Value();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity#getAttributes <em>Attributes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Attributes</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity#getAttributes()
   * @see #getAndroidEntity()
   * @generated
   */
  EReference getAndroidEntity_Attributes();

  /**
   * Returns the meta object for class '{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute <em>Android Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Android Attribute</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute
   * @generated
   */
  EClass getAndroidAttribute();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getType()
   * @see #getAndroidAttribute()
   * @generated
   */
  EAttribute getAndroidAttribute_Type();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getName()
   * @see #getAndroidAttribute()
   * @generated
   */
  EAttribute getAndroidAttribute_Name();

  /**
   * Returns the meta object for the reference '{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getObjectType <em>Object Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Object Type</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getObjectType()
   * @see #getAndroidAttribute()
   * @generated
   */
  EReference getAndroidAttribute_ObjectType();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getLinkproperties <em>Linkproperties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Linkproperties</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute#getLinkproperties()
   * @see #getAndroidAttribute()
   * @generated
   */
  EReference getAndroidAttribute_Linkproperties();

  /**
   * Returns the meta object for class '{@link org.xtext.orcasdk.entitymodel.entityModel.LinkProperties <em>Link Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Link Properties</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.LinkProperties
   * @generated
   */
  EClass getLinkProperties();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.orcasdk.entitymodel.entityModel.LinkProperties#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.LinkProperties#getType()
   * @see #getLinkProperties()
   * @generated
   */
  EAttribute getLinkProperties_Type();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.orcasdk.entitymodel.entityModel.LinkProperties#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.xtext.orcasdk.entitymodel.entityModel.LinkProperties#getName()
   * @see #getLinkProperties()
   * @generated
   */
  EAttribute getLinkProperties_Name();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  EntityModelFactory getEntityModelFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.xtext.orcasdk.entitymodel.entityModel.impl.ModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.orcasdk.entitymodel.entityModel.impl.ModelImpl
     * @see org.xtext.orcasdk.entitymodel.entityModel.impl.EntityModelPackageImpl#getModel()
     * @generated
     */
    EClass MODEL = eINSTANCE.getModel();

    /**
     * The meta object literal for the '<em><b>Appconstants</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL__APPCONSTANTS = eINSTANCE.getModel_Appconstants();

    /**
     * The meta object literal for the '{@link org.xtext.orcasdk.entitymodel.entityModel.impl.AppConstantsImpl <em>App Constants</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.orcasdk.entitymodel.entityModel.impl.AppConstantsImpl
     * @see org.xtext.orcasdk.entitymodel.entityModel.impl.EntityModelPackageImpl#getAppConstants()
     * @generated
     */
    EClass APP_CONSTANTS = eINSTANCE.getAppConstants();

    /**
     * The meta object literal for the '<em><b>Valueappname</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute APP_CONSTANTS__VALUEAPPNAME = eINSTANCE.getAppConstants_Valueappname();

    /**
     * The meta object literal for the '<em><b>Valueapikey</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute APP_CONSTANTS__VALUEAPIKEY = eINSTANCE.getAppConstants_Valueapikey();

    /**
     * The meta object literal for the '<em><b>Packageentities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference APP_CONSTANTS__PACKAGEENTITIES = eINSTANCE.getAppConstants_Packageentities();

    /**
     * The meta object literal for the '{@link org.xtext.orcasdk.entitymodel.entityModel.impl.PackagesImpl <em>Packages</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.orcasdk.entitymodel.entityModel.impl.PackagesImpl
     * @see org.xtext.orcasdk.entitymodel.entityModel.impl.EntityModelPackageImpl#getPackages()
     * @generated
     */
    EClass PACKAGES = eINSTANCE.getPackages();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PACKAGES__NAME = eINSTANCE.getPackages_Name();

    /**
     * The meta object literal for the '<em><b>Entities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PACKAGES__ENTITIES = eINSTANCE.getPackages_Entities();

    /**
     * The meta object literal for the '{@link org.xtext.orcasdk.entitymodel.entityModel.impl.AndroidEntityImpl <em>Android Entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.orcasdk.entitymodel.entityModel.impl.AndroidEntityImpl
     * @see org.xtext.orcasdk.entitymodel.entityModel.impl.EntityModelPackageImpl#getAndroidEntity()
     * @generated
     */
    EClass ANDROID_ENTITY = eINSTANCE.getAndroidEntity();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANDROID_ENTITY__NAME = eINSTANCE.getAndroidEntity_Name();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANDROID_ENTITY__VALUE = eINSTANCE.getAndroidEntity_Value();

    /**
     * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANDROID_ENTITY__ATTRIBUTES = eINSTANCE.getAndroidEntity_Attributes();

    /**
     * The meta object literal for the '{@link org.xtext.orcasdk.entitymodel.entityModel.impl.AndroidAttributeImpl <em>Android Attribute</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.orcasdk.entitymodel.entityModel.impl.AndroidAttributeImpl
     * @see org.xtext.orcasdk.entitymodel.entityModel.impl.EntityModelPackageImpl#getAndroidAttribute()
     * @generated
     */
    EClass ANDROID_ATTRIBUTE = eINSTANCE.getAndroidAttribute();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANDROID_ATTRIBUTE__TYPE = eINSTANCE.getAndroidAttribute_Type();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANDROID_ATTRIBUTE__NAME = eINSTANCE.getAndroidAttribute_Name();

    /**
     * The meta object literal for the '<em><b>Object Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANDROID_ATTRIBUTE__OBJECT_TYPE = eINSTANCE.getAndroidAttribute_ObjectType();

    /**
     * The meta object literal for the '<em><b>Linkproperties</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANDROID_ATTRIBUTE__LINKPROPERTIES = eINSTANCE.getAndroidAttribute_Linkproperties();

    /**
     * The meta object literal for the '{@link org.xtext.orcasdk.entitymodel.entityModel.impl.LinkPropertiesImpl <em>Link Properties</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.orcasdk.entitymodel.entityModel.impl.LinkPropertiesImpl
     * @see org.xtext.orcasdk.entitymodel.entityModel.impl.EntityModelPackageImpl#getLinkProperties()
     * @generated
     */
    EClass LINK_PROPERTIES = eINSTANCE.getLinkProperties();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LINK_PROPERTIES__TYPE = eINSTANCE.getLinkProperties_Type();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LINK_PROPERTIES__NAME = eINSTANCE.getLinkProperties_Name();

  }

} //EntityModelPackage

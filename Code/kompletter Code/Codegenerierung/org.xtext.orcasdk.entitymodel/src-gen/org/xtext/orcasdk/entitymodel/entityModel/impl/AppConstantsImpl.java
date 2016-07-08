/**
 */
package org.xtext.orcasdk.entitymodel.entityModel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.xtext.orcasdk.entitymodel.entityModel.AppConstants;
import org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage;
import org.xtext.orcasdk.entitymodel.entityModel.Packages;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>App Constants</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.impl.AppConstantsImpl#getValueappname <em>Valueappname</em>}</li>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.impl.AppConstantsImpl#getValueapikey <em>Valueapikey</em>}</li>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.impl.AppConstantsImpl#getPackageentities <em>Packageentities</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AppConstantsImpl extends MinimalEObjectImpl.Container implements AppConstants
{
  /**
   * The cached value of the '{@link #getValueappname() <em>Valueappname</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValueappname()
   * @generated
   * @ordered
   */
  protected EList<String> valueappname;

  /**
   * The cached value of the '{@link #getValueapikey() <em>Valueapikey</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValueapikey()
   * @generated
   * @ordered
   */
  protected EList<String> valueapikey;

  /**
   * The cached value of the '{@link #getPackageentities() <em>Packageentities</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPackageentities()
   * @generated
   * @ordered
   */
  protected EList<Packages> packageentities;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AppConstantsImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return EntityModelPackage.Literals.APP_CONSTANTS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getValueappname()
  {
    if (valueappname == null)
    {
      valueappname = new EDataTypeEList<String>(String.class, this, EntityModelPackage.APP_CONSTANTS__VALUEAPPNAME);
    }
    return valueappname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getValueapikey()
  {
    if (valueapikey == null)
    {
      valueapikey = new EDataTypeEList<String>(String.class, this, EntityModelPackage.APP_CONSTANTS__VALUEAPIKEY);
    }
    return valueapikey;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Packages> getPackageentities()
  {
    if (packageentities == null)
    {
      packageentities = new EObjectContainmentEList<Packages>(Packages.class, this, EntityModelPackage.APP_CONSTANTS__PACKAGEENTITIES);
    }
    return packageentities;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case EntityModelPackage.APP_CONSTANTS__PACKAGEENTITIES:
        return ((InternalEList<?>)getPackageentities()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case EntityModelPackage.APP_CONSTANTS__VALUEAPPNAME:
        return getValueappname();
      case EntityModelPackage.APP_CONSTANTS__VALUEAPIKEY:
        return getValueapikey();
      case EntityModelPackage.APP_CONSTANTS__PACKAGEENTITIES:
        return getPackageentities();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case EntityModelPackage.APP_CONSTANTS__VALUEAPPNAME:
        getValueappname().clear();
        getValueappname().addAll((Collection<? extends String>)newValue);
        return;
      case EntityModelPackage.APP_CONSTANTS__VALUEAPIKEY:
        getValueapikey().clear();
        getValueapikey().addAll((Collection<? extends String>)newValue);
        return;
      case EntityModelPackage.APP_CONSTANTS__PACKAGEENTITIES:
        getPackageentities().clear();
        getPackageentities().addAll((Collection<? extends Packages>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case EntityModelPackage.APP_CONSTANTS__VALUEAPPNAME:
        getValueappname().clear();
        return;
      case EntityModelPackage.APP_CONSTANTS__VALUEAPIKEY:
        getValueapikey().clear();
        return;
      case EntityModelPackage.APP_CONSTANTS__PACKAGEENTITIES:
        getPackageentities().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case EntityModelPackage.APP_CONSTANTS__VALUEAPPNAME:
        return valueappname != null && !valueappname.isEmpty();
      case EntityModelPackage.APP_CONSTANTS__VALUEAPIKEY:
        return valueapikey != null && !valueapikey.isEmpty();
      case EntityModelPackage.APP_CONSTANTS__PACKAGEENTITIES:
        return packageentities != null && !packageentities.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (valueappname: ");
    result.append(valueappname);
    result.append(", valueapikey: ");
    result.append(valueapikey);
    result.append(')');
    return result.toString();
  }

} //AppConstantsImpl

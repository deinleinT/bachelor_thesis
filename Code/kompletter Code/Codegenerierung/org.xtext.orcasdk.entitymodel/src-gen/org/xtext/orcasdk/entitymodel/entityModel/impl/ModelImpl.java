/**
 */
package org.xtext.orcasdk.entitymodel.entityModel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.xtext.orcasdk.entitymodel.entityModel.AppConstants;
import org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage;
import org.xtext.orcasdk.entitymodel.entityModel.Model;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.impl.ModelImpl#getAppconstants <em>Appconstants</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelImpl extends MinimalEObjectImpl.Container implements Model
{
  /**
   * The cached value of the '{@link #getAppconstants() <em>Appconstants</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAppconstants()
   * @generated
   * @ordered
   */
  protected AppConstants appconstants;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModelImpl()
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
    return EntityModelPackage.Literals.MODEL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AppConstants getAppconstants()
  {
    return appconstants;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAppconstants(AppConstants newAppconstants, NotificationChain msgs)
  {
    AppConstants oldAppconstants = appconstants;
    appconstants = newAppconstants;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EntityModelPackage.MODEL__APPCONSTANTS, oldAppconstants, newAppconstants);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAppconstants(AppConstants newAppconstants)
  {
    if (newAppconstants != appconstants)
    {
      NotificationChain msgs = null;
      if (appconstants != null)
        msgs = ((InternalEObject)appconstants).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EntityModelPackage.MODEL__APPCONSTANTS, null, msgs);
      if (newAppconstants != null)
        msgs = ((InternalEObject)newAppconstants).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EntityModelPackage.MODEL__APPCONSTANTS, null, msgs);
      msgs = basicSetAppconstants(newAppconstants, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EntityModelPackage.MODEL__APPCONSTANTS, newAppconstants, newAppconstants));
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
      case EntityModelPackage.MODEL__APPCONSTANTS:
        return basicSetAppconstants(null, msgs);
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
      case EntityModelPackage.MODEL__APPCONSTANTS:
        return getAppconstants();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case EntityModelPackage.MODEL__APPCONSTANTS:
        setAppconstants((AppConstants)newValue);
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
      case EntityModelPackage.MODEL__APPCONSTANTS:
        setAppconstants((AppConstants)null);
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
      case EntityModelPackage.MODEL__APPCONSTANTS:
        return appconstants != null;
    }
    return super.eIsSet(featureID);
  }

} //ModelImpl

/**
 */
package org.xtext.orcasdk.entitymodel.entityModel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute;
import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity;
import org.xtext.orcasdk.entitymodel.entityModel.EntityModelPackage;
import org.xtext.orcasdk.entitymodel.entityModel.LinkProperties;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Android Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.impl.AndroidAttributeImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.impl.AndroidAttributeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.impl.AndroidAttributeImpl#getObjectType <em>Object Type</em>}</li>
 *   <li>{@link org.xtext.orcasdk.entitymodel.entityModel.impl.AndroidAttributeImpl#getLinkproperties <em>Linkproperties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AndroidAttributeImpl extends MinimalEObjectImpl.Container implements AndroidAttribute
{
  /**
   * The default value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected static final String TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected String type = TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getObjectType() <em>Object Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getObjectType()
   * @generated
   * @ordered
   */
  protected AndroidEntity objectType;

  /**
   * The cached value of the '{@link #getLinkproperties() <em>Linkproperties</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLinkproperties()
   * @generated
   * @ordered
   */
  protected EList<LinkProperties> linkproperties;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AndroidAttributeImpl()
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
    return EntityModelPackage.Literals.ANDROID_ATTRIBUTE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(String newType)
  {
    String oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EntityModelPackage.ANDROID_ATTRIBUTE__TYPE, oldType, type));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EntityModelPackage.ANDROID_ATTRIBUTE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AndroidEntity getObjectType()
  {
    if (objectType != null && objectType.eIsProxy())
    {
      InternalEObject oldObjectType = (InternalEObject)objectType;
      objectType = (AndroidEntity)eResolveProxy(oldObjectType);
      if (objectType != oldObjectType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EntityModelPackage.ANDROID_ATTRIBUTE__OBJECT_TYPE, oldObjectType, objectType));
      }
    }
    return objectType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AndroidEntity basicGetObjectType()
  {
    return objectType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setObjectType(AndroidEntity newObjectType)
  {
    AndroidEntity oldObjectType = objectType;
    objectType = newObjectType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EntityModelPackage.ANDROID_ATTRIBUTE__OBJECT_TYPE, oldObjectType, objectType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<LinkProperties> getLinkproperties()
  {
    if (linkproperties == null)
    {
      linkproperties = new EObjectContainmentEList<LinkProperties>(LinkProperties.class, this, EntityModelPackage.ANDROID_ATTRIBUTE__LINKPROPERTIES);
    }
    return linkproperties;
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
      case EntityModelPackage.ANDROID_ATTRIBUTE__LINKPROPERTIES:
        return ((InternalEList<?>)getLinkproperties()).basicRemove(otherEnd, msgs);
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
      case EntityModelPackage.ANDROID_ATTRIBUTE__TYPE:
        return getType();
      case EntityModelPackage.ANDROID_ATTRIBUTE__NAME:
        return getName();
      case EntityModelPackage.ANDROID_ATTRIBUTE__OBJECT_TYPE:
        if (resolve) return getObjectType();
        return basicGetObjectType();
      case EntityModelPackage.ANDROID_ATTRIBUTE__LINKPROPERTIES:
        return getLinkproperties();
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
      case EntityModelPackage.ANDROID_ATTRIBUTE__TYPE:
        setType((String)newValue);
        return;
      case EntityModelPackage.ANDROID_ATTRIBUTE__NAME:
        setName((String)newValue);
        return;
      case EntityModelPackage.ANDROID_ATTRIBUTE__OBJECT_TYPE:
        setObjectType((AndroidEntity)newValue);
        return;
      case EntityModelPackage.ANDROID_ATTRIBUTE__LINKPROPERTIES:
        getLinkproperties().clear();
        getLinkproperties().addAll((Collection<? extends LinkProperties>)newValue);
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
      case EntityModelPackage.ANDROID_ATTRIBUTE__TYPE:
        setType(TYPE_EDEFAULT);
        return;
      case EntityModelPackage.ANDROID_ATTRIBUTE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EntityModelPackage.ANDROID_ATTRIBUTE__OBJECT_TYPE:
        setObjectType((AndroidEntity)null);
        return;
      case EntityModelPackage.ANDROID_ATTRIBUTE__LINKPROPERTIES:
        getLinkproperties().clear();
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
      case EntityModelPackage.ANDROID_ATTRIBUTE__TYPE:
        return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
      case EntityModelPackage.ANDROID_ATTRIBUTE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EntityModelPackage.ANDROID_ATTRIBUTE__OBJECT_TYPE:
        return objectType != null;
      case EntityModelPackage.ANDROID_ATTRIBUTE__LINKPROPERTIES:
        return linkproperties != null && !linkproperties.isEmpty();
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
    result.append(" (type: ");
    result.append(type);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //AndroidAttributeImpl

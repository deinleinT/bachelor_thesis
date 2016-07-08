package org.xtext.orcasdk.entitymodel.generator;

import com.google.common.collect.Iterables;
import java.util.HashMap;
import java.util.HashSet;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute;
import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity;
import org.xtext.orcasdk.entitymodel.entityModel.AppConstants;
import org.xtext.orcasdk.entitymodel.entityModel.Packages;
import org.xtext.orcasdk.entitymodel.generator.HelperClass;
import org.xtext.orcasdk.entitymodel.generator.backend.CreateBackendPersistenceWrapper;
import org.xtext.orcasdk.entitymodel.generator.backend.CreateIndividualPersistenceWrapper;
import org.xtext.orcasdk.entitymodel.generator.constants.CreateAppConstants;
import org.xtext.orcasdk.entitymodel.generator.constants.CreateEntityTypeConstants;
import org.xtext.orcasdk.entitymodel.generator.database.CreateIndividualDatabaseWrapper;
import org.xtext.orcasdk.entitymodel.generator.entity.CreateEntity;
import org.xtext.orcasdk.entitymodel.generator.entity.property.CreatePropertyCallback;
import org.xtext.orcasdk.entitymodel.generator.entity.property.CreatePropertyEntityAsyncTask;
import org.xtext.orcasdk.entitymodel.generator.link.CreateGetAllLinkBelongsToAsyncTask;
import org.xtext.orcasdk.entitymodel.generator.link.CreateIndividualPostLinksFromSameTypeAsyncTask;

@SuppressWarnings("all")
public class EntityModelGenerator implements IGenerator {
  private final HashSet<String> listToCheckAndSetCorrectEntityTypes = new HashSet<String>();
  
  private int tempVariableToCheckAndSetCorrectEntityType;
  
  private String theCorrectEntityType;
  
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    this.loopOverAppConstants(resource, fsa);
  }
  
  public void loopOverAppConstants(final Resource resource, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<AppConstants> _filter = Iterables.<AppConstants>filter(_iterable, AppConstants.class);
    for (final AppConstants appconstants : _filter) {
      {
        this.loopOverPackages(resource, fsa);
        this.generateAppConstantsClass(fsa, appconstants);
        this.generateBackendPersistenceWrapper(fsa);
        this.initializeEntityTypeCheckVariables();
      }
    }
  }
  
  public void loopOverPackages(final Resource resource, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<Packages> _filter = Iterables.<Packages>filter(_iterable, Packages.class);
    for (final Packages packages : _filter) {
      {
        this.loopOverAndroidEntities(packages, fsa);
        this.generateEntityTypeConstantsClass(fsa, packages);
      }
    }
  }
  
  public void loopOverAndroidEntities(final Packages packages, final IFileSystemAccess fsa) {
    EList<AndroidEntity> _entities = packages.getEntities();
    for (final AndroidEntity androidEntity : _entities) {
      {
        this.entityTypeCheck(androidEntity);
        this.loopOverAttributes(androidEntity, fsa, packages);
        this.generateSeveralClasses(fsa, androidEntity, packages);
        this.clearHelperClassImageAndLinksHashMap();
      }
    }
  }
  
  public void loopOverAttributes(final AndroidEntity androidEntity, final IFileSystemAccess fsa, final Packages packages) {
    EList<AndroidAttribute> _attributes = androidEntity.getAttributes();
    for (final AndroidAttribute attribute : _attributes) {
      String _type = attribute.getType();
      boolean _isAttributeTypeImage = this.isAttributeTypeImage(_type);
      if (_isAttributeTypeImage) {
        this.setImageImportsHelperClassVariable(androidEntity);
      } else {
        String _type_1 = attribute.getType();
        boolean _isAttributeTypeLink = this.isAttributeTypeLink(_type_1);
        if (_isAttributeTypeLink) {
          this.setLinkImportsHelperClassVariable(androidEntity, attribute);
          this.generateGetAllEntitiesFromLinkClass(fsa, attribute, androidEntity, packages);
        } else {
          this.generateDatatypeCallback(fsa, attribute);
          this.generateGetDatatypePropertyClass(fsa, attribute);
        }
      }
    }
  }
  
  public String setLinkImportsHelperClassVariable(final AndroidEntity androidEntity, final AndroidAttribute attribute) {
    String _name = androidEntity.getName();
    String _name_1 = attribute.getName();
    return HelperClass.links.put(_name, _name_1);
  }
  
  public String setImageImportsHelperClassVariable(final AndroidEntity androidEntity) {
    String _name = androidEntity.getName();
    return HelperClass.image.put(_name, "image");
  }
  
  public void generateGetDatatypePropertyClass(final IFileSystemAccess fsa, final AndroidAttribute attribute) {
    String _replace = "de.fhws.sdk.orca.property".replace(".", "/");
    String _plus = (_replace + "/");
    String _plus_1 = (_plus + "Get");
    String _type = attribute.getType();
    String _firstUpper = StringExtensions.toFirstUpper(_type);
    String _replace_1 = _firstUpper.replace("[", "Array");
    String _replace_2 = _replace_1.replace("]", "");
    String _plus_2 = (_plus_1 + _replace_2);
    String _plus_3 = (_plus_2 + "PropertyFromBackend");
    String _plus_4 = (_plus_3 + ".java");
    CharSequence _compilepropertyasynctask = CreatePropertyEntityAsyncTask.compilepropertyasynctask(attribute);
    fsa.generateFile(_plus_4, _compilepropertyasynctask);
  }
  
  public void generateDatatypeCallback(final IFileSystemAccess fsa, final AndroidAttribute attribute) {
    String _replace = "de.fhws.sdk.orca.callback".replace(".", "/");
    String _plus = (_replace + "/");
    String _plus_1 = (_plus + "I");
    String _type = attribute.getType();
    String _firstUpper = StringExtensions.toFirstUpper(_type);
    String _replace_1 = _firstUpper.replace("[", "Array");
    String _replace_2 = _replace_1.replace("]", "");
    String _plus_2 = (_plus_1 + _replace_2);
    String _plus_3 = (_plus_2 + "Callback");
    String _plus_4 = (_plus_3 + ".java");
    CharSequence _compilepropertycallback = CreatePropertyCallback.compilepropertycallback(attribute);
    fsa.generateFile(_plus_4, _compilepropertycallback);
  }
  
  public void generateGetAllEntitiesFromLinkClass(final IFileSystemAccess fsa, final AndroidAttribute attribute, final AndroidEntity androidEntity, final Packages packages) {
    String _replace = "de.fhws.sdk.orca.link".replace(".", "/");
    String _plus = (_replace + "/");
    String _plus_1 = (_plus + "GetAll");
    AndroidEntity _objectType = attribute.getObjectType();
    String _name = _objectType.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus_2 = (_plus_1 + _firstUpper);
    String _plus_3 = (_plus_2 + "sFromLink");
    String _name_1 = attribute.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
    String _plus_4 = (_plus_3 + _firstUpper_1);
    String _plus_5 = (_plus_4 + "BelongsToClass");
    String _name_2 = packages.getName();
    String _replace_1 = _name_2.replace(".", "");
    String _plus_6 = (_plus_5 + _replace_1);
    String _name_3 = androidEntity.getName();
    String _firstUpper_2 = StringExtensions.toFirstUpper(_name_3);
    String _plus_7 = (_plus_6 + _firstUpper_2);
    String _plus_8 = (_plus_7 + ".java");
    String _name_4 = packages.getName();
    String _name_5 = androidEntity.getName();
    CharSequence _compilespecialasynctask = CreateGetAllLinkBelongsToAsyncTask.compilespecialasynctask(attribute, _name_4, _name_5);
    fsa.generateFile(_plus_8, _compilespecialasynctask);
  }
  
  public void generateBackendPersistenceWrapper(final IFileSystemAccess fsa) {
    CharSequence _compileBackendPersistenceWrapper = CreateBackendPersistenceWrapper.compileBackendPersistenceWrapper();
    fsa.generateFile((((("de/fhws/sdk/orca/persistence" + "/") + "wrapper") + "/") + "BackendPersistenceWrapper.java"), _compileBackendPersistenceWrapper);
  }
  
  public void generateIndividualBackendPersistenceWrapper(final IFileSystemAccess fsa, final Packages packages, final AndroidEntity androidEntity) {
    String _name = packages.getName();
    String _replace = _name.replace(".", "/");
    String _plus = (_replace + "/");
    String _plus_1 = (_plus + "persistence/backend");
    String _plus_2 = (_plus_1 + "/");
    String _name_1 = androidEntity.getName();
    String _plus_3 = (_plus_2 + _name_1);
    String _plus_4 = (_plus_3 + "/");
    String _name_2 = androidEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name_2);
    String _plus_5 = (_plus_4 + _firstUpper);
    String _plus_6 = (_plus_5 + "PersistenceWrapper.java");
    String _name_3 = packages.getName();
    CharSequence _compileIndividualEntityWrapper = CreateIndividualPersistenceWrapper.compileIndividualEntityWrapper(androidEntity, _name_3);
    fsa.generateFile(_plus_6, _compileIndividualEntityWrapper);
  }
  
  public void generateEntityTypeConstantsClass(final IFileSystemAccess fsa, final Packages packages) {
    String _name = packages.getName();
    String _replace = _name.replace(".", "/");
    String _plus = (_replace + "/");
    String _plus_1 = (_plus + "EntityTypeConstants");
    String _plus_2 = (_plus_1 + ".java");
    CharSequence _packagecompile = CreateEntityTypeConstants.packagecompile(packages);
    fsa.generateFile(_plus_2, _packagecompile);
  }
  
  public void generateAppConstantsClass(final IFileSystemAccess fsa, final AppConstants appconstants) {
    EList<String> _valueappname = appconstants.getValueappname();
    String _string = _valueappname.toString();
    String _replace = _string.replace("[", "");
    String _replace_1 = _replace.replace("]", "");
    EList<String> _valueapikey = appconstants.getValueapikey();
    String _string_1 = _valueapikey.toString();
    String _replace_2 = _string_1.replace("[", "");
    String _replace_3 = _replace_2.replace("]", "");
    CharSequence _appconstantscompile = CreateAppConstants.appconstantscompile(_replace_1, _replace_3);
    fsa.generateFile(((("de/fhws/sdk/orca" + "/") + "AppConstants") + ".java"), _appconstantscompile);
  }
  
  public void generateEntityClass(final IFileSystemAccess fsa, final AndroidEntity androidEntity, final Packages packages) {
    String _name = packages.getName();
    String _replace = _name.replace(".", "/");
    String _plus = (_replace + "/");
    String _name_1 = androidEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name_1);
    String _plus_1 = (_plus + _firstUpper);
    String _plus_2 = (_plus_1 + ".java");
    String _name_2 = packages.getName();
    CharSequence _compileEntity = CreateEntity.compileEntity(androidEntity, _name_2, this.theCorrectEntityType);
    fsa.generateFile(_plus_2, _compileEntity);
  }
  
  public void generatePostLinksFromSameTypeClass(final IFileSystemAccess fsa, final AndroidEntity androidEntity, final Packages packages) {
    String _replace = "de.fhws.sdk.orca.link.".replace(".", "/");
    String _plus = (_replace + "PostLinksFromSameType");
    String _name = packages.getName();
    String _replace_1 = _name.replace(".", "");
    String _plus_1 = (_plus + _replace_1);
    String _name_1 = androidEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name_1);
    String _plus_2 = (_plus_1 + _firstUpper);
    String _plus_3 = (_plus_2 + ".java");
    String _name_2 = packages.getName();
    CharSequence _createPostLinkAsyncTask = CreateIndividualPostLinksFromSameTypeAsyncTask.createPostLinkAsyncTask(androidEntity, _name_2);
    fsa.generateFile(_plus_3, _createPostLinkAsyncTask);
  }
  
  public void generateIndividualDatabasePersistenceWrapper(final IFileSystemAccess fsa, final AndroidEntity androidEntity, final Packages packages) {
    String _name = packages.getName();
    String _replace = _name.replace(".", "/");
    String _plus = (_replace + "/");
    String _plus_1 = (_plus + "persistence/database");
    String _plus_2 = (_plus_1 + "/");
    String _name_1 = androidEntity.getName();
    String _plus_3 = (_plus_2 + _name_1);
    String _plus_4 = (_plus_3 + "/");
    String _name_2 = androidEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name_2);
    String _plus_5 = (_plus_4 + _firstUpper);
    String _plus_6 = (_plus_5 + "DatabaseWrapper.java");
    String _name_3 = packages.getName();
    CharSequence _createIndividualDatabaseWrapper = CreateIndividualDatabaseWrapper.createIndividualDatabaseWrapper(androidEntity, _name_3);
    fsa.generateFile(_plus_6, _createIndividualDatabaseWrapper);
  }
  
  public void clearHelperClassImageAndLinksHashMap() {
    HelperClass.image.clear();
    HelperClass.links.clear();
  }
  
  public void initializeEntityTypeCheckVariables() {
    HashMap<String, String> _hashMap = new HashMap<String, String>();
    HelperClass.packageEntitiesTypes = _hashMap;
    this.listToCheckAndSetCorrectEntityTypes.clear();
  }
  
  public String entityTypeCheck(final AndroidEntity androidEntity) {
    String _xblockexpression = null;
    {
      EList<Integer> _value = androidEntity.getValue();
      String _string = _value.toString();
      String _replace = _string.replace("[", "");
      String _replace_1 = _replace.replace("]", "");
      this.checkEntityTypeAndSetToCorrectValue(_replace_1);
      String _name = androidEntity.getName();
      _xblockexpression = HelperClass.packageEntitiesTypes.put(_name, this.theCorrectEntityType);
    }
    return _xblockexpression;
  }
  
  public void generateSeveralClasses(final IFileSystemAccess fsa, final AndroidEntity androidEntity, final Packages packages) {
    this.generatePostLinksFromSameTypeClass(fsa, androidEntity, packages);
    this.generateEntityClass(fsa, androidEntity, packages);
    this.generateIndividualBackendPersistenceWrapper(fsa, packages, androidEntity);
    this.generateIndividualDatabasePersistenceWrapper(fsa, androidEntity, packages);
  }
  
  public boolean isAttributeTypeLink(final String string) {
    return string.equalsIgnoreCase("link");
  }
  
  public boolean isAttributeTypeImage(final String string) {
    return string.equalsIgnoreCase("image");
  }
  
  public void checkEntityTypeAndSetToCorrectValue(final String string) {
    this.theCorrectEntityType = string;
    boolean _contains = this.listToCheckAndSetCorrectEntityTypes.contains(this.theCorrectEntityType);
    boolean _not = (!_contains);
    if (_not) {
      this.listToCheckAndSetCorrectEntityTypes.add(this.theCorrectEntityType);
    } else {
      while (this.listToCheckAndSetCorrectEntityTypes.contains(this.theCorrectEntityType)) {
        {
          int _parseInt = Integer.parseInt(this.theCorrectEntityType);
          this.tempVariableToCheckAndSetCorrectEntityType = _parseInt;
          int _tempVariableToCheckAndSetCorrectEntityType = this.tempVariableToCheckAndSetCorrectEntityType;
          this.tempVariableToCheckAndSetCorrectEntityType = (_tempVariableToCheckAndSetCorrectEntityType + 1);
          String _valueOf = String.valueOf(this.tempVariableToCheckAndSetCorrectEntityType);
          this.theCorrectEntityType = _valueOf;
          boolean _contains_1 = this.listToCheckAndSetCorrectEntityTypes.contains(this.theCorrectEntityType);
          boolean _not_1 = (!_contains_1);
          if (_not_1) {
            this.listToCheckAndSetCorrectEntityTypes.add(this.theCorrectEntityType);
            return;
          }
        }
      }
    }
  }
}

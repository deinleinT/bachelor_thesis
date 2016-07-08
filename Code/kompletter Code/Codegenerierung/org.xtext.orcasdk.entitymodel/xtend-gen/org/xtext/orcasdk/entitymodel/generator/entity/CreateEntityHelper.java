package org.xtext.orcasdk.entitymodel.generator.entity;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute;
import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity;
import org.xtext.orcasdk.entitymodel.entityModel.LinkProperties;
import org.xtext.orcasdk.entitymodel.generator.HelperClass;
import org.xtext.orcasdk.entitymodel.generator.entity.property.CreateAndroidEntityProperties;
import org.xtext.orcasdk.entitymodel.generator.entity.property.CreateEntityPropertiesMethods;

@SuppressWarnings("all")
public class CreateEntityHelper {
  public static CharSequence createEntityClassComment() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Vom Generator erzeugte EntityKlasse. Die spezifischen Methoden für alle");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Properties, Links und Images werden anhand der Eingaben im Model erzeugt.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Diese Klasse leitet von {@linkplain de.fhws.sdk.orca.model.Entity Entity} ab");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* und erbt die entsprechend benötigten Methoden.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @author ThomasDeinlein");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    return _builder;
  }
  
  public static CharSequence createEntityImports(final AndroidEntity androidEntity, final String packagename) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _createEntityAdditionalImports = CreateEntityHelper.createEntityAdditionalImports(androidEntity);
    _builder.append(_createEntityAdditionalImports, "");
    _builder.newLineIfNotEmpty();
    _builder.append("import com.owlike.genson.annotation.JsonIgnore;");
    _builder.newLine();
    _builder.append("import java.util.*;");
    _builder.newLine();
    _builder.append("import android.content.Context;");
    _builder.newLine();
    _builder.append("import android.database.SQLException;");
    _builder.newLine();
    _builder.append("import de.fhws.sdk.orca.model.Entity;");
    _builder.newLine();
    _builder.append("import de.fhws.sdk.orca.helper.Constants;");
    _builder.newLine();
    _builder.append("import de.fhws.sdk.orca.helper.NetworkHelper;");
    _builder.newLine();
    _builder.append("import de.fhws.sdk.orca.model.EntityPage;");
    _builder.newLine();
    _builder.append("import ");
    _builder.append(packagename, "");
    _builder.append(".persistence.backend.");
    String _name = androidEntity.getName();
    _builder.append(_name, "");
    _builder.append(".");
    String _name_1 = androidEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name_1);
    _builder.append(_firstUpper, "");
    _builder.append("PersistenceWrapper;");
    _builder.newLineIfNotEmpty();
    _builder.append("import de.fhws.sdk.orca.network.callback.IReturnValueCallback;");
    _builder.newLine();
    _builder.append("import de.fhws.sdk.orca.network.callback.IPageCallback;");
    _builder.newLine();
    _builder.append("import de.fhws.sdk.orca.network.callback.INoReturnValueCallback;");
    _builder.newLine();
    _builder.append("import de.fhws.sdk.orca.network.callback.IAllEntityIdsCallback;");
    _builder.newLine();
    _builder.append("import de.fhws.sdk.orca.network.callback.ILinkPageCallback;");
    _builder.newLine();
    _builder.append("import de.fhws.sdk.orca.network.callback.IImageCallback;");
    _builder.newLine();
    _builder.append("import ");
    _builder.append(packagename, "");
    _builder.append(".persistence.database.");
    String _name_2 = androidEntity.getName();
    _builder.append(_name_2, "");
    _builder.append(".");
    String _name_3 = androidEntity.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_3);
    _builder.append(_firstUpper_1, "");
    _builder.append("DatabaseWrapper;");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public static CharSequence createEntityAdditionalImports(final AndroidEntity androidEntity) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _name = androidEntity.getName();
      boolean _containsKey = HelperClass.image.containsKey(_name);
      if (_containsKey) {
        String _get = ((String[])Conversions.unwrapArray(HelperClass.imageimport, String.class))[0];
        _builder.append(_get, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      String _name_1 = androidEntity.getName();
      boolean _containsKey_1 = HelperClass.links.containsKey(_name_1);
      if (_containsKey_1) {
        String _get_1 = ((String[])Conversions.unwrapArray(HelperClass.linkimport, String.class))[0];
        _builder.append(_get_1, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public static CharSequence createAllEntityProperties(final AndroidEntity androidEntity) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<AndroidAttribute> _attributes = androidEntity.getAttributes();
      for(final AndroidAttribute attribute : _attributes) {
        CharSequence _compileattributes = CreateAndroidEntityProperties.compileattributes(attribute);
        _builder.append(_compileattributes, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public static CharSequence createEntityConstructor(final AndroidEntity androidEntity, final String theCorrectEntityType) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("* StandardConstructor dieses EntityTyps. In diesem werden der EntityType,");
    _builder.newLine();
    _builder.append("* der im Model eingegeben wurde, gesetzt. Ebenso werden alle");
    _builder.newLine();
    _builder.append("* EntityProperty, LinkProperty und ImageKeys in die dafür vorgesehenen");
    _builder.newLine();
    _builder.append("* Hashmaps eingetragen, damit die Zugriff auf die lokale Datenbank möglich");
    _builder.newLine();
    _builder.append("* ist.");
    _builder.newLine();
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public ");
    String _name = androidEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append("(){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("super.setType(");
    _builder.append(theCorrectEntityType, "\t");
    _builder.append("l);");
    _builder.newLineIfNotEmpty();
    {
      EList<AndroidAttribute> _attributes = androidEntity.getAttributes();
      for(final AndroidAttribute attributes : _attributes) {
        {
          String _type = attributes.getType();
          boolean _equalsIgnoreCase = _type.equalsIgnoreCase("link");
          if (_equalsIgnoreCase) {
            {
              EList<LinkProperties> _linkproperties = attributes.getLinkproperties();
              for(final LinkProperties linkprops : _linkproperties) {
                _builder.append("\t");
                _builder.append("super.linkPropertyDatatypes.put(\"");
                String _name_1 = attributes.getName();
                _builder.append(_name_1, "\t");
                _builder.append(" ");
                String _name_2 = linkprops.getName();
                _builder.append(_name_2, "\t");
                _builder.append("\",\"");
                String _type_1 = linkprops.getType();
                _builder.append(_type_1, "\t");
                _builder.append("\");");
                _builder.newLineIfNotEmpty();
              }
            }
          } else {
            String _type_2 = attributes.getType();
            boolean _equalsIgnoreCase_1 = _type_2.equalsIgnoreCase("image");
            if (_equalsIgnoreCase_1) {
              _builder.append("\t");
              _builder.append("super.imagePropertyNames.add(\"");
              String _name_3 = attributes.getName();
              _builder.append(_name_3, "\t");
              _builder.append("\");");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("\t");
              _builder.append("super.entityPropertyDatatypes.put(\"");
              String _name_4 = attributes.getName();
              _builder.append(_name_4, "\t");
              _builder.append("\",\"");
              String _type_3 = attributes.getType();
              _builder.append(_type_3, "\t");
              _builder.append("\");");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public static CharSequence createAllEntityPropertyMethods(final AndroidEntity androidEntity, final String packagename) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<AndroidAttribute> _attributes = androidEntity.getAttributes();
      for(final AndroidAttribute attribute : _attributes) {
        CharSequence _compileEntityAttributesMethods = CreateEntityPropertiesMethods.compileEntityAttributesMethods(attribute, androidEntity, packagename);
        _builder.append(_compileEntityAttributesMethods, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
}

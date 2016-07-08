package org.xtext.orcasdk.entitymodel.generator.constants;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity;
import org.xtext.orcasdk.entitymodel.entityModel.Packages;
import org.xtext.orcasdk.entitymodel.generator.HelperClass;

@SuppressWarnings("all")
public class CreateEntityTypeConstants {
  public static CharSequence packagecompile(final Packages pack) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _name = pack.getName();
    _builder.append(_name, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("public class EntityTypeConstants {");
    _builder.newLine();
    _builder.newLine();
    {
      EList<AndroidEntity> _entities = pack.getEntities();
      for(final AndroidEntity aentity : _entities) {
        _builder.append("public static final Long TYPE_");
        String _name_1 = pack.getName();
        String _replace = _name_1.replace(".", "_");
        String _upperCase = _replace.toUpperCase();
        _builder.append(_upperCase, "");
        _builder.append("_");
        String _name_2 = aentity.getName();
        String _upperCase_1 = _name_2.toUpperCase();
        _builder.append(_upperCase_1, "");
        _builder.append(" = ");
        String _name_3 = aentity.getName();
        String _get = HelperClass.packageEntitiesTypes.get(_name_3);
        _builder.append(_get, "");
        _builder.append("l;");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}

package org.xtext.orcasdk.entitymodel.generator.entity;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity;
import org.xtext.orcasdk.entitymodel.generator.entity.CreateEntityHelper;
import org.xtext.orcasdk.entitymodel.generator.entity.property.helper.CreateEntityInstanceMethodsHelper;
import org.xtext.orcasdk.entitymodel.generator.entity.property.helper.CreateEntityStaticMethodsHelper;

@SuppressWarnings("all")
public class CreateEntity {
  public static CharSequence compileEntity(final AndroidEntity androidEntity, final String packagename, final String theCorrectEntityType) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(packagename, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _createEntityImports = CreateEntityHelper.createEntityImports(androidEntity, packagename);
    _builder.append(_createEntityImports, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _createEntityClassComment = CreateEntityHelper.createEntityClassComment();
    _builder.append(_createEntityClassComment, "");
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    String _name = androidEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append(" extends Entity{ ");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _createAllEntityProperties = CreateEntityHelper.createAllEntityProperties(androidEntity);
    _builder.append(_createAllEntityProperties, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _createEntityConstructor = CreateEntityHelper.createEntityConstructor(androidEntity, theCorrectEntityType);
    _builder.append(_createEntityConstructor, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _createAllEntityPropertyMethods = CreateEntityHelper.createAllEntityPropertyMethods(androidEntity, packagename);
    _builder.append(_createAllEntityPropertyMethods, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("// zuerst die InstanzMethoden");
    _builder.newLine();
    _builder.newLine();
    CharSequence _createAllInstanceMethode = CreateEntityInstanceMethodsHelper.createAllInstanceMethode(androidEntity, packagename);
    _builder.append(_createAllInstanceMethode, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("// hier die Klassenmethoden");
    _builder.newLine();
    _builder.newLine();
    CharSequence _createAllStaticMethods = CreateEntityStaticMethodsHelper.createAllStaticMethods(androidEntity, packagename);
    _builder.append(_createAllStaticMethods, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}

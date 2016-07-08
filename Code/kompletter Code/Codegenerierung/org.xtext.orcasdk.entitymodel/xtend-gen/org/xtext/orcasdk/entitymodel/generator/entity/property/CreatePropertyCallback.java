package org.xtext.orcasdk.entitymodel.generator.entity.property;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute;

@SuppressWarnings("all")
public class CreatePropertyCallback {
  public static CharSequence compilepropertycallback(final AndroidAttribute attribute) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package de.fhws.sdk.orca.callback;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    {
      String _type = attribute.getType();
      boolean _equalsIgnoreCase = _type.equalsIgnoreCase("date");
      if (_equalsIgnoreCase) {
        _builder.append("import java.util.Date;");
        _builder.newLine();
      }
    }
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("public interface I");
    String _type_1 = attribute.getType();
    String _firstUpper = StringExtensions.toFirstUpper(_type_1);
    String _replace = _firstUpper.replace("[", "Array");
    String _replace_1 = _replace.replace("]", "");
    _builder.append(_replace_1, "");
    _builder.append("Callback{");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("void onComplete(");
    String _type_2 = attribute.getType();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_type_2);
    _builder.append(_firstUpper_1, "\t");
    _builder.append(" returnValue, int statusCode, String errorMessage);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}

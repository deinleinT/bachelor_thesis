package org.xtext.orcasdk.entitymodel.generator.constants;

import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class CreateAppConstants {
  public static CharSequence appconstantscompile(final String appName, final String apiKey) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package de.fhws.sdk.orca;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class AppConstants {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static final String APPNAME = \"");
    _builder.append(appName, "\t");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("public static final String APIKEY = \"");
    _builder.append(apiKey, "\t");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}

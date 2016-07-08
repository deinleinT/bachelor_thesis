package org.xtext.orcasdk.entitymodel.generator.link;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.xtext.orcasdk.entitymodel.entityModel.AndroidAttribute;
import org.xtext.orcasdk.entitymodel.entityModel.AndroidEntity;

@SuppressWarnings("all")
public class CreateGetAllLinkBelongsToAsyncTask {
  public static CharSequence compilespecialasynctask(final AndroidAttribute attribute, final String packagename, final String androidEntityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package de.fhws.sdk.orca.link;");
    _builder.newLine();
    _builder.append("import java.io.IOException;");
    _builder.newLine();
    _builder.append("import java.io.UnsupportedEncodingException;");
    _builder.newLine();
    _builder.append("import java.net.URLEncoder;");
    _builder.newLine();
    _builder.append("import java.util.ArrayList;");
    _builder.newLine();
    _builder.append("import org.apache.http.HttpResponse;");
    _builder.newLine();
    _builder.append("import org.apache.http.client.ClientProtocolException;");
    _builder.newLine();
    _builder.append("import org.apache.http.client.HttpClient;");
    _builder.newLine();
    _builder.append("import org.apache.http.client.methods.HttpGet;");
    _builder.newLine();
    _builder.append("import org.apache.http.impl.client.DefaultHttpClient;");
    _builder.newLine();
    _builder.append("import org.apache.http.util.EntityUtils;");
    _builder.newLine();
    _builder.append("import android.os.AsyncTask;");
    _builder.newLine();
    _builder.append("import com.owlike.genson.GenericType;");
    _builder.newLine();
    _builder.append("import com.owlike.genson.Genson;");
    _builder.newLine();
    _builder.append("import de.fhws.sdk.orca.helper.*;");
    _builder.newLine();
    _builder.append("import de.fhws.sdk.orca.model.Entity;");
    _builder.newLine();
    _builder.append("import de.fhws.sdk.orca.model.EntityPage;");
    _builder.newLine();
    _builder.append("import de.fhws.sdk.orca.network.callback.IEntityPageWithEntityCallback;");
    _builder.newLine();
    _builder.append("import android.util.Log;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* AsyncClass-Klasse: Führt einen HTTP-Get im Backend durch. Hierüber können");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* alle EntityObjects abgerufen werden, auf die ein Link dieses Typs");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* zeigt. ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* BACKEND-ENDPUNKT:");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* /api/{appname}/entities/{id}/links/{linkType}/objects");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @author ThomasDeinlein");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param <T> Klasse, die von {@linkplain de.fhws.sdk.orca.model.Entity Entity}");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            ableitet");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class GetAll");
    AndroidEntity _objectType = attribute.getObjectType();
    String _name = _objectType.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append("sFromLink");
    String _name_1 = attribute.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
    _builder.append(_firstUpper_1, "");
    _builder.append("BelongsToClass");
    String _replace = packagename.replace(".", "");
    _builder.append(_replace, "");
    String _firstUpper_2 = StringExtensions.toFirstUpper(androidEntityName);
    _builder.append(_firstUpper_2, "");
    _builder.append("<T extends Entity>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("extends AsyncTask<Void, Void, EntityPage<T>> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String\t\t\t\t\t\t\t\t\tgetUrl;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String\t\t\t\t\t\t\t\t\terrorMessage\t= Constants.NO_ERROR;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String\t\t\t\t\t\t\t\t\tappName;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String\t\t\t\t\t\t\t\t\tapiKey;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private IEntityPageWithEntityCallback<");
    _builder.append(packagename, "\t");
    _builder.append(".");
    AndroidEntity _objectType_1 = attribute.getObjectType();
    String _name_2 = _objectType_1.getName();
    String _firstUpper_3 = StringExtensions.toFirstUpper(_name_2);
    _builder.append(_firstUpper_3, "\t");
    _builder.append(">\tcallback;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("private int\t\t\t\t\t\t\t\t\t\tstatusCodeHttpGet;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private EntityPage<T>\tretValue = new EntityPage<T>();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private HttpClient\t\t\t\t\t\t\t\thttpGetClient;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private HttpGet\t\t\t\t\t\t\t\t\thttpGet;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private HttpResponse\t\t\t\t\t\t\thttpGetResponse;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String\t\t\t\t\t\t\t\t\tresponseJSONString;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private int\t\t\t\t\t\t\t\t\t\tentityId;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String\t\t\t\t\t\t\t\t\tlinkType;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private Class<? extends Entity>\t\t\t\t\tclassType;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public GetAll");
    AndroidEntity _objectType_2 = attribute.getObjectType();
    String _name_3 = _objectType_2.getName();
    String _firstUpper_4 = StringExtensions.toFirstUpper(_name_3);
    _builder.append(_firstUpper_4, "\t");
    _builder.append("sFromLink");
    String _name_4 = attribute.getName();
    String _firstUpper_5 = StringExtensions.toFirstUpper(_name_4);
    _builder.append(_firstUpper_5, "\t");
    _builder.append("BelongsToClass");
    String _replace_1 = packagename.replace(".", "");
    _builder.append(_replace_1, "\t");
    String _firstUpper_6 = StringExtensions.toFirstUpper(androidEntityName);
    _builder.append(_firstUpper_6, "\t");
    _builder.append("(int entityId,");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("String linkType, Class<? extends Entity> classType, String appName,");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("String apiKey, IEntityPageWithEntityCallback<");
    _builder.append(packagename, "\t\t\t");
    _builder.append(".");
    AndroidEntity _objectType_3 = attribute.getObjectType();
    String _name_5 = _objectType_3.getName();
    String _firstUpper_7 = StringExtensions.toFirstUpper(_name_5);
    _builder.append(_firstUpper_7, "\t\t\t");
    _builder.append("> callback) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.callback = callback;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.classType = classType;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.entityId = entityId;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.linkType = linkType;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.appName = appName;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.apiKey = apiKey;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected EntityPage<T> doInBackground(Void... params) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (checkParameters()) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("setGetURL();");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("setHttpClientAndHeader();");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("executeHttpResponseAndSetResponseString();");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("if (NetworkHelper.isStatusCode200(statusCodeHttpGet)) {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("createEntityPage();");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("if (isPageSizeZero()) {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("errorMessage = Constants.NO_LINKS_FOUND;");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("statusCodeHttpGet = -1;");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("else if (NetworkHelper.isStatusCode403Or404(statusCodeHttpGet)) {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("errorMessage = EntityUtils.toString(");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("httpGetResponse.getEntity(), Constants.UTF_STRING);");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("else {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("errorMessage = \"StatusCode is \" + statusCodeHttpGet + \".\";");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("statusCodeHttpGet = -1;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("else {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("statusCodeHttpGet = -1;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("errorMessage = \"GET-Request not possible: EntityId is 0 or invalid linkType, or AppName or Apikey\";");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("catch (UnsupportedEncodingException e) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Log.e(Constants.TAG_BACKEND,");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("\"UnsupportedEncodingException \" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("errorMessage = \"UnsupportedEncodingException \" + e.getMessage();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("statusCodeHttpGet = -1;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("catch (IOException e) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Log.e(Constants.TAG_BACKEND, \"IOException \" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("errorMessage = \"IOException \" + e.getMessage();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("statusCodeHttpGet = -1;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("catch (NullPointerException e) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Log.e(Constants.TAG_BACKEND,");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("\"NullPointerException \" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("errorMessage = \"NullPointerException \" + e.getMessage();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("statusCodeHttpGet = -1;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("catch (Exception e) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Log.e(Constants.TAG_BACKEND, \"Exception \" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("errorMessage = \"Exception \" + e.getMessage();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("statusCodeHttpGet = -1;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return retValue;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private void executeHttpResponseAndSetResponseString() throws IOException,");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("ClientProtocolException {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("httpGetResponse = httpGetClient.execute(httpGet);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("statusCodeHttpGet = httpGetResponse.getStatusLine().getStatusCode();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("responseJSONString = EntityUtils.toString(httpGetResponse.getEntity(),");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("Constants.UTF_STRING);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private void setHttpClientAndHeader() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("httpGetClient = new DefaultHttpClient();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("httpGet = new HttpGet(getUrl);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("httpGet.addHeader(Constants.APIKEYHEADER, apiKey);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private boolean checkParameters() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return linkType != null && !linkType.isEmpty() && appName != null");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("&& !appName.isEmpty() && apiKey != null");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("&& !apiKey.isEmpty() && entityId > 0 && classType != null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private void setGetURL() throws UnsupportedEncodingException {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("getUrl = Constants.BACKEND_URI");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("+ URLEncoder.encode(appName, Constants.UTF_STRING)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("+ \"/\" + Constants.ENTITIES + \"/\" + entityId + \"/\"");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("+ Constants.LINKS + \"/\"");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("+ URLEncoder.encode(linkType, Constants.UTF_STRING)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("+ \"/\" + Constants.OBJECTS;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private boolean isPageSizeZero() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return retValue.getTotalSize() == 0;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private void createEntityPage() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Genson genson = new Genson();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("retValue = genson.deserialize(responseJSONString,");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("new GenericType<EntityPage<T>>() {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("});");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private EntityPage<");
    _builder.append(packagename, "\t");
    _builder.append(".");
    AndroidEntity _objectType_4 = attribute.getObjectType();
    String _name_6 = _objectType_4.getName();
    String _firstUpper_8 = StringExtensions.toFirstUpper(_name_6);
    _builder.append(_firstUpper_8, "\t");
    _builder.append("> setCorrectFormatToReturnEntityPage(");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("EntityPage<T> result) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("EntityPage<");
    _builder.append(packagename, "\t\t\t");
    _builder.append(".");
    AndroidEntity _objectType_5 = attribute.getObjectType();
    String _name_7 = _objectType_5.getName();
    String _firstUpper_9 = StringExtensions.toFirstUpper(_name_7);
    _builder.append(_firstUpper_9, "\t\t\t");
    _builder.append("> returnEntityPage = new EntityPage<");
    _builder.append(packagename, "\t\t\t");
    _builder.append(".");
    AndroidEntity _objectType_6 = attribute.getObjectType();
    String _name_8 = _objectType_6.getName();
    String _firstUpper_10 = StringExtensions.toFirstUpper(_name_8);
    _builder.append(_firstUpper_10, "\t\t\t");
    _builder.append(">();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("returnEntityPage.setTotalSize(result.getTotalSize());");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("returnEntityPage.setPageSize(result.getPageSize());");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("returnEntityPage.setNavigationLinks(result.getNavigationLinks());");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("ArrayList<");
    _builder.append(packagename, "\t\t\t");
    _builder.append(".");
    AndroidEntity _objectType_7 = attribute.getObjectType();
    String _name_9 = _objectType_7.getName();
    String _firstUpper_11 = StringExtensions.toFirstUpper(_name_9);
    _builder.append(_firstUpper_11, "\t\t\t");
    _builder.append("> entityList = new ArrayList<");
    _builder.append(packagename, "\t\t\t");
    _builder.append(".");
    AndroidEntity _objectType_8 = attribute.getObjectType();
    String _name_10 = _objectType_8.getName();
    String _firstUpper_12 = StringExtensions.toFirstUpper(_name_10);
    _builder.append(_firstUpper_12, "\t\t\t");
    _builder.append(">();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("for(T entity : retValue.getEntities()){");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append(packagename, "\t\t\t\t");
    _builder.append(".");
    AndroidEntity _objectType_9 = attribute.getObjectType();
    String _name_11 = _objectType_9.getName();
    String _firstUpper_13 = StringExtensions.toFirstUpper(_name_11);
    _builder.append(_firstUpper_13, "\t\t\t\t");
    _builder.append(" object = new ");
    _builder.append(packagename, "\t\t\t\t");
    _builder.append(".");
    AndroidEntity _objectType_10 = attribute.getObjectType();
    String _name_12 = _objectType_10.getName();
    String _firstUpper_14 = StringExtensions.toFirstUpper(_name_12);
    _builder.append(_firstUpper_14, "\t\t\t\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.append("object.setId(entity.getId());");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("object.setLinks(entity.getLinks());");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("object.setSelf(entity.getSelf());");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("object.setProperties(entity.getProperties());");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("entityList.add(object);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("returnEntityPage.setEntities(entityList);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return returnEntityPage;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected void onPostExecute(EntityPage<T> result) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (callback != null && result != null) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("EntityPage<");
    _builder.append(packagename, "\t\t\t");
    _builder.append(".");
    AndroidEntity _objectType_11 = attribute.getObjectType();
    String _name_13 = _objectType_11.getName();
    String _firstUpper_15 = StringExtensions.toFirstUpper(_name_13);
    _builder.append(_firstUpper_15, "\t\t\t");
    _builder.append("> returnPage = setCorrectFormatToReturnEntityPage(result);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("callback.onComplete(returnPage, statusCodeHttpGet, errorMessage);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    return _builder;
  }
}
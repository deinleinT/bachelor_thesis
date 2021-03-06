/*
* generated by Xtext
*/
grammar InternalEntityModel;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package org.xtext.orcasdk.entitymodel.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.xtext.orcasdk.entitymodel.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.xtext.orcasdk.entitymodel.services.EntityModelGrammarAccess;

}

@parser::members {

 	private EntityModelGrammarAccess grammarAccess;
 	
    public InternalEntityModelParser(TokenStream input, EntityModelGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "Model";	
   	}
   	
   	@Override
   	protected EntityModelGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleModel
entryRuleModel returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getModelRule()); }
	 iv_ruleModel=ruleModel 
	 { $current=$iv_ruleModel.current; } 
	 EOF 
;

// Rule Model
ruleModel returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{ 
	        newCompositeNode(grammarAccess.getModelAccess().getAppconstantsAppConstantsParserRuleCall_0()); 
	    }
		lv_appconstants_0_0=ruleAppConstants		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getModelRule());
	        }
       		set(
       			$current, 
       			"appconstants",
        		lv_appconstants_0_0, 
        		"AppConstants");
	        afterParserOrEnumRuleCall();
	    }

)
)?
;





// Entry rule entryRuleAppConstants
entryRuleAppConstants returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getAppConstantsRule()); }
	 iv_ruleAppConstants=ruleAppConstants 
	 { $current=$iv_ruleAppConstants.current; } 
	 EOF 
;

// Rule AppConstants
ruleAppConstants returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='appconstants {' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getAppConstantsAccess().getAppconstantsKeyword_0());
    }
	otherlv_1='appname = ' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getAppConstantsAccess().getAppnameKeyword_1());
    }
(
(
		lv_valueappname_2_0=RULE_STRING
		{
			newLeafNode(lv_valueappname_2_0, grammarAccess.getAppConstantsAccess().getValueappnameSTRINGTerminalRuleCall_2_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAppConstantsRule());
	        }
       		addWithLastConsumed(
       			$current, 
       			"valueappname",
        		lv_valueappname_2_0, 
        		"STRING");
	    }

)
)*	otherlv_3=';' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getAppConstantsAccess().getSemicolonKeyword_3());
    }
	otherlv_4='apikey = ' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getAppConstantsAccess().getApikeyKeyword_4());
    }
(
(
		lv_valueapikey_5_0=RULE_STRING
		{
			newLeafNode(lv_valueapikey_5_0, grammarAccess.getAppConstantsAccess().getValueapikeySTRINGTerminalRuleCall_5_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAppConstantsRule());
	        }
       		addWithLastConsumed(
       			$current, 
       			"valueapikey",
        		lv_valueapikey_5_0, 
        		"STRING");
	    }

)
)*	otherlv_6=';' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getAppConstantsAccess().getSemicolonKeyword_6());
    }
	otherlv_7='}' 
    {
    	newLeafNode(otherlv_7, grammarAccess.getAppConstantsAccess().getRightCurlyBracketKeyword_7());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getAppConstantsAccess().getPackageentitiesPackagesParserRuleCall_8_0()); 
	    }
		lv_packageentities_8_0=rulePackages		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getAppConstantsRule());
	        }
       		add(
       			$current, 
       			"packageentities",
        		lv_packageentities_8_0, 
        		"Packages");
	        afterParserOrEnumRuleCall();
	    }

)
)+)
;





// Entry rule entryRulePackages
entryRulePackages returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getPackagesRule()); }
	 iv_rulePackages=rulePackages 
	 { $current=$iv_rulePackages.current; } 
	 EOF 
;

// Rule Packages
rulePackages returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='package' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getPackagesAccess().getPackageKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getPackagesAccess().getNameQualifiedNameParserRuleCall_1_0()); 
	    }
		lv_name_1_0=ruleQualifiedName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getPackagesRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"QualifiedName");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_2='{' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getPackagesAccess().getLeftCurlyBracketKeyword_2());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getPackagesAccess().getEntitiesAndroidEntityParserRuleCall_3_0()); 
	    }
		lv_entities_3_0=ruleAndroidEntity		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getPackagesRule());
	        }
       		add(
       			$current, 
       			"entities",
        		lv_entities_3_0, 
        		"AndroidEntity");
	        afterParserOrEnumRuleCall();
	    }

)
)+	otherlv_4='}' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getPackagesAccess().getRightCurlyBracketKeyword_4());
    }
)
;





// Entry rule entryRuleQualifiedName
entryRuleQualifiedName returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getQualifiedNameRule()); } 
	 iv_ruleQualifiedName=ruleQualifiedName 
	 { $current=$iv_ruleQualifiedName.current.getText(); }  
	 EOF 
;

// Rule QualifiedName
ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(    this_ID_0=RULE_ID    {
		$current.merge(this_ID_0);
    }

    { 
    newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 
    }
(
	kw='.' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
    }
    this_ID_2=RULE_ID    {
		$current.merge(this_ID_2);
    }

    { 
    newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 
    }
)*)
    ;





// Entry rule entryRuleAndroidEntity
entryRuleAndroidEntity returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getAndroidEntityRule()); }
	 iv_ruleAndroidEntity=ruleAndroidEntity 
	 { $current=$iv_ruleAndroidEntity.current; } 
	 EOF 
;

// Rule AndroidEntity
ruleAndroidEntity returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='entity' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getAndroidEntityAccess().getEntityKeyword_0());
    }
(
(
		lv_name_1_0=RULE_ID
		{
			newLeafNode(lv_name_1_0, grammarAccess.getAndroidEntityAccess().getNameIDTerminalRuleCall_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidEntityRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"ID");
	    }

)
)	otherlv_2='{' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getAndroidEntityAccess().getLeftCurlyBracketKeyword_2());
    }
	otherlv_3='TYPE = ' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getAndroidEntityAccess().getTYPEKeyword_3());
    }
(
(
		lv_value_4_0=RULE_INT
		{
			newLeafNode(lv_value_4_0, grammarAccess.getAndroidEntityAccess().getValueINTTerminalRuleCall_4_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidEntityRule());
	        }
       		addWithLastConsumed(
       			$current, 
       			"value",
        		lv_value_4_0, 
        		"INT");
	    }

)
)+	otherlv_5=';' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getAndroidEntityAccess().getSemicolonKeyword_5());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getAndroidEntityAccess().getAttributesAndroidAttributeParserRuleCall_6_0()); 
	    }
		lv_attributes_6_0=ruleAndroidAttribute		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getAndroidEntityRule());
	        }
       		add(
       			$current, 
       			"attributes",
        		lv_attributes_6_0, 
        		"AndroidAttribute");
	        afterParserOrEnumRuleCall();
	    }

)
)+	otherlv_7='}' 
    {
    	newLeafNode(otherlv_7, grammarAccess.getAndroidEntityAccess().getRightCurlyBracketKeyword_7());
    }
)
;





// Entry rule entryRuleAndroidAttribute
entryRuleAndroidAttribute returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getAndroidAttributeRule()); }
	 iv_ruleAndroidAttribute=ruleAndroidAttribute 
	 { $current=$iv_ruleAndroidAttribute.current; } 
	 EOF 
;

// Rule AndroidAttribute
ruleAndroidAttribute returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(((
(
		lv_type_0_0=	'string' 
    {
        newLeafNode(lv_type_0_0, grammarAccess.getAndroidAttributeAccess().getTypeStringKeyword_0_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed($current, "type", lv_type_0_0, "string");
	    }

)
)(
(
		lv_name_1_0=RULE_ID
		{
			newLeafNode(lv_name_1_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_0_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"ID");
	    }

)
)	otherlv_2=';' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getAndroidAttributeAccess().getSemicolonKeyword_0_2());
    }
)
    |((
(
		lv_type_3_0=	'boolean' 
    {
        newLeafNode(lv_type_3_0, grammarAccess.getAndroidAttributeAccess().getTypeBooleanKeyword_1_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed($current, "type", lv_type_3_0, "boolean");
	    }

)
)(
(
		lv_name_4_0=RULE_ID
		{
			newLeafNode(lv_name_4_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_1_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_4_0, 
        		"ID");
	    }

)
)	otherlv_5=';' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getAndroidAttributeAccess().getSemicolonKeyword_1_2());
    }
)
    |((
(
		lv_type_6_0=	'long' 
    {
        newLeafNode(lv_type_6_0, grammarAccess.getAndroidAttributeAccess().getTypeLongKeyword_2_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed($current, "type", lv_type_6_0, "long");
	    }

)
)(
(
		lv_name_7_0=RULE_ID
		{
			newLeafNode(lv_name_7_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_2_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_7_0, 
        		"ID");
	    }

)
)	otherlv_8=';' 
    {
    	newLeafNode(otherlv_8, grammarAccess.getAndroidAttributeAccess().getSemicolonKeyword_2_2());
    }
)
    |((
(
		lv_type_9_0=	'double' 
    {
        newLeafNode(lv_type_9_0, grammarAccess.getAndroidAttributeAccess().getTypeDoubleKeyword_3_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed($current, "type", lv_type_9_0, "double");
	    }

)
)(
(
		lv_name_10_0=RULE_ID
		{
			newLeafNode(lv_name_10_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_3_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_10_0, 
        		"ID");
	    }

)
)	otherlv_11=';' 
    {
    	newLeafNode(otherlv_11, grammarAccess.getAndroidAttributeAccess().getSemicolonKeyword_3_2());
    }
)
    |((
(
		lv_type_12_0=	'double[]' 
    {
        newLeafNode(lv_type_12_0, grammarAccess.getAndroidAttributeAccess().getTypeDoubleKeyword_4_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed($current, "type", lv_type_12_0, "double[]");
	    }

)
)(
(
		lv_name_13_0=RULE_ID
		{
			newLeafNode(lv_name_13_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_4_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_13_0, 
        		"ID");
	    }

)
)	otherlv_14=';' 
    {
    	newLeafNode(otherlv_14, grammarAccess.getAndroidAttributeAccess().getSemicolonKeyword_4_2());
    }
)
    |((
(
		lv_type_15_0=	'date' 
    {
        newLeafNode(lv_type_15_0, grammarAccess.getAndroidAttributeAccess().getTypeDateKeyword_5_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed($current, "type", lv_type_15_0, "date");
	    }

)
)(
(
		lv_name_16_0=RULE_ID
		{
			newLeafNode(lv_name_16_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_5_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_16_0, 
        		"ID");
	    }

)
)	otherlv_17=';' 
    {
    	newLeafNode(otherlv_17, grammarAccess.getAndroidAttributeAccess().getSemicolonKeyword_5_2());
    }
)
    |((
(
		lv_type_18_0=	'image' 
    {
        newLeafNode(lv_type_18_0, grammarAccess.getAndroidAttributeAccess().getTypeImageKeyword_6_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed($current, "type", lv_type_18_0, "image");
	    }

)
)(
(
		lv_name_19_0=RULE_ID
		{
			newLeafNode(lv_name_19_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_6_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_19_0, 
        		"ID");
	    }

)
)	otherlv_20=';' 
    {
    	newLeafNode(otherlv_20, grammarAccess.getAndroidAttributeAccess().getSemicolonKeyword_6_2());
    }
)
    |((
(
		lv_type_21_0=	'link' 
    {
        newLeafNode(lv_type_21_0, grammarAccess.getAndroidAttributeAccess().getTypeLinkKeyword_7_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed($current, "type", lv_type_21_0, "link");
	    }

)
)(
(
		lv_name_22_0=RULE_ID
		{
			newLeafNode(lv_name_22_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_7_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_22_0, 
        		"ID");
	    }

)
)(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getAndroidAttributeRule());
	        }
        }
	otherlv_23=RULE_ID
	{
		newLeafNode(otherlv_23, grammarAccess.getAndroidAttributeAccess().getObjectTypeAndroidEntityCrossReference_7_2_0()); 
	}

)
)	otherlv_24='{' 
    {
    	newLeafNode(otherlv_24, grammarAccess.getAndroidAttributeAccess().getLeftCurlyBracketKeyword_7_3());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getAndroidAttributeAccess().getLinkpropertiesLinkPropertiesParserRuleCall_7_4_0()); 
	    }
		lv_linkproperties_25_0=ruleLinkProperties		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getAndroidAttributeRule());
	        }
       		add(
       			$current, 
       			"linkproperties",
        		lv_linkproperties_25_0, 
        		"LinkProperties");
	        afterParserOrEnumRuleCall();
	    }

)
)+	otherlv_26='}' 
    {
    	newLeafNode(otherlv_26, grammarAccess.getAndroidAttributeAccess().getRightCurlyBracketKeyword_7_5());
    }
))
;





// Entry rule entryRuleLinkProperties
entryRuleLinkProperties returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getLinkPropertiesRule()); }
	 iv_ruleLinkProperties=ruleLinkProperties 
	 { $current=$iv_ruleLinkProperties.current; } 
	 EOF 
;

// Rule LinkProperties
ruleLinkProperties returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(((
(
		lv_type_0_0=	'string' 
    {
        newLeafNode(lv_type_0_0, grammarAccess.getLinkPropertiesAccess().getTypeStringKeyword_0_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLinkPropertiesRule());
	        }
       		setWithLastConsumed($current, "type", lv_type_0_0, "string");
	    }

)
)(
(
		lv_name_1_0=RULE_ID
		{
			newLeafNode(lv_name_1_0, grammarAccess.getLinkPropertiesAccess().getNameIDTerminalRuleCall_0_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLinkPropertiesRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"ID");
	    }

)
)	otherlv_2=';' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getLinkPropertiesAccess().getSemicolonKeyword_0_2());
    }
)
    |((
(
		lv_type_3_0=	'boolean' 
    {
        newLeafNode(lv_type_3_0, grammarAccess.getLinkPropertiesAccess().getTypeBooleanKeyword_1_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLinkPropertiesRule());
	        }
       		setWithLastConsumed($current, "type", lv_type_3_0, "boolean");
	    }

)
)(
(
		lv_name_4_0=RULE_ID
		{
			newLeafNode(lv_name_4_0, grammarAccess.getLinkPropertiesAccess().getNameIDTerminalRuleCall_1_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLinkPropertiesRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_4_0, 
        		"ID");
	    }

)
)	otherlv_5=';' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getLinkPropertiesAccess().getSemicolonKeyword_1_2());
    }
)
    |((
(
		lv_type_6_0=	'long' 
    {
        newLeafNode(lv_type_6_0, grammarAccess.getLinkPropertiesAccess().getTypeLongKeyword_2_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLinkPropertiesRule());
	        }
       		setWithLastConsumed($current, "type", lv_type_6_0, "long");
	    }

)
)(
(
		lv_name_7_0=RULE_ID
		{
			newLeafNode(lv_name_7_0, grammarAccess.getLinkPropertiesAccess().getNameIDTerminalRuleCall_2_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLinkPropertiesRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_7_0, 
        		"ID");
	    }

)
)	otherlv_8=';' 
    {
    	newLeafNode(otherlv_8, grammarAccess.getLinkPropertiesAccess().getSemicolonKeyword_2_2());
    }
)
    |((
(
		lv_type_9_0=	'double' 
    {
        newLeafNode(lv_type_9_0, grammarAccess.getLinkPropertiesAccess().getTypeDoubleKeyword_3_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLinkPropertiesRule());
	        }
       		setWithLastConsumed($current, "type", lv_type_9_0, "double");
	    }

)
)(
(
		lv_name_10_0=RULE_ID
		{
			newLeafNode(lv_name_10_0, grammarAccess.getLinkPropertiesAccess().getNameIDTerminalRuleCall_3_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLinkPropertiesRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_10_0, 
        		"ID");
	    }

)
)	otherlv_11=';' 
    {
    	newLeafNode(otherlv_11, grammarAccess.getLinkPropertiesAccess().getSemicolonKeyword_3_2());
    }
)
    |((
(
		lv_type_12_0=	'double[]' 
    {
        newLeafNode(lv_type_12_0, grammarAccess.getLinkPropertiesAccess().getTypeDoubleKeyword_4_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLinkPropertiesRule());
	        }
       		setWithLastConsumed($current, "type", lv_type_12_0, "double[]");
	    }

)
)(
(
		lv_name_13_0=RULE_ID
		{
			newLeafNode(lv_name_13_0, grammarAccess.getLinkPropertiesAccess().getNameIDTerminalRuleCall_4_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLinkPropertiesRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_13_0, 
        		"ID");
	    }

)
)	otherlv_14=';' 
    {
    	newLeafNode(otherlv_14, grammarAccess.getLinkPropertiesAccess().getSemicolonKeyword_4_2());
    }
)
    |((
(
		lv_type_15_0=	'date' 
    {
        newLeafNode(lv_type_15_0, grammarAccess.getLinkPropertiesAccess().getTypeDateKeyword_5_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLinkPropertiesRule());
	        }
       		setWithLastConsumed($current, "type", lv_type_15_0, "date");
	    }

)
)(
(
		lv_name_16_0=RULE_ID
		{
			newLeafNode(lv_name_16_0, grammarAccess.getLinkPropertiesAccess().getNameIDTerminalRuleCall_5_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getLinkPropertiesRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_16_0, 
        		"ID");
	    }

)
)	otherlv_17=';' 
    {
    	newLeafNode(otherlv_17, grammarAccess.getLinkPropertiesAccess().getSemicolonKeyword_5_2());
    }
))
;





RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;



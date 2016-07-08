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



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalEntityModelParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'appconstants {'", "'appname = '", "';'", "'apikey = '", "'}'", "'package'", "'{'", "'.'", "'entity'", "'TYPE = '", "'string'", "'boolean'", "'long'", "'double'", "'double[]'", "'date'", "'image'", "'link'"
    };
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=5;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalEntityModelParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalEntityModelParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalEntityModelParser.tokenNames; }
    public String getGrammarFileName() { return "../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g"; }



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



    // $ANTLR start "entryRuleModel"
    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:67:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:68:2: (iv_ruleModel= ruleModel EOF )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:69:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_ruleModel_in_entryRuleModel75);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModel85); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:76:1: ruleModel returns [EObject current=null] : ( (lv_appconstants_0_0= ruleAppConstants ) )? ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_appconstants_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:79:28: ( ( (lv_appconstants_0_0= ruleAppConstants ) )? )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:80:1: ( (lv_appconstants_0_0= ruleAppConstants ) )?
            {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:80:1: ( (lv_appconstants_0_0= ruleAppConstants ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==11) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:81:1: (lv_appconstants_0_0= ruleAppConstants )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:81:1: (lv_appconstants_0_0= ruleAppConstants )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:82:3: lv_appconstants_0_0= ruleAppConstants
                    {
                     
                    	        newCompositeNode(grammarAccess.getModelAccess().getAppconstantsAppConstantsParserRuleCall_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAppConstants_in_ruleModel130);
                    lv_appconstants_0_0=ruleAppConstants();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getModelRule());
                    	        }
                           		set(
                           			current, 
                           			"appconstants",
                            		lv_appconstants_0_0, 
                            		"AppConstants");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleAppConstants"
    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:106:1: entryRuleAppConstants returns [EObject current=null] : iv_ruleAppConstants= ruleAppConstants EOF ;
    public final EObject entryRuleAppConstants() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAppConstants = null;


        try {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:107:2: (iv_ruleAppConstants= ruleAppConstants EOF )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:108:2: iv_ruleAppConstants= ruleAppConstants EOF
            {
             newCompositeNode(grammarAccess.getAppConstantsRule()); 
            pushFollow(FOLLOW_ruleAppConstants_in_entryRuleAppConstants166);
            iv_ruleAppConstants=ruleAppConstants();

            state._fsp--;

             current =iv_ruleAppConstants; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAppConstants176); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAppConstants"


    // $ANTLR start "ruleAppConstants"
    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:115:1: ruleAppConstants returns [EObject current=null] : (otherlv_0= 'appconstants {' otherlv_1= 'appname = ' ( (lv_valueappname_2_0= RULE_STRING ) )* otherlv_3= ';' otherlv_4= 'apikey = ' ( (lv_valueapikey_5_0= RULE_STRING ) )* otherlv_6= ';' otherlv_7= '}' ( (lv_packageentities_8_0= rulePackages ) )+ ) ;
    public final EObject ruleAppConstants() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_valueappname_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_valueapikey_5_0=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_packageentities_8_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:118:28: ( (otherlv_0= 'appconstants {' otherlv_1= 'appname = ' ( (lv_valueappname_2_0= RULE_STRING ) )* otherlv_3= ';' otherlv_4= 'apikey = ' ( (lv_valueapikey_5_0= RULE_STRING ) )* otherlv_6= ';' otherlv_7= '}' ( (lv_packageentities_8_0= rulePackages ) )+ ) )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:119:1: (otherlv_0= 'appconstants {' otherlv_1= 'appname = ' ( (lv_valueappname_2_0= RULE_STRING ) )* otherlv_3= ';' otherlv_4= 'apikey = ' ( (lv_valueapikey_5_0= RULE_STRING ) )* otherlv_6= ';' otherlv_7= '}' ( (lv_packageentities_8_0= rulePackages ) )+ )
            {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:119:1: (otherlv_0= 'appconstants {' otherlv_1= 'appname = ' ( (lv_valueappname_2_0= RULE_STRING ) )* otherlv_3= ';' otherlv_4= 'apikey = ' ( (lv_valueapikey_5_0= RULE_STRING ) )* otherlv_6= ';' otherlv_7= '}' ( (lv_packageentities_8_0= rulePackages ) )+ )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:119:3: otherlv_0= 'appconstants {' otherlv_1= 'appname = ' ( (lv_valueappname_2_0= RULE_STRING ) )* otherlv_3= ';' otherlv_4= 'apikey = ' ( (lv_valueapikey_5_0= RULE_STRING ) )* otherlv_6= ';' otherlv_7= '}' ( (lv_packageentities_8_0= rulePackages ) )+
            {
            otherlv_0=(Token)match(input,11,FOLLOW_11_in_ruleAppConstants213); 

                	newLeafNode(otherlv_0, grammarAccess.getAppConstantsAccess().getAppconstantsKeyword_0());
                
            otherlv_1=(Token)match(input,12,FOLLOW_12_in_ruleAppConstants225); 

                	newLeafNode(otherlv_1, grammarAccess.getAppConstantsAccess().getAppnameKeyword_1());
                
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:127:1: ( (lv_valueappname_2_0= RULE_STRING ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_STRING) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:128:1: (lv_valueappname_2_0= RULE_STRING )
            	    {
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:128:1: (lv_valueappname_2_0= RULE_STRING )
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:129:3: lv_valueappname_2_0= RULE_STRING
            	    {
            	    lv_valueappname_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleAppConstants242); 

            	    			newLeafNode(lv_valueappname_2_0, grammarAccess.getAppConstantsAccess().getValueappnameSTRINGTerminalRuleCall_2_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getAppConstantsRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"valueappname",
            	            		lv_valueappname_2_0, 
            	            		"STRING");
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleAppConstants260); 

                	newLeafNode(otherlv_3, grammarAccess.getAppConstantsAccess().getSemicolonKeyword_3());
                
            otherlv_4=(Token)match(input,14,FOLLOW_14_in_ruleAppConstants272); 

                	newLeafNode(otherlv_4, grammarAccess.getAppConstantsAccess().getApikeyKeyword_4());
                
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:153:1: ( (lv_valueapikey_5_0= RULE_STRING ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==RULE_STRING) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:154:1: (lv_valueapikey_5_0= RULE_STRING )
            	    {
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:154:1: (lv_valueapikey_5_0= RULE_STRING )
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:155:3: lv_valueapikey_5_0= RULE_STRING
            	    {
            	    lv_valueapikey_5_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleAppConstants289); 

            	    			newLeafNode(lv_valueapikey_5_0, grammarAccess.getAppConstantsAccess().getValueapikeySTRINGTerminalRuleCall_5_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getAppConstantsRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"valueapikey",
            	            		lv_valueapikey_5_0, 
            	            		"STRING");
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            otherlv_6=(Token)match(input,13,FOLLOW_13_in_ruleAppConstants307); 

                	newLeafNode(otherlv_6, grammarAccess.getAppConstantsAccess().getSemicolonKeyword_6());
                
            otherlv_7=(Token)match(input,15,FOLLOW_15_in_ruleAppConstants319); 

                	newLeafNode(otherlv_7, grammarAccess.getAppConstantsAccess().getRightCurlyBracketKeyword_7());
                
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:179:1: ( (lv_packageentities_8_0= rulePackages ) )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==16) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:180:1: (lv_packageentities_8_0= rulePackages )
            	    {
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:180:1: (lv_packageentities_8_0= rulePackages )
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:181:3: lv_packageentities_8_0= rulePackages
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getAppConstantsAccess().getPackageentitiesPackagesParserRuleCall_8_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulePackages_in_ruleAppConstants340);
            	    lv_packageentities_8_0=rulePackages();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getAppConstantsRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"packageentities",
            	            		lv_packageentities_8_0, 
            	            		"Packages");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAppConstants"


    // $ANTLR start "entryRulePackages"
    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:205:1: entryRulePackages returns [EObject current=null] : iv_rulePackages= rulePackages EOF ;
    public final EObject entryRulePackages() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePackages = null;


        try {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:206:2: (iv_rulePackages= rulePackages EOF )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:207:2: iv_rulePackages= rulePackages EOF
            {
             newCompositeNode(grammarAccess.getPackagesRule()); 
            pushFollow(FOLLOW_rulePackages_in_entryRulePackages377);
            iv_rulePackages=rulePackages();

            state._fsp--;

             current =iv_rulePackages; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePackages387); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePackages"


    // $ANTLR start "rulePackages"
    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:214:1: rulePackages returns [EObject current=null] : (otherlv_0= 'package' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_entities_3_0= ruleAndroidEntity ) )+ otherlv_4= '}' ) ;
    public final EObject rulePackages() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_entities_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:217:28: ( (otherlv_0= 'package' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_entities_3_0= ruleAndroidEntity ) )+ otherlv_4= '}' ) )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:218:1: (otherlv_0= 'package' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_entities_3_0= ruleAndroidEntity ) )+ otherlv_4= '}' )
            {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:218:1: (otherlv_0= 'package' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_entities_3_0= ruleAndroidEntity ) )+ otherlv_4= '}' )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:218:3: otherlv_0= 'package' ( (lv_name_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_entities_3_0= ruleAndroidEntity ) )+ otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,16,FOLLOW_16_in_rulePackages424); 

                	newLeafNode(otherlv_0, grammarAccess.getPackagesAccess().getPackageKeyword_0());
                
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:222:1: ( (lv_name_1_0= ruleQualifiedName ) )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:223:1: (lv_name_1_0= ruleQualifiedName )
            {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:223:1: (lv_name_1_0= ruleQualifiedName )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:224:3: lv_name_1_0= ruleQualifiedName
            {
             
            	        newCompositeNode(grammarAccess.getPackagesAccess().getNameQualifiedNameParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedName_in_rulePackages445);
            lv_name_1_0=ruleQualifiedName();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPackagesRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"QualifiedName");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_17_in_rulePackages457); 

                	newLeafNode(otherlv_2, grammarAccess.getPackagesAccess().getLeftCurlyBracketKeyword_2());
                
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:244:1: ( (lv_entities_3_0= ruleAndroidEntity ) )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==19) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:245:1: (lv_entities_3_0= ruleAndroidEntity )
            	    {
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:245:1: (lv_entities_3_0= ruleAndroidEntity )
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:246:3: lv_entities_3_0= ruleAndroidEntity
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getPackagesAccess().getEntitiesAndroidEntityParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAndroidEntity_in_rulePackages478);
            	    lv_entities_3_0=ruleAndroidEntity();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getPackagesRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"entities",
            	            		lv_entities_3_0, 
            	            		"AndroidEntity");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

            otherlv_4=(Token)match(input,15,FOLLOW_15_in_rulePackages491); 

                	newLeafNode(otherlv_4, grammarAccess.getPackagesAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePackages"


    // $ANTLR start "entryRuleQualifiedName"
    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:274:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:275:2: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:276:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
             newCompositeNode(grammarAccess.getQualifiedNameRule()); 
            pushFollow(FOLLOW_ruleQualifiedName_in_entryRuleQualifiedName528);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;

             current =iv_ruleQualifiedName.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedName539); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:283:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:286:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:287:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:287:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:287:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedName579); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 
                
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:294:1: (kw= '.' this_ID_2= RULE_ID )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==18) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:295:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,18,FOLLOW_18_in_ruleQualifiedName598); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
            	        
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedName613); 

            	    		current.merge(this_ID_2);
            	        
            	     
            	        newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "entryRuleAndroidEntity"
    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:315:1: entryRuleAndroidEntity returns [EObject current=null] : iv_ruleAndroidEntity= ruleAndroidEntity EOF ;
    public final EObject entryRuleAndroidEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndroidEntity = null;


        try {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:316:2: (iv_ruleAndroidEntity= ruleAndroidEntity EOF )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:317:2: iv_ruleAndroidEntity= ruleAndroidEntity EOF
            {
             newCompositeNode(grammarAccess.getAndroidEntityRule()); 
            pushFollow(FOLLOW_ruleAndroidEntity_in_entryRuleAndroidEntity660);
            iv_ruleAndroidEntity=ruleAndroidEntity();

            state._fsp--;

             current =iv_ruleAndroidEntity; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndroidEntity670); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAndroidEntity"


    // $ANTLR start "ruleAndroidEntity"
    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:324:1: ruleAndroidEntity returns [EObject current=null] : (otherlv_0= 'entity' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' otherlv_3= 'TYPE = ' ( (lv_value_4_0= RULE_INT ) )+ otherlv_5= ';' ( (lv_attributes_6_0= ruleAndroidAttribute ) )+ otherlv_7= '}' ) ;
    public final EObject ruleAndroidEntity() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_value_4_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_attributes_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:327:28: ( (otherlv_0= 'entity' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' otherlv_3= 'TYPE = ' ( (lv_value_4_0= RULE_INT ) )+ otherlv_5= ';' ( (lv_attributes_6_0= ruleAndroidAttribute ) )+ otherlv_7= '}' ) )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:328:1: (otherlv_0= 'entity' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' otherlv_3= 'TYPE = ' ( (lv_value_4_0= RULE_INT ) )+ otherlv_5= ';' ( (lv_attributes_6_0= ruleAndroidAttribute ) )+ otherlv_7= '}' )
            {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:328:1: (otherlv_0= 'entity' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' otherlv_3= 'TYPE = ' ( (lv_value_4_0= RULE_INT ) )+ otherlv_5= ';' ( (lv_attributes_6_0= ruleAndroidAttribute ) )+ otherlv_7= '}' )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:328:3: otherlv_0= 'entity' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' otherlv_3= 'TYPE = ' ( (lv_value_4_0= RULE_INT ) )+ otherlv_5= ';' ( (lv_attributes_6_0= ruleAndroidAttribute ) )+ otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,19,FOLLOW_19_in_ruleAndroidEntity707); 

                	newLeafNode(otherlv_0, grammarAccess.getAndroidEntityAccess().getEntityKeyword_0());
                
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:332:1: ( (lv_name_1_0= RULE_ID ) )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:333:1: (lv_name_1_0= RULE_ID )
            {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:333:1: (lv_name_1_0= RULE_ID )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:334:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAndroidEntity724); 

            			newLeafNode(lv_name_1_0, grammarAccess.getAndroidEntityAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getAndroidEntityRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_17_in_ruleAndroidEntity741); 

                	newLeafNode(otherlv_2, grammarAccess.getAndroidEntityAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,20,FOLLOW_20_in_ruleAndroidEntity753); 

                	newLeafNode(otherlv_3, grammarAccess.getAndroidEntityAccess().getTYPEKeyword_3());
                
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:358:1: ( (lv_value_4_0= RULE_INT ) )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_INT) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:359:1: (lv_value_4_0= RULE_INT )
            	    {
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:359:1: (lv_value_4_0= RULE_INT )
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:360:3: lv_value_4_0= RULE_INT
            	    {
            	    lv_value_4_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleAndroidEntity770); 

            	    			newLeafNode(lv_value_4_0, grammarAccess.getAndroidEntityAccess().getValueINTTerminalRuleCall_4_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getAndroidEntityRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"value",
            	            		lv_value_4_0, 
            	            		"INT");
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleAndroidEntity788); 

                	newLeafNode(otherlv_5, grammarAccess.getAndroidEntityAccess().getSemicolonKeyword_5());
                
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:380:1: ( (lv_attributes_6_0= ruleAndroidAttribute ) )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>=21 && LA8_0<=28)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:381:1: (lv_attributes_6_0= ruleAndroidAttribute )
            	    {
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:381:1: (lv_attributes_6_0= ruleAndroidAttribute )
            	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:382:3: lv_attributes_6_0= ruleAndroidAttribute
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getAndroidEntityAccess().getAttributesAndroidAttributeParserRuleCall_6_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAndroidAttribute_in_ruleAndroidEntity809);
            	    lv_attributes_6_0=ruleAndroidAttribute();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getAndroidEntityRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"attributes",
            	            		lv_attributes_6_0, 
            	            		"AndroidAttribute");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            otherlv_7=(Token)match(input,15,FOLLOW_15_in_ruleAndroidEntity822); 

                	newLeafNode(otherlv_7, grammarAccess.getAndroidEntityAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAndroidEntity"


    // $ANTLR start "entryRuleAndroidAttribute"
    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:410:1: entryRuleAndroidAttribute returns [EObject current=null] : iv_ruleAndroidAttribute= ruleAndroidAttribute EOF ;
    public final EObject entryRuleAndroidAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndroidAttribute = null;


        try {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:411:2: (iv_ruleAndroidAttribute= ruleAndroidAttribute EOF )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:412:2: iv_ruleAndroidAttribute= ruleAndroidAttribute EOF
            {
             newCompositeNode(grammarAccess.getAndroidAttributeRule()); 
            pushFollow(FOLLOW_ruleAndroidAttribute_in_entryRuleAndroidAttribute858);
            iv_ruleAndroidAttribute=ruleAndroidAttribute();

            state._fsp--;

             current =iv_ruleAndroidAttribute; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndroidAttribute868); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAndroidAttribute"


    // $ANTLR start "ruleAndroidAttribute"
    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:419:1: ruleAndroidAttribute returns [EObject current=null] : ( ( ( (lv_type_0_0= 'string' ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' ) | ( ( (lv_type_3_0= 'boolean' ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ';' ) | ( ( (lv_type_6_0= 'long' ) ) ( (lv_name_7_0= RULE_ID ) ) otherlv_8= ';' ) | ( ( (lv_type_9_0= 'double' ) ) ( (lv_name_10_0= RULE_ID ) ) otherlv_11= ';' ) | ( ( (lv_type_12_0= 'double[]' ) ) ( (lv_name_13_0= RULE_ID ) ) otherlv_14= ';' ) | ( ( (lv_type_15_0= 'date' ) ) ( (lv_name_16_0= RULE_ID ) ) otherlv_17= ';' ) | ( ( (lv_type_18_0= 'image' ) ) ( (lv_name_19_0= RULE_ID ) ) otherlv_20= ';' ) | ( ( (lv_type_21_0= 'link' ) ) ( (lv_name_22_0= RULE_ID ) ) ( (otherlv_23= RULE_ID ) ) otherlv_24= '{' ( (lv_linkproperties_25_0= ruleLinkProperties ) )+ otherlv_26= '}' ) ) ;
    public final EObject ruleAndroidAttribute() throws RecognitionException {
        EObject current = null;

        Token lv_type_0_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token lv_type_3_0=null;
        Token lv_name_4_0=null;
        Token otherlv_5=null;
        Token lv_type_6_0=null;
        Token lv_name_7_0=null;
        Token otherlv_8=null;
        Token lv_type_9_0=null;
        Token lv_name_10_0=null;
        Token otherlv_11=null;
        Token lv_type_12_0=null;
        Token lv_name_13_0=null;
        Token otherlv_14=null;
        Token lv_type_15_0=null;
        Token lv_name_16_0=null;
        Token otherlv_17=null;
        Token lv_type_18_0=null;
        Token lv_name_19_0=null;
        Token otherlv_20=null;
        Token lv_type_21_0=null;
        Token lv_name_22_0=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        Token otherlv_26=null;
        EObject lv_linkproperties_25_0 = null;


         enterRule(); 
            
        try {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:422:28: ( ( ( ( (lv_type_0_0= 'string' ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' ) | ( ( (lv_type_3_0= 'boolean' ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ';' ) | ( ( (lv_type_6_0= 'long' ) ) ( (lv_name_7_0= RULE_ID ) ) otherlv_8= ';' ) | ( ( (lv_type_9_0= 'double' ) ) ( (lv_name_10_0= RULE_ID ) ) otherlv_11= ';' ) | ( ( (lv_type_12_0= 'double[]' ) ) ( (lv_name_13_0= RULE_ID ) ) otherlv_14= ';' ) | ( ( (lv_type_15_0= 'date' ) ) ( (lv_name_16_0= RULE_ID ) ) otherlv_17= ';' ) | ( ( (lv_type_18_0= 'image' ) ) ( (lv_name_19_0= RULE_ID ) ) otherlv_20= ';' ) | ( ( (lv_type_21_0= 'link' ) ) ( (lv_name_22_0= RULE_ID ) ) ( (otherlv_23= RULE_ID ) ) otherlv_24= '{' ( (lv_linkproperties_25_0= ruleLinkProperties ) )+ otherlv_26= '}' ) ) )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:423:1: ( ( ( (lv_type_0_0= 'string' ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' ) | ( ( (lv_type_3_0= 'boolean' ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ';' ) | ( ( (lv_type_6_0= 'long' ) ) ( (lv_name_7_0= RULE_ID ) ) otherlv_8= ';' ) | ( ( (lv_type_9_0= 'double' ) ) ( (lv_name_10_0= RULE_ID ) ) otherlv_11= ';' ) | ( ( (lv_type_12_0= 'double[]' ) ) ( (lv_name_13_0= RULE_ID ) ) otherlv_14= ';' ) | ( ( (lv_type_15_0= 'date' ) ) ( (lv_name_16_0= RULE_ID ) ) otherlv_17= ';' ) | ( ( (lv_type_18_0= 'image' ) ) ( (lv_name_19_0= RULE_ID ) ) otherlv_20= ';' ) | ( ( (lv_type_21_0= 'link' ) ) ( (lv_name_22_0= RULE_ID ) ) ( (otherlv_23= RULE_ID ) ) otherlv_24= '{' ( (lv_linkproperties_25_0= ruleLinkProperties ) )+ otherlv_26= '}' ) )
            {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:423:1: ( ( ( (lv_type_0_0= 'string' ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' ) | ( ( (lv_type_3_0= 'boolean' ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ';' ) | ( ( (lv_type_6_0= 'long' ) ) ( (lv_name_7_0= RULE_ID ) ) otherlv_8= ';' ) | ( ( (lv_type_9_0= 'double' ) ) ( (lv_name_10_0= RULE_ID ) ) otherlv_11= ';' ) | ( ( (lv_type_12_0= 'double[]' ) ) ( (lv_name_13_0= RULE_ID ) ) otherlv_14= ';' ) | ( ( (lv_type_15_0= 'date' ) ) ( (lv_name_16_0= RULE_ID ) ) otherlv_17= ';' ) | ( ( (lv_type_18_0= 'image' ) ) ( (lv_name_19_0= RULE_ID ) ) otherlv_20= ';' ) | ( ( (lv_type_21_0= 'link' ) ) ( (lv_name_22_0= RULE_ID ) ) ( (otherlv_23= RULE_ID ) ) otherlv_24= '{' ( (lv_linkproperties_25_0= ruleLinkProperties ) )+ otherlv_26= '}' ) )
            int alt10=8;
            switch ( input.LA(1) ) {
            case 21:
                {
                alt10=1;
                }
                break;
            case 22:
                {
                alt10=2;
                }
                break;
            case 23:
                {
                alt10=3;
                }
                break;
            case 24:
                {
                alt10=4;
                }
                break;
            case 25:
                {
                alt10=5;
                }
                break;
            case 26:
                {
                alt10=6;
                }
                break;
            case 27:
                {
                alt10=7;
                }
                break;
            case 28:
                {
                alt10=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:423:2: ( ( (lv_type_0_0= 'string' ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:423:2: ( ( (lv_type_0_0= 'string' ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:423:3: ( (lv_type_0_0= 'string' ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';'
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:423:3: ( (lv_type_0_0= 'string' ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:424:1: (lv_type_0_0= 'string' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:424:1: (lv_type_0_0= 'string' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:425:3: lv_type_0_0= 'string'
                    {
                    lv_type_0_0=(Token)match(input,21,FOLLOW_21_in_ruleAndroidAttribute912); 

                            newLeafNode(lv_type_0_0, grammarAccess.getAndroidAttributeAccess().getTypeStringKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(current, "type", lv_type_0_0, "string");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:438:2: ( (lv_name_1_0= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:439:1: (lv_name_1_0= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:439:1: (lv_name_1_0= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:440:3: lv_name_1_0= RULE_ID
                    {
                    lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAndroidAttribute942); 

                    			newLeafNode(lv_name_1_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_0_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_1_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_2=(Token)match(input,13,FOLLOW_13_in_ruleAndroidAttribute959); 

                        	newLeafNode(otherlv_2, grammarAccess.getAndroidAttributeAccess().getSemicolonKeyword_0_2());
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:461:6: ( ( (lv_type_3_0= 'boolean' ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ';' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:461:6: ( ( (lv_type_3_0= 'boolean' ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ';' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:461:7: ( (lv_type_3_0= 'boolean' ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ';'
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:461:7: ( (lv_type_3_0= 'boolean' ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:462:1: (lv_type_3_0= 'boolean' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:462:1: (lv_type_3_0= 'boolean' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:463:3: lv_type_3_0= 'boolean'
                    {
                    lv_type_3_0=(Token)match(input,22,FOLLOW_22_in_ruleAndroidAttribute985); 

                            newLeafNode(lv_type_3_0, grammarAccess.getAndroidAttributeAccess().getTypeBooleanKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(current, "type", lv_type_3_0, "boolean");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:476:2: ( (lv_name_4_0= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:477:1: (lv_name_4_0= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:477:1: (lv_name_4_0= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:478:3: lv_name_4_0= RULE_ID
                    {
                    lv_name_4_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAndroidAttribute1015); 

                    			newLeafNode(lv_name_4_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_1_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_4_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleAndroidAttribute1032); 

                        	newLeafNode(otherlv_5, grammarAccess.getAndroidAttributeAccess().getSemicolonKeyword_1_2());
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:499:6: ( ( (lv_type_6_0= 'long' ) ) ( (lv_name_7_0= RULE_ID ) ) otherlv_8= ';' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:499:6: ( ( (lv_type_6_0= 'long' ) ) ( (lv_name_7_0= RULE_ID ) ) otherlv_8= ';' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:499:7: ( (lv_type_6_0= 'long' ) ) ( (lv_name_7_0= RULE_ID ) ) otherlv_8= ';'
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:499:7: ( (lv_type_6_0= 'long' ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:500:1: (lv_type_6_0= 'long' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:500:1: (lv_type_6_0= 'long' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:501:3: lv_type_6_0= 'long'
                    {
                    lv_type_6_0=(Token)match(input,23,FOLLOW_23_in_ruleAndroidAttribute1058); 

                            newLeafNode(lv_type_6_0, grammarAccess.getAndroidAttributeAccess().getTypeLongKeyword_2_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(current, "type", lv_type_6_0, "long");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:514:2: ( (lv_name_7_0= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:515:1: (lv_name_7_0= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:515:1: (lv_name_7_0= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:516:3: lv_name_7_0= RULE_ID
                    {
                    lv_name_7_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAndroidAttribute1088); 

                    			newLeafNode(lv_name_7_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_2_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_7_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_8=(Token)match(input,13,FOLLOW_13_in_ruleAndroidAttribute1105); 

                        	newLeafNode(otherlv_8, grammarAccess.getAndroidAttributeAccess().getSemicolonKeyword_2_2());
                        

                    }


                    }
                    break;
                case 4 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:537:6: ( ( (lv_type_9_0= 'double' ) ) ( (lv_name_10_0= RULE_ID ) ) otherlv_11= ';' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:537:6: ( ( (lv_type_9_0= 'double' ) ) ( (lv_name_10_0= RULE_ID ) ) otherlv_11= ';' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:537:7: ( (lv_type_9_0= 'double' ) ) ( (lv_name_10_0= RULE_ID ) ) otherlv_11= ';'
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:537:7: ( (lv_type_9_0= 'double' ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:538:1: (lv_type_9_0= 'double' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:538:1: (lv_type_9_0= 'double' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:539:3: lv_type_9_0= 'double'
                    {
                    lv_type_9_0=(Token)match(input,24,FOLLOW_24_in_ruleAndroidAttribute1131); 

                            newLeafNode(lv_type_9_0, grammarAccess.getAndroidAttributeAccess().getTypeDoubleKeyword_3_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(current, "type", lv_type_9_0, "double");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:552:2: ( (lv_name_10_0= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:553:1: (lv_name_10_0= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:553:1: (lv_name_10_0= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:554:3: lv_name_10_0= RULE_ID
                    {
                    lv_name_10_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAndroidAttribute1161); 

                    			newLeafNode(lv_name_10_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_3_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_10_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_11=(Token)match(input,13,FOLLOW_13_in_ruleAndroidAttribute1178); 

                        	newLeafNode(otherlv_11, grammarAccess.getAndroidAttributeAccess().getSemicolonKeyword_3_2());
                        

                    }


                    }
                    break;
                case 5 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:575:6: ( ( (lv_type_12_0= 'double[]' ) ) ( (lv_name_13_0= RULE_ID ) ) otherlv_14= ';' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:575:6: ( ( (lv_type_12_0= 'double[]' ) ) ( (lv_name_13_0= RULE_ID ) ) otherlv_14= ';' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:575:7: ( (lv_type_12_0= 'double[]' ) ) ( (lv_name_13_0= RULE_ID ) ) otherlv_14= ';'
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:575:7: ( (lv_type_12_0= 'double[]' ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:576:1: (lv_type_12_0= 'double[]' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:576:1: (lv_type_12_0= 'double[]' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:577:3: lv_type_12_0= 'double[]'
                    {
                    lv_type_12_0=(Token)match(input,25,FOLLOW_25_in_ruleAndroidAttribute1204); 

                            newLeafNode(lv_type_12_0, grammarAccess.getAndroidAttributeAccess().getTypeDoubleKeyword_4_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(current, "type", lv_type_12_0, "double[]");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:590:2: ( (lv_name_13_0= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:591:1: (lv_name_13_0= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:591:1: (lv_name_13_0= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:592:3: lv_name_13_0= RULE_ID
                    {
                    lv_name_13_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAndroidAttribute1234); 

                    			newLeafNode(lv_name_13_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_4_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_13_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_14=(Token)match(input,13,FOLLOW_13_in_ruleAndroidAttribute1251); 

                        	newLeafNode(otherlv_14, grammarAccess.getAndroidAttributeAccess().getSemicolonKeyword_4_2());
                        

                    }


                    }
                    break;
                case 6 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:613:6: ( ( (lv_type_15_0= 'date' ) ) ( (lv_name_16_0= RULE_ID ) ) otherlv_17= ';' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:613:6: ( ( (lv_type_15_0= 'date' ) ) ( (lv_name_16_0= RULE_ID ) ) otherlv_17= ';' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:613:7: ( (lv_type_15_0= 'date' ) ) ( (lv_name_16_0= RULE_ID ) ) otherlv_17= ';'
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:613:7: ( (lv_type_15_0= 'date' ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:614:1: (lv_type_15_0= 'date' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:614:1: (lv_type_15_0= 'date' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:615:3: lv_type_15_0= 'date'
                    {
                    lv_type_15_0=(Token)match(input,26,FOLLOW_26_in_ruleAndroidAttribute1277); 

                            newLeafNode(lv_type_15_0, grammarAccess.getAndroidAttributeAccess().getTypeDateKeyword_5_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(current, "type", lv_type_15_0, "date");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:628:2: ( (lv_name_16_0= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:629:1: (lv_name_16_0= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:629:1: (lv_name_16_0= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:630:3: lv_name_16_0= RULE_ID
                    {
                    lv_name_16_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAndroidAttribute1307); 

                    			newLeafNode(lv_name_16_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_5_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_16_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_17=(Token)match(input,13,FOLLOW_13_in_ruleAndroidAttribute1324); 

                        	newLeafNode(otherlv_17, grammarAccess.getAndroidAttributeAccess().getSemicolonKeyword_5_2());
                        

                    }


                    }
                    break;
                case 7 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:651:6: ( ( (lv_type_18_0= 'image' ) ) ( (lv_name_19_0= RULE_ID ) ) otherlv_20= ';' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:651:6: ( ( (lv_type_18_0= 'image' ) ) ( (lv_name_19_0= RULE_ID ) ) otherlv_20= ';' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:651:7: ( (lv_type_18_0= 'image' ) ) ( (lv_name_19_0= RULE_ID ) ) otherlv_20= ';'
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:651:7: ( (lv_type_18_0= 'image' ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:652:1: (lv_type_18_0= 'image' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:652:1: (lv_type_18_0= 'image' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:653:3: lv_type_18_0= 'image'
                    {
                    lv_type_18_0=(Token)match(input,27,FOLLOW_27_in_ruleAndroidAttribute1350); 

                            newLeafNode(lv_type_18_0, grammarAccess.getAndroidAttributeAccess().getTypeImageKeyword_6_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(current, "type", lv_type_18_0, "image");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:666:2: ( (lv_name_19_0= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:667:1: (lv_name_19_0= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:667:1: (lv_name_19_0= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:668:3: lv_name_19_0= RULE_ID
                    {
                    lv_name_19_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAndroidAttribute1380); 

                    			newLeafNode(lv_name_19_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_6_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_19_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_20=(Token)match(input,13,FOLLOW_13_in_ruleAndroidAttribute1397); 

                        	newLeafNode(otherlv_20, grammarAccess.getAndroidAttributeAccess().getSemicolonKeyword_6_2());
                        

                    }


                    }
                    break;
                case 8 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:689:6: ( ( (lv_type_21_0= 'link' ) ) ( (lv_name_22_0= RULE_ID ) ) ( (otherlv_23= RULE_ID ) ) otherlv_24= '{' ( (lv_linkproperties_25_0= ruleLinkProperties ) )+ otherlv_26= '}' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:689:6: ( ( (lv_type_21_0= 'link' ) ) ( (lv_name_22_0= RULE_ID ) ) ( (otherlv_23= RULE_ID ) ) otherlv_24= '{' ( (lv_linkproperties_25_0= ruleLinkProperties ) )+ otherlv_26= '}' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:689:7: ( (lv_type_21_0= 'link' ) ) ( (lv_name_22_0= RULE_ID ) ) ( (otherlv_23= RULE_ID ) ) otherlv_24= '{' ( (lv_linkproperties_25_0= ruleLinkProperties ) )+ otherlv_26= '}'
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:689:7: ( (lv_type_21_0= 'link' ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:690:1: (lv_type_21_0= 'link' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:690:1: (lv_type_21_0= 'link' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:691:3: lv_type_21_0= 'link'
                    {
                    lv_type_21_0=(Token)match(input,28,FOLLOW_28_in_ruleAndroidAttribute1423); 

                            newLeafNode(lv_type_21_0, grammarAccess.getAndroidAttributeAccess().getTypeLinkKeyword_7_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(current, "type", lv_type_21_0, "link");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:704:2: ( (lv_name_22_0= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:705:1: (lv_name_22_0= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:705:1: (lv_name_22_0= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:706:3: lv_name_22_0= RULE_ID
                    {
                    lv_name_22_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAndroidAttribute1453); 

                    			newLeafNode(lv_name_22_0, grammarAccess.getAndroidAttributeAccess().getNameIDTerminalRuleCall_7_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_22_0, 
                            		"ID");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:722:2: ( (otherlv_23= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:723:1: (otherlv_23= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:723:1: (otherlv_23= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:724:3: otherlv_23= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getAndroidAttributeRule());
                    	        }
                            
                    otherlv_23=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAndroidAttribute1478); 

                    		newLeafNode(otherlv_23, grammarAccess.getAndroidAttributeAccess().getObjectTypeAndroidEntityCrossReference_7_2_0()); 
                    	

                    }


                    }

                    otherlv_24=(Token)match(input,17,FOLLOW_17_in_ruleAndroidAttribute1490); 

                        	newLeafNode(otherlv_24, grammarAccess.getAndroidAttributeAccess().getLeftCurlyBracketKeyword_7_3());
                        
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:739:1: ( (lv_linkproperties_25_0= ruleLinkProperties ) )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0>=21 && LA9_0<=26)) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:740:1: (lv_linkproperties_25_0= ruleLinkProperties )
                    	    {
                    	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:740:1: (lv_linkproperties_25_0= ruleLinkProperties )
                    	    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:741:3: lv_linkproperties_25_0= ruleLinkProperties
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getAndroidAttributeAccess().getLinkpropertiesLinkPropertiesParserRuleCall_7_4_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleLinkProperties_in_ruleAndroidAttribute1511);
                    	    lv_linkproperties_25_0=ruleLinkProperties();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getAndroidAttributeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"linkproperties",
                    	            		lv_linkproperties_25_0, 
                    	            		"LinkProperties");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt9 >= 1 ) break loop9;
                                EarlyExitException eee =
                                    new EarlyExitException(9, input);
                                throw eee;
                        }
                        cnt9++;
                    } while (true);

                    otherlv_26=(Token)match(input,15,FOLLOW_15_in_ruleAndroidAttribute1524); 

                        	newLeafNode(otherlv_26, grammarAccess.getAndroidAttributeAccess().getRightCurlyBracketKeyword_7_5());
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAndroidAttribute"


    // $ANTLR start "entryRuleLinkProperties"
    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:769:1: entryRuleLinkProperties returns [EObject current=null] : iv_ruleLinkProperties= ruleLinkProperties EOF ;
    public final EObject entryRuleLinkProperties() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLinkProperties = null;


        try {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:770:2: (iv_ruleLinkProperties= ruleLinkProperties EOF )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:771:2: iv_ruleLinkProperties= ruleLinkProperties EOF
            {
             newCompositeNode(grammarAccess.getLinkPropertiesRule()); 
            pushFollow(FOLLOW_ruleLinkProperties_in_entryRuleLinkProperties1561);
            iv_ruleLinkProperties=ruleLinkProperties();

            state._fsp--;

             current =iv_ruleLinkProperties; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLinkProperties1571); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLinkProperties"


    // $ANTLR start "ruleLinkProperties"
    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:778:1: ruleLinkProperties returns [EObject current=null] : ( ( ( (lv_type_0_0= 'string' ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' ) | ( ( (lv_type_3_0= 'boolean' ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ';' ) | ( ( (lv_type_6_0= 'long' ) ) ( (lv_name_7_0= RULE_ID ) ) otherlv_8= ';' ) | ( ( (lv_type_9_0= 'double' ) ) ( (lv_name_10_0= RULE_ID ) ) otherlv_11= ';' ) | ( ( (lv_type_12_0= 'double[]' ) ) ( (lv_name_13_0= RULE_ID ) ) otherlv_14= ';' ) | ( ( (lv_type_15_0= 'date' ) ) ( (lv_name_16_0= RULE_ID ) ) otherlv_17= ';' ) ) ;
    public final EObject ruleLinkProperties() throws RecognitionException {
        EObject current = null;

        Token lv_type_0_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token lv_type_3_0=null;
        Token lv_name_4_0=null;
        Token otherlv_5=null;
        Token lv_type_6_0=null;
        Token lv_name_7_0=null;
        Token otherlv_8=null;
        Token lv_type_9_0=null;
        Token lv_name_10_0=null;
        Token otherlv_11=null;
        Token lv_type_12_0=null;
        Token lv_name_13_0=null;
        Token otherlv_14=null;
        Token lv_type_15_0=null;
        Token lv_name_16_0=null;
        Token otherlv_17=null;

         enterRule(); 
            
        try {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:781:28: ( ( ( ( (lv_type_0_0= 'string' ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' ) | ( ( (lv_type_3_0= 'boolean' ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ';' ) | ( ( (lv_type_6_0= 'long' ) ) ( (lv_name_7_0= RULE_ID ) ) otherlv_8= ';' ) | ( ( (lv_type_9_0= 'double' ) ) ( (lv_name_10_0= RULE_ID ) ) otherlv_11= ';' ) | ( ( (lv_type_12_0= 'double[]' ) ) ( (lv_name_13_0= RULE_ID ) ) otherlv_14= ';' ) | ( ( (lv_type_15_0= 'date' ) ) ( (lv_name_16_0= RULE_ID ) ) otherlv_17= ';' ) ) )
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:782:1: ( ( ( (lv_type_0_0= 'string' ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' ) | ( ( (lv_type_3_0= 'boolean' ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ';' ) | ( ( (lv_type_6_0= 'long' ) ) ( (lv_name_7_0= RULE_ID ) ) otherlv_8= ';' ) | ( ( (lv_type_9_0= 'double' ) ) ( (lv_name_10_0= RULE_ID ) ) otherlv_11= ';' ) | ( ( (lv_type_12_0= 'double[]' ) ) ( (lv_name_13_0= RULE_ID ) ) otherlv_14= ';' ) | ( ( (lv_type_15_0= 'date' ) ) ( (lv_name_16_0= RULE_ID ) ) otherlv_17= ';' ) )
            {
            // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:782:1: ( ( ( (lv_type_0_0= 'string' ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' ) | ( ( (lv_type_3_0= 'boolean' ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ';' ) | ( ( (lv_type_6_0= 'long' ) ) ( (lv_name_7_0= RULE_ID ) ) otherlv_8= ';' ) | ( ( (lv_type_9_0= 'double' ) ) ( (lv_name_10_0= RULE_ID ) ) otherlv_11= ';' ) | ( ( (lv_type_12_0= 'double[]' ) ) ( (lv_name_13_0= RULE_ID ) ) otherlv_14= ';' ) | ( ( (lv_type_15_0= 'date' ) ) ( (lv_name_16_0= RULE_ID ) ) otherlv_17= ';' ) )
            int alt11=6;
            switch ( input.LA(1) ) {
            case 21:
                {
                alt11=1;
                }
                break;
            case 22:
                {
                alt11=2;
                }
                break;
            case 23:
                {
                alt11=3;
                }
                break;
            case 24:
                {
                alt11=4;
                }
                break;
            case 25:
                {
                alt11=5;
                }
                break;
            case 26:
                {
                alt11=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:782:2: ( ( (lv_type_0_0= 'string' ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:782:2: ( ( (lv_type_0_0= 'string' ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:782:3: ( (lv_type_0_0= 'string' ) ) ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ';'
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:782:3: ( (lv_type_0_0= 'string' ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:783:1: (lv_type_0_0= 'string' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:783:1: (lv_type_0_0= 'string' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:784:3: lv_type_0_0= 'string'
                    {
                    lv_type_0_0=(Token)match(input,21,FOLLOW_21_in_ruleLinkProperties1615); 

                            newLeafNode(lv_type_0_0, grammarAccess.getLinkPropertiesAccess().getTypeStringKeyword_0_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLinkPropertiesRule());
                    	        }
                           		setWithLastConsumed(current, "type", lv_type_0_0, "string");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:797:2: ( (lv_name_1_0= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:798:1: (lv_name_1_0= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:798:1: (lv_name_1_0= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:799:3: lv_name_1_0= RULE_ID
                    {
                    lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLinkProperties1645); 

                    			newLeafNode(lv_name_1_0, grammarAccess.getLinkPropertiesAccess().getNameIDTerminalRuleCall_0_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLinkPropertiesRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_1_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_2=(Token)match(input,13,FOLLOW_13_in_ruleLinkProperties1662); 

                        	newLeafNode(otherlv_2, grammarAccess.getLinkPropertiesAccess().getSemicolonKeyword_0_2());
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:820:6: ( ( (lv_type_3_0= 'boolean' ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ';' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:820:6: ( ( (lv_type_3_0= 'boolean' ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ';' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:820:7: ( (lv_type_3_0= 'boolean' ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ';'
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:820:7: ( (lv_type_3_0= 'boolean' ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:821:1: (lv_type_3_0= 'boolean' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:821:1: (lv_type_3_0= 'boolean' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:822:3: lv_type_3_0= 'boolean'
                    {
                    lv_type_3_0=(Token)match(input,22,FOLLOW_22_in_ruleLinkProperties1688); 

                            newLeafNode(lv_type_3_0, grammarAccess.getLinkPropertiesAccess().getTypeBooleanKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLinkPropertiesRule());
                    	        }
                           		setWithLastConsumed(current, "type", lv_type_3_0, "boolean");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:835:2: ( (lv_name_4_0= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:836:1: (lv_name_4_0= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:836:1: (lv_name_4_0= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:837:3: lv_name_4_0= RULE_ID
                    {
                    lv_name_4_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLinkProperties1718); 

                    			newLeafNode(lv_name_4_0, grammarAccess.getLinkPropertiesAccess().getNameIDTerminalRuleCall_1_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLinkPropertiesRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_4_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleLinkProperties1735); 

                        	newLeafNode(otherlv_5, grammarAccess.getLinkPropertiesAccess().getSemicolonKeyword_1_2());
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:858:6: ( ( (lv_type_6_0= 'long' ) ) ( (lv_name_7_0= RULE_ID ) ) otherlv_8= ';' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:858:6: ( ( (lv_type_6_0= 'long' ) ) ( (lv_name_7_0= RULE_ID ) ) otherlv_8= ';' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:858:7: ( (lv_type_6_0= 'long' ) ) ( (lv_name_7_0= RULE_ID ) ) otherlv_8= ';'
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:858:7: ( (lv_type_6_0= 'long' ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:859:1: (lv_type_6_0= 'long' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:859:1: (lv_type_6_0= 'long' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:860:3: lv_type_6_0= 'long'
                    {
                    lv_type_6_0=(Token)match(input,23,FOLLOW_23_in_ruleLinkProperties1761); 

                            newLeafNode(lv_type_6_0, grammarAccess.getLinkPropertiesAccess().getTypeLongKeyword_2_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLinkPropertiesRule());
                    	        }
                           		setWithLastConsumed(current, "type", lv_type_6_0, "long");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:873:2: ( (lv_name_7_0= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:874:1: (lv_name_7_0= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:874:1: (lv_name_7_0= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:875:3: lv_name_7_0= RULE_ID
                    {
                    lv_name_7_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLinkProperties1791); 

                    			newLeafNode(lv_name_7_0, grammarAccess.getLinkPropertiesAccess().getNameIDTerminalRuleCall_2_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLinkPropertiesRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_7_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_8=(Token)match(input,13,FOLLOW_13_in_ruleLinkProperties1808); 

                        	newLeafNode(otherlv_8, grammarAccess.getLinkPropertiesAccess().getSemicolonKeyword_2_2());
                        

                    }


                    }
                    break;
                case 4 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:896:6: ( ( (lv_type_9_0= 'double' ) ) ( (lv_name_10_0= RULE_ID ) ) otherlv_11= ';' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:896:6: ( ( (lv_type_9_0= 'double' ) ) ( (lv_name_10_0= RULE_ID ) ) otherlv_11= ';' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:896:7: ( (lv_type_9_0= 'double' ) ) ( (lv_name_10_0= RULE_ID ) ) otherlv_11= ';'
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:896:7: ( (lv_type_9_0= 'double' ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:897:1: (lv_type_9_0= 'double' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:897:1: (lv_type_9_0= 'double' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:898:3: lv_type_9_0= 'double'
                    {
                    lv_type_9_0=(Token)match(input,24,FOLLOW_24_in_ruleLinkProperties1834); 

                            newLeafNode(lv_type_9_0, grammarAccess.getLinkPropertiesAccess().getTypeDoubleKeyword_3_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLinkPropertiesRule());
                    	        }
                           		setWithLastConsumed(current, "type", lv_type_9_0, "double");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:911:2: ( (lv_name_10_0= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:912:1: (lv_name_10_0= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:912:1: (lv_name_10_0= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:913:3: lv_name_10_0= RULE_ID
                    {
                    lv_name_10_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLinkProperties1864); 

                    			newLeafNode(lv_name_10_0, grammarAccess.getLinkPropertiesAccess().getNameIDTerminalRuleCall_3_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLinkPropertiesRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_10_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_11=(Token)match(input,13,FOLLOW_13_in_ruleLinkProperties1881); 

                        	newLeafNode(otherlv_11, grammarAccess.getLinkPropertiesAccess().getSemicolonKeyword_3_2());
                        

                    }


                    }
                    break;
                case 5 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:934:6: ( ( (lv_type_12_0= 'double[]' ) ) ( (lv_name_13_0= RULE_ID ) ) otherlv_14= ';' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:934:6: ( ( (lv_type_12_0= 'double[]' ) ) ( (lv_name_13_0= RULE_ID ) ) otherlv_14= ';' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:934:7: ( (lv_type_12_0= 'double[]' ) ) ( (lv_name_13_0= RULE_ID ) ) otherlv_14= ';'
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:934:7: ( (lv_type_12_0= 'double[]' ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:935:1: (lv_type_12_0= 'double[]' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:935:1: (lv_type_12_0= 'double[]' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:936:3: lv_type_12_0= 'double[]'
                    {
                    lv_type_12_0=(Token)match(input,25,FOLLOW_25_in_ruleLinkProperties1907); 

                            newLeafNode(lv_type_12_0, grammarAccess.getLinkPropertiesAccess().getTypeDoubleKeyword_4_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLinkPropertiesRule());
                    	        }
                           		setWithLastConsumed(current, "type", lv_type_12_0, "double[]");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:949:2: ( (lv_name_13_0= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:950:1: (lv_name_13_0= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:950:1: (lv_name_13_0= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:951:3: lv_name_13_0= RULE_ID
                    {
                    lv_name_13_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLinkProperties1937); 

                    			newLeafNode(lv_name_13_0, grammarAccess.getLinkPropertiesAccess().getNameIDTerminalRuleCall_4_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLinkPropertiesRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_13_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_14=(Token)match(input,13,FOLLOW_13_in_ruleLinkProperties1954); 

                        	newLeafNode(otherlv_14, grammarAccess.getLinkPropertiesAccess().getSemicolonKeyword_4_2());
                        

                    }


                    }
                    break;
                case 6 :
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:972:6: ( ( (lv_type_15_0= 'date' ) ) ( (lv_name_16_0= RULE_ID ) ) otherlv_17= ';' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:972:6: ( ( (lv_type_15_0= 'date' ) ) ( (lv_name_16_0= RULE_ID ) ) otherlv_17= ';' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:972:7: ( (lv_type_15_0= 'date' ) ) ( (lv_name_16_0= RULE_ID ) ) otherlv_17= ';'
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:972:7: ( (lv_type_15_0= 'date' ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:973:1: (lv_type_15_0= 'date' )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:973:1: (lv_type_15_0= 'date' )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:974:3: lv_type_15_0= 'date'
                    {
                    lv_type_15_0=(Token)match(input,26,FOLLOW_26_in_ruleLinkProperties1980); 

                            newLeafNode(lv_type_15_0, grammarAccess.getLinkPropertiesAccess().getTypeDateKeyword_5_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLinkPropertiesRule());
                    	        }
                           		setWithLastConsumed(current, "type", lv_type_15_0, "date");
                    	    

                    }


                    }

                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:987:2: ( (lv_name_16_0= RULE_ID ) )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:988:1: (lv_name_16_0= RULE_ID )
                    {
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:988:1: (lv_name_16_0= RULE_ID )
                    // ../org.xtext.orcasdk.entitymodel/src-gen/org/xtext/orcasdk/entitymodel/parser/antlr/internal/InternalEntityModel.g:989:3: lv_name_16_0= RULE_ID
                    {
                    lv_name_16_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLinkProperties2010); 

                    			newLeafNode(lv_name_16_0, grammarAccess.getLinkPropertiesAccess().getNameIDTerminalRuleCall_5_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLinkPropertiesRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_16_0, 
                            		"ID");
                    	    

                    }


                    }

                    otherlv_17=(Token)match(input,13,FOLLOW_13_in_ruleLinkProperties2027); 

                        	newLeafNode(otherlv_17, grammarAccess.getLinkPropertiesAccess().getSemicolonKeyword_5_2());
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLinkProperties"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAppConstants_in_ruleModel130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAppConstants_in_entryRuleAppConstants166 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAppConstants176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleAppConstants213 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleAppConstants225 = new BitSet(new long[]{0x0000000000002010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleAppConstants242 = new BitSet(new long[]{0x0000000000002010L});
    public static final BitSet FOLLOW_13_in_ruleAppConstants260 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleAppConstants272 = new BitSet(new long[]{0x0000000000002010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleAppConstants289 = new BitSet(new long[]{0x0000000000002010L});
    public static final BitSet FOLLOW_13_in_ruleAppConstants307 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleAppConstants319 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_rulePackages_in_ruleAppConstants340 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_rulePackages_in_entryRulePackages377 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePackages387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rulePackages424 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_rulePackages445 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_rulePackages457 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleAndroidEntity_in_rulePackages478 = new BitSet(new long[]{0x0000000000088000L});
    public static final BitSet FOLLOW_15_in_rulePackages491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedName_in_entryRuleQualifiedName528 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedName539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedName579 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_ruleQualifiedName598 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedName613 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ruleAndroidEntity_in_entryRuleAndroidEntity660 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndroidEntity670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleAndroidEntity707 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAndroidEntity724 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleAndroidEntity741 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleAndroidEntity753 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleAndroidEntity770 = new BitSet(new long[]{0x0000000000002040L});
    public static final BitSet FOLLOW_13_in_ruleAndroidEntity788 = new BitSet(new long[]{0x000000001FE00000L});
    public static final BitSet FOLLOW_ruleAndroidAttribute_in_ruleAndroidEntity809 = new BitSet(new long[]{0x000000001FE08000L});
    public static final BitSet FOLLOW_15_in_ruleAndroidEntity822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndroidAttribute_in_entryRuleAndroidAttribute858 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndroidAttribute868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleAndroidAttribute912 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAndroidAttribute942 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAndroidAttribute959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleAndroidAttribute985 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAndroidAttribute1015 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAndroidAttribute1032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleAndroidAttribute1058 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAndroidAttribute1088 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAndroidAttribute1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleAndroidAttribute1131 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAndroidAttribute1161 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAndroidAttribute1178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleAndroidAttribute1204 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAndroidAttribute1234 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAndroidAttribute1251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleAndroidAttribute1277 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAndroidAttribute1307 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAndroidAttribute1324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleAndroidAttribute1350 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAndroidAttribute1380 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAndroidAttribute1397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleAndroidAttribute1423 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAndroidAttribute1453 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAndroidAttribute1478 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleAndroidAttribute1490 = new BitSet(new long[]{0x0000000007E00000L});
    public static final BitSet FOLLOW_ruleLinkProperties_in_ruleAndroidAttribute1511 = new BitSet(new long[]{0x0000000007E08000L});
    public static final BitSet FOLLOW_15_in_ruleAndroidAttribute1524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLinkProperties_in_entryRuleLinkProperties1561 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLinkProperties1571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleLinkProperties1615 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLinkProperties1645 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleLinkProperties1662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleLinkProperties1688 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLinkProperties1718 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleLinkProperties1735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleLinkProperties1761 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLinkProperties1791 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleLinkProperties1808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleLinkProperties1834 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLinkProperties1864 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleLinkProperties1881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleLinkProperties1907 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLinkProperties1937 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleLinkProperties1954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleLinkProperties1980 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLinkProperties2010 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleLinkProperties2027 = new BitSet(new long[]{0x0000000000000002L});

}
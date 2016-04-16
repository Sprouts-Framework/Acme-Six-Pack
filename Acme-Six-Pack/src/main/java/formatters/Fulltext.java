//package formatters;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
//import org.apache.lucene.analysis.miscellaneous.TruncateTokenFilterFactory;
//import org.apache.lucene.analysis.phonetic.PhoneticFilterFactory;
//import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
//import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
//import org.hibernate.search.annotations.AnalyzerDef;
//import org.hibernate.search.annotations.Indexed;
//import org.hibernate.search.annotations.Parameter;
//import org.hibernate.search.annotations.TokenFilterDef;
//import org.hibernate.search.annotations.TokenizerDef;
//
//@Indexed
//@AnalyzerDef(name = "customanalyzer",
//tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
//filters = {
//  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
//  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
//    @Parameter(name = "language", value = "English")
//  }),
//  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
//	    @Parameter(name = "language", value = "Spanish")
//  }),
//  @TokenFilterDef(factory = TruncateTokenFilterFactory.class, params = {
//	  	@Parameter(name = "prefixLength", value = "3")
//  }),
//  @TokenFilterDef(factory = PhoneticFilterFactory.class, params = {
//	  	@Parameter(name = "encoder", value = "DoubleMetaphone"),
//	  	@Parameter(name = "inject", value = "false"),
//	  	@Parameter(name = "maxCodeLength", value = "4")
//  })
//}
//)
//@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.TYPE})
//public @interface Fulltext {
//
//}
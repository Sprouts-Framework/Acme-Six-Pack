//package formatters;
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.format.AnnotationFormatterFactory;
//import org.springframework.format.Parser;
//import org.springframework.format.Printer;
//
//
//public class CustomCurrencyAnnotationFormatterFactory implements AnnotationFormatterFactory<CurrencyFormatter>{
//
//	@Override
//	public Set<Class<?>> getFieldTypes() {
//		return new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { 
//	            Short.class, Integer.class, Long.class, Float.class, Double.class, BigDecimal.class, BigInteger.class }));
//	}
//
//	@Override
//	public Printer<?> getPrinter(CurrencyFormatter annotation, Class<?> fieldType) {
//		return configurateFormatterFrom(annotation, fieldType);
//	}
//
//	@Override
//	public Parser<?> getParser(CurrencyFormatter annotation, Class<?> fieldType) {
//		return configurateFormatterFrom(annotation, fieldType);
//	}
//	
//	private CustomCurrencyFormatter configurateFormatterFrom(CurrencyFormatter annotation, Class<?> fieldType){
//		CustomCurrencyFormatter result;
//		result = new CustomCurrencyFormatter();
//		result.setCurrencyName(annotation.currency());
//		return result;
//	}
//
//}

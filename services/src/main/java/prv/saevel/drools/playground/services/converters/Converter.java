package prv.saevel.drools.playground.services.converters;

public interface Converter<X, Y> {
    
    Y convert(X x);
    
    X reverseConvert(Y y);
}

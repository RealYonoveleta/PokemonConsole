package annotation.scan;

import java.lang.annotation.Annotation;

public interface AnnotationScanner<T extends Annotation> {
	
	void scan();
	
}

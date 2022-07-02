package chapter_5_generics;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ConsiderTypesafeHeterogeneousContainers_33 {
}

class Favorites {

    private final Map<Class<?>, Object> favs = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance){
        favs.put(Objects.requireNonNull(type), type.cast(instance));
    }
    public <T> T getFavorite(Class<T> type) {
        return type.cast(favs.get(type));  // Dynamic cast
    }

    public static void main(String[] args) {
        Favorites f= new Favorites();
        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);

        String fString = f.getFavorite(String.class);
        int fInteger = f.getFavorite(Integer.class);
        Class<?> fClass = f.getFavorite(Class.class);
        System.out.printf("%s %x %s%n", fString, fInteger, fClass.getName());
    }

    /**
     * See
     * Collections.checkedSet
     * Collections.checkedList etc.
     *
     *
     * bounded type token - a type token that places a bound on what type can
     * be represented using a bounded type parameter or a bounded wildcard
     */
    public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        return null;
    }

    // Use of asSubclass to safely cast to a bounded type token
    static Annotation getAnnotation(AnnotatedElement element, String annotationTypeName) {
        Class<?> annotationType = null; // Unbounded type token
        try {
            annotationType = Class.forName(annotationTypeName);
        } catch (Exception ex){
            throw new IllegalArgumentException(ex);
        }
        return element.getAnnotation(annotationType.asSubclass(Annotation.class));
     }

}

/**
 * you can get around fixed number of type parameters per container by
 * placing the type parameter on the key rather than the container
 * - you can use Class objects as keys for such typesafe heterogeneous containers.
 * A Class object used in this fashion is called a TYPE TOKEN.
 * you can also use a custom key type.
 * For example, you could have a DatabaseRow type representing a database row
 * (the container) and a generic type Column<T> as its key.
 */

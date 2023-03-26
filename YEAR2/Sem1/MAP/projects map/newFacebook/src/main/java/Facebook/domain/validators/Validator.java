package Facebook.domain.validators;

import Facebook.exceptions.ValidationException;

/**
 *
 * @param <T>
 */
public interface Validator<T> {
    /**
     *
     * @param entity
     * @throws ValidationException
     */
    void validate(T entity) throws ValidationException;
}

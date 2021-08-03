package br.com.zupacademy.enricco.proposta.validations;

import br.com.zupacademy.enricco.proposta.utils.crypto.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;


public class UniqueValueValidator implements ConstraintValidator<UniqueValue,Object> {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Crypto crypto;

    private Class<?> domainName;
    private String fieldName;
    private Boolean encryptor;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
        this.domainName = constraintAnnotation.domainClass();
        this.encryptor = constraintAnnotation.encryptor();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        List<?> list;
        if(encryptor){
            Query query = entityManager.createQuery("SELECT x."+fieldName+" FROM " + domainName.getName()+ " x");
            list = query.getResultList();
            try {
                list = list.stream().filter(object->crypto.decode((String) object).equals(o.toString())).collect(Collectors.toList());
            }catch (Exception e){
                return false;
            }

        }else {
            Query query = entityManager.createQuery("SELECT 1 FROM " + domainName.getName()+" x WHERE x."+fieldName+"=:value");
            query.setParameter("value",o.toString());
            list = query.getResultList();
        }


        if (!list.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, constraintValidatorContext.getDefaultConstraintMessageTemplate());
        }
        return list.isEmpty();
    }
}

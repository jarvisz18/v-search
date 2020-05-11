package cn.ixan.search.java8;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.function.Supplier;

public class Java8SupplierTest {

    @Test
    public void testSupplierNew(){
        Supplier<Person> supplier = Person::new;
        Person person = supplier.get();
        Assert.assertTrue(Objects.nonNull(person));
    }

}

/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/domain/EntityMeta_.java.e.vm
 */
package com.mycompany.myapp.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Book.class)
public abstract class Book_ {

    // Raw attributes
    public static volatile SingularAttribute<Book, Integer> id;
    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, String> summary;
    public static volatile SingularAttribute<Book, byte[]> extractBinary;
    public static volatile SingularAttribute<Book, String> extractFileName;
    public static volatile SingularAttribute<Book, String> extractContentType;
    public static volatile SingularAttribute<Book, Long> extractSize;
    public static volatile SingularAttribute<Book, LocalDate> publicationDate;
    public static volatile SingularAttribute<Book, Boolean> bestSeller;
    public static volatile SingularAttribute<Book, BigDecimal> price;

    // Many to one
    public static volatile SingularAttribute<Book, Author> author;
}
/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/dto/EntityDTOService.java.e.vm
 */
package com.mycompany.myapp.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.myapp.domain.Author;
import com.mycompany.myapp.dto.support.PageRequestByExample;
import com.mycompany.myapp.dto.support.PageResponse;
import com.mycompany.myapp.repository.AuthorRepository;

/**
 * A simple DTO Facility for Author.
 */
@Service
public class AuthorDTOService {

    @Inject
    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public AuthorDTO findOne(Integer id) {
        return toDTO(authorRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<AuthorDTO> complete(String query, int maxResults) {
        List<Author> results = authorRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<AuthorDTO> findAll(PageRequestByExample<AuthorDTO> req) {
        Example<Author> example = null;
        Author author = toEntity(req.example);

        if (author != null) {
            example = Example.of(author);
        }

        Page<Author> page;
        if (example != null) {
            page = authorRepository.findAll(example, req.toPageable());
        } else {
            page = authorRepository.findAll(req.toPageable());
        }

        List<AuthorDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public AuthorDTO save(AuthorDTO dto) {
        if (dto == null) {
            return null;
        }

        Author author;
        if (dto.isIdSet()) {
            author = authorRepository.findOne(dto.id);
        } else {
            author = new Author();
        }

        author.setCivility(dto.civility);
        author.setFirstName(dto.firstName);
        author.setLastName(dto.lastName);
        author.setEmail(dto.email);
        author.setBirthDate(dto.birthDate);
        author.setBirthDateTime(dto.birthDateTime);

        if (dto.favoriteAuthor == null) {
            author.setFavoriteAuthor(null);
        } else {
            Author favoriteAuthor = author.getFavoriteAuthor();
            if (favoriteAuthor == null || (favoriteAuthor.getId().compareTo(dto.favoriteAuthor.id) != 0)) {
                author.setFavoriteAuthor(authorRepository.findOne(dto.favoriteAuthor.id));
            }
        }

        return toDTO(authorRepository.save(author));
    }

    /**
     * Converts the passed author to a DTO.
     */
    public AuthorDTO toDTO(Author author) {
        return toDTO(author, 1);
    }

    /**
     * Converts the passed author to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param author
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public AuthorDTO toDTO(Author author, int depth) {
        if (author == null) {
            return null;
        }

        AuthorDTO dto = new AuthorDTO();

        dto.id = author.getId();
        dto.civility = author.getCivility();
        dto.firstName = author.getFirstName();
        dto.lastName = author.getLastName();
        dto.email = author.getEmail();
        dto.birthDate = author.getBirthDate();
        dto.birthDateTime = author.getBirthDateTime();
        if (depth-- > 0) {
            dto.favoriteAuthor = toDTO(author.getFavoriteAuthor(), depth);
        }

        return dto;
    }

    /**
     * Converts the passed dto to a Author.
     * Convenient for query by example.
     */
    public Author toEntity(AuthorDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a Author.
     * Convenient for query by example.
     */
    public Author toEntity(AuthorDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        Author author = new Author();

        author.setId(dto.id);
        author.setCivility(dto.civility);
        author.setFirstName(dto.firstName);
        author.setLastName(dto.lastName);
        author.setEmail(dto.email);
        author.setBirthDate(dto.birthDate);
        author.setBirthDateTime(dto.birthDateTime);
        if (depth-- > 0) {
            author.setFavoriteAuthor(toEntity(dto.favoriteAuthor, depth));
        }

        return author;
    }
}
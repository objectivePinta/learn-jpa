package com.learn.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "commenter")
@EqualsAndHashCode(exclude = {"comments"}, callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Commenter extends AbstractEntity<Long> {


    private String username;
    private String password;


    //@OneToMany and @ManyToMany associations use the FetchType.LAZY by default
    @OneToMany(mappedBy = "commenter")
    private List<Comment> comments;

}


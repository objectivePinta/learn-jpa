package com.learn.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comment")
@EqualsAndHashCode(exclude = {"commenter"}, callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(name = "commentOfPostByCommenter",
                attributeNodes = {
                        @NamedAttributeNode("post"),
                        @NamedAttributeNode("commenter")}
        ),
        @NamedEntityGraph(name = "commentOfPostAndAuthors",
                attributeNodes = @NamedAttributeNode(value = "post",
                        subgraph = "post.authors"),
                subgraphs = @NamedSubgraph(
                        name = "post.authors",
                        attributeNodes = @NamedAttributeNode("authors")
                ))}
)
public class Comment extends AbstractEntity<Long> {


    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "commenter_id")
    //@OneToMany and @ManyToMany associations use the FetchType.LAZY by default
    //@ManyToOne and @OneToOne -> Eager
    private Commenter commenter;

}


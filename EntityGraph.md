By default, @ManyToOne and @OneToOne associations use the FetchTyp.EAGER strategy, which is a
terrible choice from a performance perspective. So, for this reason, it’s good practice to set all
@ManyToOne and @OneToOne associations to use the FetchType.LAZY strategy, like in the following
example:

```
@Entity(name = "PostComment")
@Table(name = "post_comment")
public class PostComment {
 
    @Id
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
 
    private String review;
     
    //Getters and setters omitted for brevity
}

//Query Language (JPQL), Criteria API, Native Queries, Callbacks and Listeners
```
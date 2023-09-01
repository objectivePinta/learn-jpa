package com.learn.jpa.relationship.model;

import com.learn.jpa.model.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

// Corresponding to Profile table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true, exclude = "appUser")
@ToString(exclude = "appUser", callSuper = true)
public class Profile extends AbstractEntity<Long> {

    private String bio;

    @OneToOne
    @JoinColumn(name = "appuser_id", referencedColumnName = "id")
    private AppUser appUser;

}

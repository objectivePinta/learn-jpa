package com.learn.jpa.relationship.model;

import com.learn.jpa.model.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "appuser")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = "profile")
public class AppUser extends AbstractEntity<Long> {

    private String username;

    private String password;

    /*
    Cascading only makes sense only for Parent – Child associations (the Parent entity state transition being cascaded to its Child entities).
     Cascading from Child to Parent is not very useful and usually, it’s a mapping code smell.
     */
    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    public void addProfile(Profile profile) {
        this.profile = profile;
        profile.setAppUser(this);
    }

    public void removeProfile(Profile profile) {
        if (profile != null) {
            profile.setAppUser(null);
        }
        this.profile = null;
    }
}


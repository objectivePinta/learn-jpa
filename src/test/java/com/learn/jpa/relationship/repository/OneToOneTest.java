package com.learn.jpa.relationship.repository;

import com.learn.jpa.relationship.model.AppUser;
import com.learn.jpa.relationship.model.Profile;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("local")
@Slf4j
class OneToOneTest {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    AppUserRepository appUserRepository;


    @Test
    void getSomeData() {
        profileRepository.findAll().forEach(it ->
                log.info(it + " " + it.getAppUser()));
        appUserRepository.findAll().forEach(it ->
                log.info(it + " " + it.getProfile()));
    }

    @Test
        //no cascading set
        //it deletes only the profile
    void deleteAProfile() {
        profileRepository.findById(1L).ifPresent(profile -> {
            profileRepository.delete(profile);
        });
    }

    @Test
        //no cascading set:ERROR: update or delete on table "appuser" violates foreign key constraint "profile_appuser_id_fkey"
        // on table "profile" Detail: Key (id)=(2) is still referenced from table "profile".
        // Call getNextException to see other errors in the batch.], SQL: delete from appuser where id=?
    void deleteAnAppUser() {
        /*
        The CascadeType.REMOVE is also inherited from the CascadeType.ALL configuration,
         so the Post entity deletion triggers a PostDetails entity removal too:
         */
        appUserRepository.findById(2L).ifPresent(appUser -> {
            appUserRepository.delete(appUser);
        });
    }

    @Test
    void createAppUser() {
        Profile profile = Profile.builder()
                .bio("abcxyz").build();
        AppUser appUser = AppUser.builder().username("aaaabc").password("123555").build();
        // without this profile row will not have an appuser id
        appUser.addProfile(profile);
        /*
        The CascadeType.PERSIST comes along with the CascadeType.ALL configuration, so we only have to persist the Post entity,
         and the associated PostDetails entity is persisted as well:
         */
        AppUser persisted = appUserRepository.save(appUser);
        log.info(persisted.toString());
        log.info(persisted.getProfile().toString());

        persisted.setUsername("abcde");
        persisted.getProfile().setBio("O la la");
        AppUser persisted2 = appUserRepository.save(persisted);
        log.info(persisted2.toString());
        log.info(persisted2.getProfile().toString());
    }

    @Test
    void mergeCascadeType() {
        AppUser appUser = createAnAppUser();
        appUser.setUsername("Something Different");
        appUser.getProfile().setBio("A bad dog");
        /*
        The CascadeType.MERGE is inherited from the CascadeType.ALL
    setting, so we only have to merge the AppUser entity and the associated Profile is merged as well:
    Hibernate:
    select
        a1_0.id,
        a1_0.password,
        p1_0.id,
        p1_0.bio,
        a1_0.username
    from
        appuser a1_0
    left join
        profile p1_0
            on a1_0.id=p1_0.appuser_id
    where
        a1_0.id=?
Hibernate:
    update
        profile
    set
        appuser_id=?,
        bio=?
    where
        id=?
Hibernate:
    update
        appuser
    set
        password=?,
        username=?
    where
        id=?
    */
        appUserRepository.save(appUser);
    }

    @Transactional
    AppUser createAnAppUser() {
        Profile profile = Profile.builder()
                .bio("a lovely dog").build();
        AppUser appUser = AppUser.builder().username("dog").password("doggy").build();
        // without this profile row will not have an appuser id
        appUser.addProfile(profile);
        return appUserRepository.save(appUser);
    }

    /*The one-to-one delete orphan cascading operation */
    @Test
    void removeProfileWithoutTheAppUser() {
        AppUser appUser = createAnAppUser();
        /*
        Hibernate:
    select
        a1_0.id,
        a1_0.password,
        p1_0.id,
        p1_0.bio,
        a1_0.username
    from
        appuser a1_0
    left join
        profile p1_0
            on a1_0.id=p1_0.appuser_id
    where
        a1_0.id=?
Hibernate:
    delete
    from
        profile
    where
        id=?
         */
        appUser.removeProfile(appUser.getProfile());
        appUserRepository.save(appUser);
    }


}
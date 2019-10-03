package de.mrrobworks.springbootangular.backend.appuser;

import de.mrrobworks.springbootangular.backend.approle.AppRoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = AppUserServiceImplTest.TConf.class)
@ExtendWith({MockitoExtension.class, SpringExtension.class})
@RunWith(SpringRunner.class)
class AppUserServiceImplTest {

  private AppUserServiceImpl cut;

  @Autowired private AppUserMapper appUserMapper;
  @Mock private AppRoleService appRoleService;
  @Mock private AppUserRepository appUserRepository;

  @TestConfiguration
  static class TConf {

    @Bean
    public AppUserMapper getAppUserMapper() {
      return Mappers.getMapper(AppUserMapper.class);
    }
  }

  @BeforeEach
  void setUp() {
    cut = new AppUserServiceImpl(appRoleService, appUserRepository, appUserMapper);
    appUserMapper = Mappers.getMapper(AppUserMapper.class);
  }

  @Test
  void getAllAppUsers() {
    when(appUserRepository.findAll())
        .thenReturn(List.of(AppUser.builder().id("1234567890").build()));

    List<AppUserDto> allAppUsers = cut.getAllAppUsers();
    assertEquals(1, allAppUsers.size());
    assertEquals("1234567890", allAppUsers.iterator().next().getId());
  }

  @Test
  void updateAppUser() {
    AppUser appUser = AppUser.builder().build();
    when(appUserRepository.findByIdGet(any())).thenReturn(appUser);
    when(appUserRepository.save(appUser)).thenReturn(AppUser.builder().id("1234567890").build());

    AppUserDto appUserDto = cut.updateAppUser(AppUserDto.builder().roles(anyList()).build());
    assertEquals("1234567890", appUserDto.getId());
  }
}

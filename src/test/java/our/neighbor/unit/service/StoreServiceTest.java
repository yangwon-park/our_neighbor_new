package our.neighbor.unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import our.neighbor.app.domain.store.Store;
import our.neighbor.app.domain.store.dto.StoreDTO;
import our.neighbor.app.repository.store.StoreRepository;
import our.neighbor.app.service.store.StoreService;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static our.neighbor.app.domain.store.dto.StoreSearchRequest.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {

    @Mock
    StoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;

    private StoreDTO.Save saveRequest;

    @BeforeEach
    void setUp() {
        saveRequest = StoreDTO.Save
                .builder()
                .name("새로운 카페")
                .zipcode("12345")
                .roadAddr("우리집")
                .numberAddr("아파트")
                .detail("좋아요")
                .lat(123.456)
                .lon(35.678)
                .phoneNumber("0123456789")
                .openingTime(LocalTime.now())
                .closingTime(LocalTime.now())
                .build();
    }

    @Test
    @DisplayName("매장 저장")
    void should_SaveNewStore_ThenReturnId() {
        Store savedStore = saveRequest.toEntity();

        given(storeRepository.save(any(Store.class))).willAnswer(invocation -> invocation.getArgument(0));

        Long id = storeService.save(saveRequest);

        ArgumentCaptor<Store> argumentCaptor = ArgumentCaptor.forClass(Store.class);

        then(storeRepository).should(times(1)).save(argumentCaptor.capture());

        assertThat(id).isEqualTo(savedStore.getId());
    }
}

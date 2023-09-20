package our.neighbor.app.controller.api.store;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import our.neighbor.app.domain.store.dto.StoreDTO;
import our.neighbor.app.service.store.StoreService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/owner/store")
public class StoreOwnerApiController {

    private final StoreService storeService;

    @PostMapping
    public Long save(@Validated @RequestBody StoreDTO.Save request) {
        return storeService.save(request);
    }
}

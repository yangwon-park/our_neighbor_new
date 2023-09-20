package our.neighbor.app.controller.api.store;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import our.neighbor.app.service.store.StoreService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
public class StoreApiController {

    private StoreService storeService;

    @GetMapping("/{storeId}")
    public void getStoreDetailByStoreId(@PathVariable Long storeId) {

    }
}

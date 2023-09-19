package our.neighbor.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import our.neighbor.service.StoreService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StoreApiController {

    private final StoreService storeService;


}

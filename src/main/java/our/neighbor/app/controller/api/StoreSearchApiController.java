package our.neighbor.app.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import our.neighbor.app.service.StoreService;
import our.neighbor.app.controller.common.ResultResponse;
import our.neighbor.app.domain.store.dto.StoreSearchRequest;
import our.neighbor.app.domain.store.dto.StoreSearchResponse;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class StoreSearchApiController {

    private final StoreService storeService;

    @GetMapping("/keyword")
    public ResultResponse<?> getStoresByKeyword(@RequestBody StoreSearchRequest.Keyword request) {
        List<StoreSearchResponse> result = storeService.getStoresByKeyword(request);

        return new ResultResponse<>(result.size(), result);
    }
}

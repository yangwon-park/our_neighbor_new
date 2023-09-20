package our.neighbor.app.controller.api.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import our.neighbor.app.domain.admin.dto.AdminDTO;
import our.neighbor.app.service.admin.AdminService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminApiController {

    private final AdminService adminService;

    @PostMapping
    public Long joinAdmin(@Validated @RequestBody AdminDTO.Save request) {
        return adminService.joinNewAdmin(request);
    }
}

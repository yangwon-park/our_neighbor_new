package our.neighbor.app.controller.api.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import our.neighbor.app.domain.admin.dto.AdminDTO;
import our.neighbor.app.service.admin.AdminService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminApiController {

    private final AdminService adminService;

    @GetMapping("/{adminId}")
    public AdminDTO.Detail getAdminDetail(@PathVariable Long adminId) {
        return adminService.getAdminDetail(adminId);
    }

    @PostMapping
    public Long joinAdmin(@Validated @RequestBody AdminDTO.Save request) {
        return adminService.joinAdmin(request);
    }

    @PutMapping("/{adminId}")
    public Long updateAdminInfo(@PathVariable Long adminId, @Validated @RequestBody AdminDTO.Update request) {
        return adminService.updateAdminInfo(adminId, request);
    }
}

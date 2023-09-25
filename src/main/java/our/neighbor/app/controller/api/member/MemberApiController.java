package our.neighbor.app.controller.api.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import our.neighbor.app.domain.member.dto.MemberDTO;
import our.neighbor.app.service.member.MemberService;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/existing-check")
    public ResponseEntity<?> checkExistingMember(@RequestBody MemberDTO.Sns request) {
        return ok(new JSONObject()
                .appendField("response", memberService.checkExistingMember(request))
                .appendField("status", SC_OK));
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinMember(@RequestBody MemberDTO.Join request) {
        return ok(new JSONObject()
                .appendField("response", memberService.joinMember(request))
                .appendField("status", SC_OK));
    }
}

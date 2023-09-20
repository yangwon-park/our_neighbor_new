package our.neighbor.app.core.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.util.Optional;


/*
    Spring Data JPA에서 Auditing 기능 사용을 위해 필수 (등록, 수정 추적)
 */
@EnableJpaAuditing
@Configuration
public class AuditingConfig {

    /*
        생성자, 수정자 Email로 받아옴
     */
    @Bean
    public AuditorAware<String> auditorProvider(HttpServletRequest request) {
        return () -> {
//            HttpSession session = request.getSession();
//            Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

//            if (member == null) {
//                return Optional.empty();
//            }
//
//            return Optional.of(member.getEmail());
            return null;
        };
    }
}

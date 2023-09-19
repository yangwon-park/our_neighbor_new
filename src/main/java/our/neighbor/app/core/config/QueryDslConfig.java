package our.neighbor.app.core.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class QueryDslConfig {

    @PersistenceContext(name = "appEntityManager", unitName = "appEntityManager")
    public EntityManager appEntityManager;

    @PersistenceContext(unitName = "logEntityManager")
    public EntityManager logEntityManager;

    @Bean
    @Primary
    public JPAQueryFactory appJpaQueryFactory() {
        return new JPAQueryFactory(appEntityManager);
    }

    @Bean
    public JPAQueryFactory logJpaQueryFactory() {
        return new JPAQueryFactory(logEntityManager);
    }
}

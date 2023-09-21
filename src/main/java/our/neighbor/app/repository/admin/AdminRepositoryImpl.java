package our.neighbor.app.repository.admin;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import our.neighbor.app.domain.admin.dto.AdminDTO;

import static our.neighbor.app.domain.admin.QAdmin.*;

@RequiredArgsConstructor
public class AdminRepositoryImpl implements AdminRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public AdminDTO.Detail getAdminDetailByAdminId(Long adminId) {
        return queryFactory
                .select(
                        Projections.constructor(
                                AdminDTO.Detail.class,
                                admin.id,
                                admin.name,
                                admin.phoneNumber,
                                admin.role
                        )
                ).from(admin)
                .where(admin.id.eq(adminId))
                .fetchOne();
    }
}

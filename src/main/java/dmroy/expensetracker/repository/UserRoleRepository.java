package dmroy.expensetracker.repository;

import dmroy.expensetracker.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Integer> {

    @Override
    public Iterable<UserRole> findAll(Sort sort);
    @Override
    public Page<UserRole> findAll(Pageable pageable);
    @Override
    public <S extends UserRole> S save(S s);
    @Override
    public <S extends UserRole> Iterable<S> saveAll(Iterable<S> iterable);
    @Override
    public Optional<UserRole> findById(Integer globalUserId);
    @Override
    public boolean existsById(Integer globalUserId);
    @Override
    public Iterable<UserRole> findAll();
    @Override
    public Iterable<UserRole> findAllById(Iterable<Integer> iterable);
    @Override
    public long count();
    @Override
    public void deleteById(Integer globalUserId);
    @Override
    public void delete(UserRole userRole);
    @Override
    public void deleteAll(Iterable<? extends UserRole> iterable);
    @Override
    public void deleteAll();

}

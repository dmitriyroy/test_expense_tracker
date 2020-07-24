package dmroy.expensetracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import dmroy.expensetracker.model.CustomUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomUserRepository extends PagingAndSortingRepository<CustomUser, Integer> {

    @Override
    public Iterable<CustomUser> findAll(Sort sort);
    @Override
    public Page<CustomUser> findAll(Pageable pageable);
    @Override
    public <S extends CustomUser> S save(S s);
    @Override
    public <S extends CustomUser> Iterable<S> saveAll(Iterable<S> iterable);
    @Override
    public Optional<CustomUser> findById(Integer globalUserId);
    @Override
    public boolean existsById(Integer globalUserId);
    @Override
    public Iterable<CustomUser> findAll();
    @Override
    public Iterable<CustomUser> findAllById(Iterable<Integer> iterable);
    @Override
    public long count();
    @Override
    public void deleteById(Integer globalUserId);
    @Override
    public void delete(CustomUser customUser);
    @Override
    public void deleteAll(Iterable<? extends CustomUser> iterable);
    @Override
    public void deleteAll();

    public CustomUser findByUsername(String username);
}

package dmroy.expensetracker.repository;

import dmroy.expensetracker.model.UserExpenses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserExpensesRepository extends PagingAndSortingRepository<UserExpenses, Integer> {

    @Override
    public Iterable<UserExpenses> findAll(Sort sort);
    @Override
    public Page<UserExpenses> findAll(Pageable pageable);
    @Override
    public <S extends UserExpenses> S save(S s);
    @Override
    public <S extends UserExpenses> Iterable<S> saveAll(Iterable<S> iterable);
    @Override
    public Optional<UserExpenses> findById(Integer globalUserId);
    @Override
    public boolean existsById(Integer globalUserId);
    @Override
    public Iterable<UserExpenses> findAll();
    @Override
    public Iterable<UserExpenses> findAllById(Iterable<Integer> iterable);
    @Override
    public long count();
    @Override
    public void deleteById(Integer globalUserId);
    @Override
    public void delete(UserExpenses userExpenses);
    @Override
    public void deleteAll(Iterable<? extends UserExpenses> iterable);
    @Override
    public void deleteAll();

    public List<UserExpenses> findAllByUserId(Integer userExpensesId);

}

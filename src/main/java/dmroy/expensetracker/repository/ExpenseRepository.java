package dmroy.expensetracker.repository;

import dmroy.expensetracker.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends PagingAndSortingRepository<Expense, Integer> {

    @Override
    public Iterable<Expense> findAll(Sort sort);
    @Override
    public Page<Expense> findAll(Pageable pageable);
    @Override
    public <S extends Expense> S save(S s);
    @Override
    public <S extends Expense> Iterable<S> saveAll(Iterable<S> iterable);
    @Override
    public Optional<Expense> findById(Integer globalUserId);
    @Override
    public boolean existsById(Integer globalUserId);
    @Override
    public Iterable<Expense> findAll();
    @Override
    public Iterable<Expense> findAllById(Iterable<Integer> iterable);
    @Override
    public long count();
    @Override
    public void deleteById(Integer globalUserId);
    @Override
    public void delete(Expense expense);
    @Override
    public void deleteAll(Iterable<? extends Expense> iterable);
    @Override
    public void deleteAll();

    public Page<Expense> findAllByExpenseIdIn(List<Integer> expenseIds, Pageable pageable);
    public List<Expense> findAllByExpenseIdIn(List<Integer> expenseIds);

}

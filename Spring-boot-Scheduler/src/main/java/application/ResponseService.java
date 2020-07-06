package application;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseService extends CrudRepository<ApiResponse,Integer> {
}

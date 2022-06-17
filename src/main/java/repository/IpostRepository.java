package repository;

import model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IpostRepository extends JpaRepository<Post,Long> {
 List<Post> findPostByTitleContaining(String title);
 @Query(value = "select * from post order by likes asc", nativeQuery = true)
 Iterable<Post> findAllByLikesAsc();
 @Query(value = "select * from post order by createAt desc limit 4", nativeQuery = true)
 Iterable<Post> findTop4New();
}

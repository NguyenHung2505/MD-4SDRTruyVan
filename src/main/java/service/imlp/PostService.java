package service.imlp;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IpostRepository;
import service.IPostIService;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostIService {
@Autowired
IpostRepository postRepository;

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> findByTitle(String title) {
        return postRepository.findPostByTitleContaining(title);
    }

    @Override
    public Iterable<Post> findAllByLikesAsc() {
        return postRepository.findAllByLikesAsc();
    }

    @Override
    public Iterable<Post> findTop4New() {
        return postRepository.findTop4New();
    }


}

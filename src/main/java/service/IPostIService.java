package service;

import model.Post;

import java.util.List;

public interface IPostIService extends IGeneralService<Post>{
    List<Post> findByTitle(String title);
    Iterable<Post> findAllByLikesAsc();
    Iterable<Post> findTop4New();
}

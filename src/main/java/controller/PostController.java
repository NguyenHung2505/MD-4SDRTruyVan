package controller;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IPostIService;
import java.time.LocalDateTime;
@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    IPostIService postIService;
    @GetMapping
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("/post/list");
        modelAndView.addObject("posts", postIService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/post/create");
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView save(Post post) {
        post.setCreateAt(LocalDateTime.now());
        postIService.save(post);
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/post/edit");
        Post post = postIService.findById(id).get();
        modelAndView.addObject("pro", post);
        return modelAndView;
    }
    @PostMapping("/edit/{id}")
    public ModelAndView update(@PathVariable Long id, Post post) {
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        post.setCreateAt(LocalDateTime.now());
        postIService.save(post);
        return modelAndView;
    }

    @GetMapping("/seach")
    public ModelAndView find(@RequestParam String title) {
        ModelAndView modelAndView = new ModelAndView("/post/seach");
        modelAndView.addObject("tim", postIService.findByTitle(title));
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/post/delete");
        Post post = postIService.findById(id).get();
        modelAndView.addObject("xoa", post);
        return modelAndView;
    }
    @PostMapping("/delete/{id}")
    public ModelAndView remove(@PathVariable Long id, Post post) {
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        post.setCreateAt(LocalDateTime.now());
        postIService.remove(id);
        return modelAndView;
    }

    @PostMapping("/post-asc")
    public ModelAndView newPost() {
        Iterable<Post> posts = postIService.findAllByLikesAsc();
        ModelAndView modelAndView = new ModelAndView("/post/postListAsc");
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @PostMapping("/post-top4-new")
    public ModelAndView newPostTop4() {
        Iterable<Post> posts = postIService.findTop4New();
        ModelAndView modelAndView = new ModelAndView("/post/postTop4New");
        modelAndView.addObject("top4", posts);
        return modelAndView;
    }

}

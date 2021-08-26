package temp_1.temp_1;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsWithMember1() {
        final List<Post> all = postRepository.findAll();
        all.forEach(
                post -> post.getMember().getId()
        );
        return all;
    }

    public List<Post> getPostsWithMember2() {
        final List<Post> all = postRepository.findAll();
        all.forEach(
                post -> post.getMember().getEmail()
        );
        return all;
    }
}

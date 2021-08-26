package temp_1.temp_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class Temp1ApplicationTests {

    @Autowired
    private EntityManager em;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PostService postService;

    @BeforeEach
    void BeforeEach() {
        Member member1 = Member.builder().email("test1@test.com").build();
        Member member2 = Member.builder().email("test2@test.com").build();

        Post post1 = Post.builder().title("title1").member(member1).build();
        Post post2 = Post.builder().title("title2").member(member1).build();
        Post post3 = Post.builder().title("title3").member(member1).build();
        Post post4 = Post.builder().title("title4").member(member2).build();
        Post post5 = Post.builder().title("title5").member(member2).build();
        Post post6 = Post.builder().title("title6").member(member2).build();

        member1.getPosts().add(post1);
        member1.getPosts().add(post2);
        member1.getPosts().add(post3);
        member2.getPosts().add(post4);
        member2.getPosts().add(post5);
        member2.getPosts().add(post6);

        em.persist(member1);
        em.persist(member2);
        em.persist(post1);
        em.persist(post2);
        em.persist(post3);
        em.persist(post4);
        em.persist(post5);
        em.persist(post6);
        em.flush();
        em.clear();
    }

    @Test
    void test1() {
        final List<Member> members = memberService.getMembers();

        assertTrue(emf.getPersistenceUnitUtil().isLoaded(members.get(0).getPosts()));
    }

    @Test
    void test2() {
        final List<Member> members = memberService.getMembersAndPosts();

        assertTrue(emf.getPersistenceUnitUtil().isLoaded(members.get(0).getPosts()));
    }

    @Test
    void test3() {
        final List<Post> posts = postService.getPosts();

        assertTrue(emf.getPersistenceUnitUtil().isLoaded(posts.get(0).getMember()));
    }


    @Test
    void test4() {
        final List<Post> posts = postService.getPostsWithMember1();

        assertTrue(emf.getPersistenceUnitUtil().isLoaded(posts.get(0).getMember()));
    }

    @Test
    void test5() {
        final List<Post> posts = postService.getPostsWithMember2();

        assertTrue(emf.getPersistenceUnitUtil().isLoaded(posts.get(0).getMember()));
    }

}

package temp_1.temp_1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> getMembers() {

        final List<Member> all = memberRepository.findAll();

        return all;
    }

    public List<Member> getMembersAndPosts() {

        final List<Member> all = memberRepository.findAll();
        all.forEach(
            member ->
                member.getPosts().forEach(
                        post -> post.getId()
                )
        );

        return all;
    }
}

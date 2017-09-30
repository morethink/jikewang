package studio.geek.dao;

import org.springframework.stereotype.Repository;
import studio.geek.entity.Member;
import studio.geek.util.Page;

import java.util.List;

@Repository
public interface IMemberDao {

    Member getMemberByMemberId(String memberId);

    int saveMember(Member member);

    int deleteMember(String memberId);

    int deleteMembers(List<String> members);



    List<Member> listMembersById(Page page);

    List<Member> listMembersByName(Page page);

    List<Member> listCurrentMembers(Page page);

    List<Member> listOldMembers(Page page);

    int updateMember(Member member);

}

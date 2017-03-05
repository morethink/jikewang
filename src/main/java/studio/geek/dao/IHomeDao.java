package studio.geek.dao;

import org.springframework.stereotype.Repository;
import studio.geek.entity.Candidate;
import studio.geek.entity.Member;
import studio.geek.entity.Production;
import studio.geek.util.CurrentMember;

import java.util.List;

/**
 * @author 李文浩
 * @version 2017/2/21.
 */

@Repository
public interface IHomeDao {

    List<Member> getAllOldMembers();

    List<CurrentMember> getCurrentMembersByGrade(String grade);


    List<Production> getAllProductions();

    int saveCandidate(Candidate candidate);

    Candidate getCandidateById(String candidateId);
}
